package it.epicode.dao;

import it.epicode.entity.Mezzo;
import it.epicode.entity.PreSet;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PreSetDAO {
    private EntityManager em;

    public void save(PreSet oggetto) {
        em.getTransaction().begin();
        em.persist(oggetto);
        em.getTransaction().commit();
    }

    public PreSet findById(Long id) {
        return em.find(PreSet.class, id);
    }

    public List<PreSet> findAll() {
        return em.createNamedQuery("Trova_tutto_PreSet", PreSet.class).getResultList();
    }

    public void update(PreSet oggetto) {
        em.getTransaction().begin();
        em.merge(oggetto);
        em.getTransaction().commit();
    }

    public void delete(PreSet oggetto) {
        em.getTransaction().begin();
        em.remove(oggetto);
        em.getTransaction().commit();
    }

    public List<PreSet> getOrderedByMezzo() {
        return em.createNamedQuery("Grouped_by_mezzo", PreSet.class).getResultList();
    }



}
