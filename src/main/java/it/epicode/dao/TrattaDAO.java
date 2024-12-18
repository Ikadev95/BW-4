package it.epicode.dao;

import it.epicode.entity.Tratta;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TrattaDAO {
    private EntityManager em;

    public void save(Tratta oggetto) {
        em.getTransaction().begin();
        em.persist(oggetto);
        em.getTransaction().commit();
    }

    public Tratta findById(Long id) {
        return em.find(Tratta.class, id);
    }

    public List<Tratta> findAll() {
        return em.createNamedQuery("Trova_tutto_Tratta", Tratta.class).getResultList();
    }

    public void update(Tratta oggetto) {
        em.getTransaction().begin();
        em.merge(oggetto);
        em.getTransaction().commit();
    }

    public void delete(Tratta oggetto) {
        em.getTransaction().begin();
        em.remove(oggetto);
        em.getTransaction().commit();
    }

    public Tratta getTrattaByName(String name) {
        return em.createQuery("SELECT t FROM Tratta t WHERE t.nome = :nome", Tratta.class)
                .setParameter("nome", name )
                .getSingleResult();
    }


}
