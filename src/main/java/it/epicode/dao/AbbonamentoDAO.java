package it.epicode.dao;

import it.epicode.entity.Abbonamento;
import it.epicode.entity.ConteggioTratta;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

@AllArgsConstructor
public class AbbonamentoDAO {
    private EntityManager em;

    public void save(Abbonamento oggetto) {
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

    public Abbonamento findById(Long id) {
        return em.find(Abbonamento.class, id);
    }

    public List<Abbonamento> findAll() {
        return em.createNamedQuery("Trova_tutto_Abbonamento", Abbonamento.class).getResultList();
    }

    public void update(Abbonamento oggetto) {
        em.getTransaction().begin();
        em.merge(oggetto);
        em.getTransaction().commit();
    }

    public void delete(Abbonamento oggetto) {
        em.getTransaction().begin();
        em.remove(oggetto);
        em.getTransaction().commit();
    }


}
