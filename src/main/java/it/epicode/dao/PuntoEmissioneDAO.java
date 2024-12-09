package it.epicode.dao;

import it.epicode.entity.PuntoEmissione;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PuntoEmissioneDAO {
    private EntityManager em;

    public void save(PuntoEmissione oggetto) {
        em.getTransaction().begin();
        em.persist(oggetto);
        em.getTransaction().commit();
    }

    public PuntoEmissione findById(Long id) {
        return em.find(PuntoEmissione.class, id);
    }

    public List<PuntoEmissione> findAll() {
        return em.createNamedQuery("Trova_tutto_PuntoEmissione", PuntoEmissione.class).getResultList();
    }

    public void update(PuntoEmissione oggetto) {
        em.getTransaction().begin();
        em.merge(oggetto);
        em.getTransaction().commit();
    }

    public void delete(PuntoEmissione oggetto) {
        em.getTransaction().begin();
        em.remove(oggetto);
        em.getTransaction().commit();
    }


}
