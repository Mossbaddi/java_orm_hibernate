package com.exemple;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.exemple.dao.ArticleDao;
import com.exemple.dao.UtilisateurDao;
import com.exemple.models.Article;
import com.exemple.models.Utilisateur;
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

        // Instantier nos DAO
        UtilisateurDao utilisateurDao = new UtilisateurDao(sessionFactory);
        ArticleDao articleDao = new ArticleDao(sessionFactory);

        // Créer utilisateurs et articles
        Utilisateur utilisateur1 = new Utilisateur("utilisateur1", "bob@bobby.bob");
        Utilisateur utilisateur2 = new Utilisateur("utilisateur2", "john@john.john");
        utilisateurDao.creer(utilisateur1);
        utilisateurDao.creer(utilisateur2);

        Article article1 = new Article("Article 1", "Contenu de l'article 1", utilisateur1);
        Article article2 = new Article("Article 2: le retour du 1", "Contenu de l'article 2", utilisateur1);
        Article article3 = new Article("Article 3", "Contenu de l'article 3", utilisateur2);
        articleDao.creer(article1);
        articleDao.creer(article2); 
        articleDao.creer(article3);

        // Lister les articles d'un utilisateur
        System.out.println("Articles de l'utilisateur 1 :");
        for (Article article : articleDao.trouverParAuteur(utilisateur1.getId())) {
            System.out.println(article);
        }

        // Lister les articles contenant un mot clé
        System.out.println("Articles contenant '1' :");
        for (Article article : articleDao.chercherParTitre("1")) {
            System.out.println(article);
        }

        // Trouver un utilisateur par son email
        Utilisateur utilisateurTrouve = utilisateurDao.trouverParEmail("bob@bobby.bob");
        if (utilisateurTrouve != null) {
            System.out.println("Utilisateur trouvé : " + utilisateurTrouve);
        } else {
            System.out.println("Aucun utilisateur trouvé avec cet email.");
        }


    }
}
