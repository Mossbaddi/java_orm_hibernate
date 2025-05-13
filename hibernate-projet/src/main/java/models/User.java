package models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    // @Column(columnDefinition="varchar(50)")
    private String nom;

    private int age;




    public User() {
    }

    public User( String nom) {
        this.nom = nom;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }
}
