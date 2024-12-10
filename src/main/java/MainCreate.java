import com.github.javafaker.Faker;
import it.epicode.dao.*;
import it.epicode.entity.*;

import it.epicode.enums.*;
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

        MezzoDAO mezzoDAO = new MezzoDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        PuntoEmissioneDAO puntoEmissioneDAO = new PuntoEmissioneDAO(em);
        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
        PreSetDAO preSetDAO = new PreSetDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        TesseraDAO tesseraDAO = new TesseraDAO(em);

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




        for (int i = 0; i < 5; i++) {
            Mezzo mezzo = new Mezzo();
            mezzo.setTipo(faker.options().option(TipoMezzo.class));
            mezzo.setCapienza(faker.number().numberBetween(15,50));
            mezzo.setStato(faker.options().option(StatoMezzo.class));
            mezzo.setDataInizio(LocalDate.now().plusDays(faker.number().randomNumber()));
            mezzo.setDataFine(LocalDate.now().plusDays(faker.number().randomNumber()));

            mezzoDAO.save(mezzo);

            Tratta tratta = new Tratta();
            tratta.setDurata(faker.number().numberBetween(12,344));
            tratta.setArrivo(faker.country().capital());
            tratta.setPartenza(faker.country().capital());

            trattaDAO.save(tratta);


            PuntoEmissione puntoEmissione = new PuntoEmissione();
            puntoEmissione.setTipo(faker.options().option(TipoPuntoEmissione.class));
            puntoEmissione.setDisponibile(faker.options().option(StatoMezzo.class));

            puntoEmissioneDAO.save(puntoEmissione);

        }

        List<Utente> utenti = utenteDAO.findAll();


        List<PuntoEmissione> puntiEmissione = puntoEmissioneDAO.findAll();
        List<Mezzo> mezzi = mezzoDAO.findAll();
        List<Tratta> tratte = trattaDAO.findAll();

        for (Utente u : utenti) {
            Abbonamento abbonamento = new Abbonamento();
            abbonamento.setDataEmissione(LocalDate.now());
            abbonamento.setDataScadenza(LocalDate.now().plusMonths(faker.number().numberBetween(1, 12)));
            abbonamento.setPeriodicita(faker.options().option(Periodicita.class));
            abbonamento.setTessera(u.getTessera());

            if (!puntiEmissione.isEmpty()) {
                abbonamento.setPuntoEmissione(faker.options().nextElement(puntiEmissione));
            }

            abbonamentoDAO.save(abbonamento);

            for (int i = 0; i < faker.number().numberBetween(1, 5); i++) {
                Biglietto biglietto = new Biglietto();
                biglietto.setDataEmissione(LocalDate.now());
                biglietto.setValidita(faker.bool().bool());
                biglietto.setTessera(u.getTessera());

                if (!mezzi.isEmpty()) {
                    biglietto.setMezzo(faker.options().nextElement(mezzi));
                }

                if (!tratte.isEmpty()) {
                    biglietto.setTratta(faker.options().nextElement(tratte));
                }

                if (!puntiEmissione.isEmpty()) {
                    biglietto.setPuntoEmissione(faker.options().nextElement(puntiEmissione));
                }

                bigliettoDAO.save(biglietto);
            }
        }


        em.close();


    }
}