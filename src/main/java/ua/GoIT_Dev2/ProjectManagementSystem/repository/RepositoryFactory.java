package ua.GoIT_Dev2.ProjectManagementSystem.repository;

import ua.GoIT_Dev2.ProjectManagementSystem.model.BaseEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RepositoryFactory {

    private final static Map<String, BaseRepository> REPOSITORIES = new ConcurrentHashMap<>();

    public synchronized static <E extends BaseEntity<ID>, R extends BaseRepository<E, ID>, ID> BaseRepository<E, ID> of(Class<E> modelClass) {
        final String modelName = modelClass.getName();
        if (!REPOSITORIES.containsKey(modelName)) {
            REPOSITORIES.put(modelName, new HibernateRepositoryImpl<E, ID>(modelClass));
        }
        return REPOSITORIES.get(modelName);
    }
}
