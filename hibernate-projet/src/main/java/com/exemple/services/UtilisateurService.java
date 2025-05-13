package com.exemple.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import models.Utilisateur;

public class UtilisateurService {
    private final SessionFactory sessionFactory;

    public UtilisateurService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void creer(Utilisateur utilisateur) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction(); 
            session.persist(utilisateur);
            session.getTransaction().commit();
        }
    }

    public Utilisateur lire(Long id) {
        try (Session session = sessionFactory.openSession()){
            return session.get(Utilisateur.class, id);            
        }
    }

    public void MettreAJour(Utilisateur utilisateur) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(utilisateur);
            session.getTransaction().commit();
        }
    }
    

    public void supprimer(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Utilisateur utilisateur = session.get(Utilisateur.class, id);
            if (utilisateur != null) {
                session.remove(utilisateur);
            }
            session.getTransaction().commit();

        }
    }


}
