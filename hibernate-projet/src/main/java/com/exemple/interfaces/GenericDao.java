package com.exemple.interfaces;

import java.util.List;

public interface GenericDao <T, ID> {
    void creer(T entity);
    T lire(ID id);
    void mettreAJour(T entity);
    void supprimer(ID id);
    List<T> tout();
}
