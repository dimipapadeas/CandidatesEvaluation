package com.dterz.repositories;

import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.FlushMode;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public abstract class GenericRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    protected final Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public T read(final long id) {
        return entityManager.find(clazz, id);
    }

    public T readOnly(final long id) {
        entityManager.unwrap(Session.class).setDefaultReadOnly(true);
        entityManager.unwrap(Session.class).setHibernateFlushMode(FlushMode.MANUAL);
        return entityManager.find(clazz, id);
    }

    public T getReference(final long id) {
        return entityManager.getReference(clazz, id);
    }

    public T save(final T entity) {
        return entityManager.merge(entity);
    }

    public void persist(final T entity) {
        entityManager.persist(entity);
    }

    public void delete(final long id) {
        final T entity = entityManager.getReference(clazz, id);

        if (entity != null) {
            entityManager.remove(entity);
            entityManager.flush();
        }
    }

    public void flush() {
        entityManager.flush();
    }


    public void clear() {
        entityManager.clear();
    }

    public void detach(final T entity) {
        entityManager.detach(entity);
    }

    public List<T> getAll() {
        final JPAQuery<T> query = new JPAQuery<>(entityManager);
        final PathBuilder<T> entityPath = new PathBuilder<>(clazz, "entity");

        return query.select(entityPath).from(entityPath).fetch();

    }

    public <T> T unwrap(final Class<T> cls) {
        return entityManager.unwrap(cls);
    }
}
