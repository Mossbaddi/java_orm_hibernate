package com.exemple.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.exemple.models.Article;

public class ArticleDao extends GenericDaoImpl<Article, Long> {
    
     public ArticleDao(SessionFactory sessionFactory) {
        super(sessionFactory, Article.class);
     }

     public List<Article> trouverParAuteur(Long utilisateurId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Article a WHERE a.auteur.id = :utilisateurId";
            return session.createQuery(hql, Article.class)
                    .setParameter("utilisateurId", utilisateurId)
                    .getResultList();
        } 
    }
    
    public List<Article> chercherParTitre(String motcle) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Article a WHERE a.titre LIKE :motcle";
            return session.createQuery(hql, Article.class)
                    .setParameter("motcle", "%" + motcle + "%")
                    .getResultList();
            }
        }
        

}
