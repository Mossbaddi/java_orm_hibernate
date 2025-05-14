package models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    
    private String titre;

    private String contenu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auteur_id", nullable = false)
    private Utilisateur auteur;


    public Article() {
    }

    public Article(String titre, String contenu, Utilisateur auteur) {
        this.titre = titre;
        this.contenu = contenu;
        this.auteur = auteur;
    }

    public Long getId() {
        return id;
    }
    public String getTitre() {
        return titre;
    }
    public String getContenu() {
        return contenu;
    }
    public Utilisateur getAuteur() {
        return auteur;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    public void setAuteur(Utilisateur auteur) {
        this.auteur = auteur;
    }


    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", contenu='" + contenu + '\'' +
                ", auteur=" + auteur +
                '}';
    }
    
}
