package it.epicode.dao;

import it.epicode.entity.Tessera;
import it.epicode.entity.Utente;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor
public class TesseraDAO {
    private EntityManager em;

    public void saveTessera(Tessera oggetto, Utente utente) {
        em.getTransaction().begin();
        oggetto.setClienteId(utente);
        oggetto.setDataEmissione(LocalDate.now());
        oggetto.setDataScadenza(LocalDate.now().plusYears(1));
        em.persist(oggetto);
        em.getTransaction().commit();
    }

    public Tessera findById(Long id) {
        return em.find(Tessera.class, id);
    }

    public List<Tessera> findAll() {
        return em.createNamedQuery("Trova_tutto_Tessera", Tessera.class).getResultList();
    }

    public void update(Tessera oggetto) {
        em.getTransaction().begin();
        em.merge(oggetto);
        em.getTransaction().commit();
    }

    public void delete(Tessera oggetto) {
        em.getTransaction().begin();
        em.remove(oggetto);
        em.getTransaction().commit();
    }


}
