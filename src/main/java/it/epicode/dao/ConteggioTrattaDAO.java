package it.epicode.dao;

import it.epicode.entity.ConteggioTratta;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ConteggioTrattaDAO {
    private EntityManager em;

    public void save(ConteggioTratta oggetto) {
        em.getTransaction().begin();
        em.persist(oggetto);
        em.getTransaction().commit();
    }

    public ConteggioTratta findById(Long id) {
        return em.find(ConteggioTratta.class, id);
    }

    public List<ConteggioTratta> findAll() {
        return em.createNamedQuery("Trova_tutto_ConteggioTratta", ConteggioTratta.class).getResultList();
    }

    public void update(ConteggioTratta oggetto) {
        em.getTransaction().begin();
        em.merge(oggetto);
        em.getTransaction().commit();
    }

    public void delete(ConteggioTratta oggetto) {
        em.getTransaction().begin();
        em.remove(oggetto);
        em.getTransaction().commit();
    }


}
