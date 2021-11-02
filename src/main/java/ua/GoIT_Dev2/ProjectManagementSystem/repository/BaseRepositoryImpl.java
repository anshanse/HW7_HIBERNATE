package ua.GoIT_Dev2.ProjectManagementSystem.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ua.GoIT_Dev2.ProjectManagementSystem.model.BaseEntity;
import ua.GoIT_Dev2.ProjectManagementSystem.util.DBConnection;
import ua.GoIT_Dev2.ProjectManagementSystem.util.PropertiesLoader;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Closeable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BaseRepositoryImpl<T extends BaseEntity<ID>, ID> implements BaseRepository<T, ID>, Closeable {
    private final Connection connection;

    private final ObjectMapper mapper;
    private final Class<T> modelClass;
    private final Map<String,String> columnFieldName;
    private final String dbSchemaName;

    private final PreparedStatement findAllPreparedStatement;
    private final PreparedStatement findByIdPreparedStatement;
    private final PreparedStatement deletePreparedStatement;
    private final PreparedStatement createPreparedStatement;
    private final PreparedStatement updatePreparedStatement;
    private final PreparedStatement findByNamePreparedStatement;

    @SneakyThrows
    public BaseRepositoryImpl(Class<T> modelClass) {
        this.connection = DBConnection.getInstance().getConnection();
        this.dbSchemaName = PropertiesLoader.getProperty("db.name");
        this.mapper = new ObjectMapper();
        this.modelClass = modelClass;
        this.columnFieldName = Arrays.stream(this.modelClass.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .collect(Collectors.toMap(field -> getColumnName(field), field -> field.getName()));
        String generatedColumns[] = {getColumnName(Arrays.stream(this.modelClass.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .filter(field -> field.getAnnotation(Id.class) != null)
                .findAny().orElseThrow(() -> new RuntimeException("Entity must contains ID")))};
        String tableName = modelClass.getAnnotation(Entity.class) != null
                ? modelClass.getAnnotation(Entity.class).name() : modelClass.getSimpleName().toLowerCase();
        String countValues = IntStream.range(0, columnFieldName.size()).mapToObj(i -> "?").collect(Collectors.joining(","));
        String fieldsForCreate = columnFieldName.keySet().stream().collect(Collectors.joining(","));
        String fieldsForUpdate = columnFieldName.keySet().stream().map(v -> v + "=?").collect(Collectors.joining(","));

        this.findAllPreparedStatement = connection.prepareStatement("SELECT * FROM " + tableName, generatedColumns);
        this.findByIdPreparedStatement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id=?;", generatedColumns);
        this.deletePreparedStatement = connection.prepareStatement("DELETE FROM " + tableName + " WHERE id=?;",generatedColumns);
        this.createPreparedStatement = connection.prepareStatement("INSERT INTO " + tableName + " ("
                + fieldsForCreate + ")" + " VALUES (" + countValues + ");", generatedColumns);
        this.updatePreparedStatement = connection.prepareStatement(
                "UPDATE " + tableName + " SET " + fieldsForUpdate + " WHERE id=?", generatedColumns);
        this.findByNamePreparedStatement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE name=?;", generatedColumns);
    }

    private String getColumnName (Field field){
        return field.getAnnotation(Column.class) == null ? field.getName() : field.getAnnotation(Column.class).name();
    }

    @SneakyThrows
    @Override
    public Optional<T> findById(ID id) {
        findByIdPreparedStatement.setObject(1, id);
        final List<T> result = parse (findByIdPreparedStatement.executeQuery());
        if (result.isEmpty()) return Optional.empty();
        if (result.size() > 1) throw new RuntimeException("Method 'find by id' returned more than one result");
        return Optional.of(result.get(0));
    }

    @Override
    public T getOne(ID id) {
        Optional<T> byId = findById(id);
        if (byId.isEmpty()){
            return null;
        } else {
            return findById(id).orElseThrow(() -> new RuntimeException("Entity with id " + id + " not found"));
        }
    }

    @SneakyThrows
    @Override
    public List<T> findByName(String name) {
        findByNamePreparedStatement.setObject(1, name);
        List<T> result = parse (findByNamePreparedStatement.executeQuery());
        return parse(findByNamePreparedStatement.executeQuery());
    }

    @SneakyThrows
    @Override
    public List<T> findAll() {
        return parse(findAllPreparedStatement.executeQuery());
    }

    @SneakyThrows
    @Override
    public void close() {
        connection.close();
    }

    @Override
    public List<T> saveAll(Iterable<T> itrbl) {
        final  List<T> result = new ArrayList<>();
        for (T t : itrbl) result.add(save(t));
        return result;
    }

    @SneakyThrows
    @Override
    public void deleteById(ID id) {
        deletePreparedStatement.setObject(1, id);
        deletePreparedStatement.executeUpdate();
    }


    @SneakyThrows
    @Override
    public T save(T t) {
        if (t.getId() == null || !findById(t.getId()).isPresent()) return executeStatement(createPreparedStatement, t);
        else {
            updatePreparedStatement.setObject(columnFieldName.size()+1, t.getId());
            return executeStatement(updatePreparedStatement, t);
        }
    }

    @SneakyThrows
    private List<T> parse(ResultSet resultSet) {
        final List<T> result = new ArrayList<>();
        while (resultSet.next()) {
            final Map<String, Object> objectMap = new HashMap<>();
            for (String fieldName : columnFieldName.keySet()) {
                objectMap.put(columnFieldName.get(fieldName), resultSet.getObject(fieldName));
            }
            result.add(mapper.convertValue(objectMap, modelClass));
        }
        return result;
    }

    @SneakyThrows
    private T executeStatement(PreparedStatement preparedStatement, T t) {
        int count = 1;
        for (String fieldName : columnFieldName.values()) {
            Field declaredField = modelClass.getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            preparedStatement.setObject(count++, declaredField.get(t));
        }
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        ID id;
        try {
            id = (ID) generatedKeys.getObject(1);
        }
        catch (Exception e){
            id = t.getId();
        }
        return findById(id).get();
    }

    @SneakyThrows
    public List<String> sendQuery(String sql){
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSetToList(resultSet);
    }

    @SneakyThrows
    private List<String> resultSetToList (ResultSet rs){
        List<String> rsList = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        while (rs.next()){
            StringBuilder row = new StringBuilder();
            for (int i = 1; i <= columns ; i++) {
                row.append(String.join(": ",rsmd.getColumnName(i), rs.getString(i)));
                if (i<columns) row.append(", ");
            }
            rsList.add(row.toString());
        }
        return rsList;
    }
}
