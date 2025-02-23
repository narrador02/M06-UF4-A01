package com.iticbcn.jairo.dao;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import jakarta.validation.ConstraintViolationException;
import java.util.List;

public abstract class GenDAOImpl<T> implements GenDAO<T> {

    private final SessionFactory sessionFactory;
    private final Class<T> entityClass;

    public GenDAOImpl(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    @Override
    public T get(int id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.get(entityClass, id);
        } catch (HibernateException e) {
            handleException(e, "Error al obtener entidad");
            return null;
        }
    }

    @Override
    public List<T> getAll() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Query<T> query = session.createQuery("FROM " + entityClass.getSimpleName(), entityClass);
            return query.getResultList();
        } catch (HibernateException e) {
            handleException(e, "Error al obtener todas las entidades");
            return List.of();
        }
    }

    @Override
    public void save(T entity) throws Exception {
        executeTransaction(session -> session.persist(entity), "Error al guardar entidad");
    }

    @Override
    public void update(T entity) throws Exception {
        executeTransaction(session -> session.merge(entity), "Error al actualizar entidad");
    }

    @Override
    public void delete(T entity) throws Exception {
        executeTransaction(session -> session.remove(entity), "Error al eliminar entidad");
    }

    private void executeTransaction(SessionOperation operation, String errorMessage) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                operation.execute(session);
                session.getTransaction().commit();
            } catch (ConstraintViolationException e) {
                rollback(session, "Violación de restricción: " + e.getMessage());
            } catch (HibernateException e) {
                rollback(session, errorMessage + ": " + e.getMessage());
            }
        }
    }

    private void rollback(Session session, String message) throws Exception {
        if (session.getTransaction().isActive()) {
            session.getTransaction().rollback();
        }
        throw new Exception(message);
    }

    private void handleException(Exception e, String message) throws Exception {
        throw new Exception(message + ": " + e.getMessage());
    }

    @FunctionalInterface
    private interface SessionOperation {
        void execute(Session session);
    }
}