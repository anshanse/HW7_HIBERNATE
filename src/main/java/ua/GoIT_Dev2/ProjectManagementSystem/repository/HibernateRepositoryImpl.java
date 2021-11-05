package ua.GoIT_Dev2.ProjectManagementSystem.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import ua.GoIT_Dev2.ProjectManagementSystem.model.BaseEntity;
import ua.GoIT_Dev2.ProjectManagementSystem.util.HibernateSessionFactory;

import java.io.Closeable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

class HibernateRepositoryImpl <T extends BaseEntity<ID>, ID> implements BaseRepository<T, ID>, Closeable {

    private final Class<T> modelClass;

    HibernateRepositoryImpl (Class<T> modelClass){
        this.modelClass = modelClass;
    }

    @Override
    public List<T> saveAll(Iterable<T> itrb) {
        return StreamSupport.stream(itrb.spliterator(), false)
                .map(entity -> save(entity))
                .collect(Collectors.toList());
    }

    @Override
    public T save(T t) {
        Session session= createSession();
        ID id =t.getId() == null ? save(t,session) : update(t,session);
        Optional<T> result = getById(id, session);
        closeSession(session);
        return result.get();
    }

    private ID save (T t, Session session){
        return (ID) session.save(t);
    }

    private ID update(T t, Session session){
        session.saveOrUpdate(t);
        return t.getId();
    }

    @Override
    public void deleteById(ID id) {
        Session session = createSession();
        getById(id, session).ifPresent(entity -> session.remove(entity));
        closeSession(session);
    }

    private Optional<T> getById(ID id, Session session){
        return Optional.ofNullable(session.get(modelClass, id));
    }

    @Override
    public T getOne(ID id) {
        return findById(id).get();
    }

    @Override
    public Optional<T> findById(ID id) {
        Session session = createSession();
        Optional<T> result = getById(id, session);
        closeSession(session);
        return result;
    }

    @Override
    public List<T> findAll() {
        Session session = createSession();
        JpaCriteriaQuery<T> query = session.getCriteriaBuilder().createQuery(modelClass);
        List<T> result = session.createQuery(query.select(query.from(modelClass))).getResultList();
        return result;
    }

    @Override
    public List<T> findByName(String name) {
        Session session = createSession();
        JpaCriteriaQuery<T> query = session.getCriteriaBuilder().createQuery(modelClass);
        List<T> result = session.createQuery(query.select(query.from(modelClass))
                .where(session.getCriteriaBuilder().equal(query.from(modelClass).get("name"), name ))).getResultList();
        return result;
    }

    @Override
    public void close() {
        HibernateSessionFactory.close();
    }

    private Session createSession(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }

    private void closeSession (Session session){
        session.getTransaction().commit();
        session.close();
    }
}
