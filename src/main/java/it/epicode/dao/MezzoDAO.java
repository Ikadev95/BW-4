package it.epicode.dao;

import it.epicode.entity.Mezzo;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MezzoDAO {
    private EntityManager em;

    public void save(Mezzo oggetto) {
        em.getTransaction().begin();
        em.persist(oggetto);
        em.getTransaction().commit();
    }

    public Mezzo findById(String id) {
        return em.find(Mezzo.class, id);
    }

    public List<Mezzo> findAll() {
        return em.createNamedQuery("Trova_tutto_Mezzo", Mezzo.class).getResultList();
    }

    public void update(Mezzo oggetto) {
        em.getTransaction().begin();
        em.merge(oggetto);
        em.getTransaction().commit();
    }

    public void delete(Mezzo oggetto) {
        em.getTransaction().begin();
        em.remove(oggetto);
        em.getTransaction().commit();
    }

    public  List<Mezzo> findAllAvailable(){return em.createNamedQuery("Trova_tutti_Disponibili", Mezzo.class).getResultList();}


}
