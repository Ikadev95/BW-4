import com.github.javafaker.Faker;
import it.epicode.dao.AbbonamentoDAO;
import it.epicode.dao.BigliettoDAO;
import it.epicode.dao.PreSetDAO;
import it.epicode.dao.UtenteDAO;
import it.epicode.entity.Abbonamento;
import it.epicode.entity.Biglietto;

import it.epicode.entity.Utente;
import it.epicode.enums.Periodicita;
import it.epicode.enums.TipoDiRuolo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class MainCreate {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("it"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
        EntityManager em = emf.createEntityManager();

        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
        PreSetDAO preSetDAO = new PreSetDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);


        for (int i = 0; i < 5; i++) {
            Utente utente = new Utente();
            utente.setNome(faker.name().fullName());
            utente.setCognome(faker.name().lastName());
            utente.setTipoDiRuolo(TipoDiRuolo.CLIENTE);
            utenteDAO.save(utente);
        }

        Utente utente = new Utente();
        utente.setNome("Mario");
        utente.setCognome("Rossi");
        utente.setTipoDiRuolo(TipoDiRuolo.AMMINISTRATORE);
        utenteDAO.save(utente);

        List<Utente> utenti = utenteDAO.findAll();


        for (Utente u : utenti) {
            Abbonamento abbonamento = new Abbonamento();
            abbonamento.setDataEmissione(LocalDate.now());
            abbonamento.setDataScadenza(LocalDate.now().plusMonths(faker.number().numberBetween(1, 12)));
            abbonamento.setPeriodicita(faker.options().option(Periodicita.class));
            abbonamento.setTessera(u.getTessera());
            abbonamentoDAO.save(abbonamento);


            for (int i = 0; i < faker.number().numberBetween(1, 5); i++) {
                Biglietto biglietto = new Biglietto();
                biglietto.setDataEmissione(LocalDate.now());
                biglietto.setDataScadenza(LocalDate.now().plusDays(faker.number().numberBetween(1, 30)));
                biglietto.setValidita(faker.bool().bool());
                biglietto.setTessera(u.getTessera());
                bigliettoDAO.save(biglietto);
                bigliettoDAO.save(biglietto);
            }
        }

        em.close();


    }
}