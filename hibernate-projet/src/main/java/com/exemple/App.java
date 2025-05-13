package com.exemple;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.exemple.services.UtilisateurService;

import models.Utilisateur;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Démarrage de l'application");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();
        Metadata metadata = new MetadataSources(registry).buildMetadata();
        SessionFactory sessionFactory = metadata.buildSessionFactory();
        System.out.println("Connexion réussie !");


        UtilisateurService service = new UtilisateurService(sessionFactory);

        // Créer un user
        Utilisateur utilisateur = new Utilisateur("Bob", "bob@bob.bob");
        service.creer(utilisateur);
        System.out.println("Utilisateur créé : " + utilisateur);

        // Lire un user
        Utilisateur utilisateurLu = service.lire(utilisateur.getId());
        System.out.println("Utilisateur lu : " + utilisateurLu);

        // Mettre à jour un user
        utilisateurLu.setNom("John");
        service.MettreAJour(utilisateurLu);
        System.out.println("Utilisateur mis à jour : " + utilisateurLu);

        // Supprimer un user
        service.supprimer(utilisateurLu.getId());

    }
}
