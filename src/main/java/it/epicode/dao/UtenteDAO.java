package it.epicode.dao;

import it.epicode.entity.Tessera;
import it.epicode.entity.Utente;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class UtenteDAO {
    private EntityManager em;

    public void save(Utente oggetto) {
        em.getTransaction().begin();
        em.persist(oggetto); // Salva l'utente
        em.getTransaction().commit();

        Tessera tessera = new Tessera();
        tessera.setClienteId(oggetto); // Collega l'utente alla tessera
        tessera.setDataEmissione(LocalDate.now());
        tessera.setDataScadenza(LocalDate.now().plusYears(1));

        em.getTransaction().begin();
        em.persist(tessera); // Salva la tessera
        oggetto.setTessera(tessera); // Aggiorna l'utente con la tessera
        em.merge(oggetto); // Salva di nuovo l'utente con la relazione aggiornata
        em.getTransaction().commit();
    }


    public Utente findById(Long id) {
        return em.find(Utente.class, id);
    }

    public List<Utente> findAll() {
        return em.createNamedQuery("Trova_tutto_Utente", Utente.class).getResultList();
    }

    public void update(Utente oggetto) {
        em.getTransaction().begin();
        em.merge(oggetto);
        em.getTransaction().commit();
    }

    public void delete(Utente oggetto) {
        em.getTransaction().begin();
        em.remove(oggetto);
        em.getTransaction().commit();
    }




}
