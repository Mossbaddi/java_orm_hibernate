package com.exemple.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.exemple.models.Utilisateur;

public class UtilisateurDao extends GenericDaoImpl<Utilisateur, Long> {

    
    public UtilisateurDao(SessionFactory sessionFactory) {
        super(sessionFactory, Utilisateur.class);
    }

    public Utilisateur trouverParEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Utilisateur WHERE email = :email";
            return session.createQuery(hql, Utilisateur.class)
                    .setParameter("email", email)
                    .uniqueResult();

    }

}
}
