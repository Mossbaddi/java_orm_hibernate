package com.exemple;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Service<A> {
    private final SessionFactory sessionFactory;
    private final Class<A> type;

    @SuppressWarnings("unchecked")
    public Service() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        Metadata metadata = new MetadataSources(registry).buildMetadata();
        sessionFactory = metadata.buildSessionFactory();

        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        this.type = (Class<A>) pt.getActualTypeArguments()[0];
    }

    public void create(A entity){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        }
    }

    public A read(Long id){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            A entity = session.get(type, id);
            session.getTransaction().commit();

            return entity;
        }
    }

    /*public void update(A entity){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge;
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    public void delete(Long id){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.ggg;
            session.delete(id);
            session.getTransaction().commit();
        }
    }*/
}