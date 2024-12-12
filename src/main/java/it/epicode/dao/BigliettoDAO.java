package it.epicode.dao;

import it.epicode.entity.Biglietto;
import it.epicode.entity.ConteggioTratta;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Random;

@AllArgsConstructor
public class BigliettoDAO {
    private EntityManager em;

    public void save(Biglietto oggetto) {
        ConteggioTrattaDAO conteggioTrattaDAO = new ConteggioTrattaDAO(em);
        em.getTransaction().begin();
        em.persist(oggetto);
        em.getTransaction().commit();
        ConteggioTratta conteggioTratta = new ConteggioTratta();
        conteggioTratta.setTratta(oggetto.getTratta());
        conteggioTratta.setMezzo(oggetto.getMezzo());
        Random random = new Random();
        int randomNumber = random.nextInt(371) + 30;
        conteggioTratta.setDurata(randomNumber);
        conteggioTrattaDAO.save(conteggioTratta);
    }

    public Biglietto findById(Long id) {
        return em.find(Biglietto.class, id);
    }

    public List<Biglietto> findAll() {
        return em.createNamedQuery("Trova_tutto_Biglietto", Biglietto.class).getResultList();
    }

    public void update(Biglietto oggetto) {
        em.getTransaction().begin();
        em.merge(oggetto);
        em.getTransaction().commit();
    }

    public void delete(Biglietto oggetto) {
        em.getTransaction().begin();
        em.remove(oggetto);
        em.getTransaction().commit();
    }

    public List<Biglietto> findActive() {
        List<Biglietto> result = em.createNamedQuery("Trova_attivi", Biglietto.class).getResultList();
        return result;
    }

    public List<Biglietto> findExpired() {
        List<Biglietto> result = em.createNamedQuery("Trova_scaduti", Biglietto.class).getResultList();
        return result;
    }



}
