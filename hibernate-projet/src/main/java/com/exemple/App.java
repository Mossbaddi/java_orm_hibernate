package com.exemple;

import java.util.List;

import org.hibernate.Session;

import models.HQLDemo;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // System.out.println("Démarrage de l'application");
        // StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
        //     .configure()
        //     .build();
        // Metadata metadata = new MetadataSources(registry).buildMetadata();
        // SessionFactory sessionFactory = metadata.buildSessionFactory();
        // System.out.println("Connexion réussie !");

        // Insertion de données de test pour HQLDemo
        try (Session s = HQLDemo.sessionFactory.openSession()) {
            s.beginTransaction();
            HQLDemo demo1 = new HQLDemo("Test 1", 15);
            HQLDemo demo2 = new HQLDemo("Test 2", 20);
            HQLDemo demo3 = new HQLDemo("Test 3", 18);
            s.persist(demo1);
            s.persist(demo2);
            s.persist(demo3);
            s.getTransaction().commit();
        }

        // Récupération de données
        System.out.println("Récupération des données :");
        System.out.println("-------------------------------------------------");
        List<HQLDemo> alldemos = HQLDemo.fetchAll();
        for (HQLDemo demo : alldemos) {
            System.out.println("ID: " + demo.getId());
            System.out.println("Titre: " + demo.getTitle());
            System.out.println("Score: " + demo.getScore());
            System.out.println("Date de création: " + demo.getCreatedOn());
            System.out.println("-------------------------------------------------");
        }

        // Récupération avec un score supérieur à 19
        System.out.println("Récupération des données avec un score supérieur à 19 :");
        System.out.println("-------------------------------------------------");
        List<HQLDemo> filteredDemos = HQLDemo.fetchWithMinScore(19);
        for (HQLDemo demo : filteredDemos) {
            System.out.println("ID: " + demo.getId());
            System.out.println("Titre: " + demo.getTitle());
            System.out.println("Score: " + demo.getScore());
            System.out.println("Date de création: " + demo.getCreatedOn());
            System.out.println("-------------------------------------------------");
        }


    }
}
