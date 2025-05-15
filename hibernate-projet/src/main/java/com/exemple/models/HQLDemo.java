package com.exemple.models;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.sqm.tree.from.SqmRoot;

import com.google.protobuf.RpcCallback;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Entity
public class HQLDemo {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int score;

    private LocalDate createdOn = LocalDate.now();

    public HQLDemo() {
    }

    public HQLDemo(String title, int score) {
        this.title = title;
        this.score = score;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public LocalDate getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public static final SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(HQLDemo.class).buildSessionFactory();

    // public static List<HQLDemo> fetchAll() {
    //     try (Session s = sessionFactory.openSession()) {
    //         String hql = "FROM HQLDemo";
    //         return s.createQuery(hql, HQLDemo.class).list();
    //     }
    // }

    // public static List<HQLDemo> fetchWithMinScore(int min) {
    //     try (Session s = sessionFactory.openSession()) {
    //         String hql = "FROM HQLDemo WHERE score >= :param";
    //         return s.createQuery(hql, HQLDemo.class)
    //                 .setParameter("param", min)
    //                 .list();
    //     }
    // }


    // Using Criteria API
    public static List<HQLDemo> fetchAll() {
        try (Session s = sessionFactory.openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<HQLDemo> cq = cb.createQuery(HQLDemo.class);
            var root = cq.from(HQLDemo.class);

            cq.select(root);

            return s.createQuery(cq).getResultList();
        }
    }

    public static List<HQLDemo> fetchWithMinScore(int min) {
        try (Session s = sessionFactory.openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<HQLDemo> cq = cb.createQuery(HQLDemo.class);
            Root<HQLDemo> root = cq.from(HQLDemo.class);
            cq.select(root)
                .where(cb.gt(root.get("score"), min));
            
            return s.createQuery(cq).getResultList();
        }

        
    }


    @Override
    public String toString() {
        return "HQLDemo [id=" + id + ", title=" + title + ", score=" + score + ", createdOn=" + createdOn + "]";
    }
}
