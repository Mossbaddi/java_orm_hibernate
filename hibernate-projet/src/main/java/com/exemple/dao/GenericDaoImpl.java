package com.exemple.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.exemple.interfaces.GenericDao;

public abstract class GenericDaoImpl<T, ID> implements GenericDao<T, ID> {
    protected final SessionFactory sessionFactory;
    private final Class<T> entityClass;

    public GenericDaoImpl(SessionFactory sessionFactory, Class<T> entityClass) {
        this.entityClass = entityClass;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void creer(T entity) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public T lire(ID id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(entityClass, id);
        }
    }

    @Override
    public void mettreAJour(T entity) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void supprimer(ID id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            T entity = session.get(entityClass, id);
            if (entity != null) {
                session.remove(entity);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public List<T> tout() {
        try (var session = sessionFactory.openSession()) {
            String hql = "FROM " + entityClass.getName();
            return session.createQuery(hql, entityClass).getResultList();
        }
    }    

}
