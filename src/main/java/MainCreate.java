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


        for (int i = 0; i < 7; i++) {
            LocalDate startDate =  LocalDate.now().minusMonths(faker.number().numberBetween(1, 4));
            Mezzo mezzo = new Mezzo();
            mezzo.setTipo(faker.options().option(TipoMezzo.class));
            mezzo.setCapienza(faker.number().numberBetween(15,50));
            mezzo.setStato(faker.options().option(StatoMezzo.class));
            mezzo.setDataInizio(startDate);
            mezzo.setDataFine(startDate.plusMonths(faker.number().numberBetween(1, 4)));
            mezzo.setNumManutenzioni(faker.number().numberBetween(1 , 3));
            mezzoDAO.save(mezzo);


            PuntoEmissione puntoEmissione = new PuntoEmissione();
            puntoEmissione.setTipo(faker.options().option(TipoPuntoEmissione.class));
            puntoEmissione.setDisponibile(faker.options().option(StatoMezzo.class));

            puntoEmissioneDAO.save(puntoEmissione);

        }

        List<Mezzo> mezzi = mezzoDAO.findAll();


        Tratta tratta1 = new Tratta();
        tratta1.setNome("roma milano");
        tratta1.setPartenza("Roma");
        tratta1.setArrivo("Milano");
        tratta1.setMezzo(mezzi.get(0)); // Associare il mezzo appropriato
        tratta1.setDurata(180);
        trattaDAO.save(tratta1);

        Tratta tratta2 = new Tratta();
        tratta2.setNome("napoli torino");
        tratta2.setPartenza("Napoli");
        tratta2.setArrivo("Torino");
        tratta2.setMezzo(mezzi.get(1)); // Associare il mezzo appropriato
        tratta2.setDurata(240);
        trattaDAO.save(tratta2);

        Tratta tratta3 = new Tratta();
        tratta3.setNome("venezia firenze");
        tratta3.setPartenza("Venezia");
        tratta3.setArrivo("Firenze");
        tratta3.setMezzo(mezzi.get(3)); // Associare il mezzo appropriato
        tratta3.setDurata(150);
        trattaDAO.save(tratta3);

        Tratta tratta4 = new Tratta();
        tratta4.setNome("genova bologna");
        tratta4.setPartenza("Genova");
        tratta4.setArrivo("Bologna");
        tratta4.setMezzo(mezzi.get(4)); // Associare il mezzo appropriato
        tratta4.setDurata(120);
        trattaDAO.save(tratta4);

        Tratta tratta5 = new Tratta();
        tratta5.setNome("cagliari palermo");
        tratta5.setPartenza("Cagliari");
        tratta5.setArrivo("Palermo");
        tratta5.setMezzo(mezzi.get(5)); // Associare il mezzo appropriato
        tratta5.setDurata(300);
        trattaDAO.save(tratta5);

        Tratta tratta6 = new Tratta();
        tratta6.setNome("trieste bari");
        tratta6.setPartenza("Trieste");
        tratta6.setArrivo("Bari");
        tratta6.setMezzo(mezzi.get(6)); // Associare il mezzo appropriato
        tratta6.setDurata(420);
        trattaDAO.save(tratta6);


        List<Utente> utenti = utenteDAO.findAll();


        List<PuntoEmissione> puntiEmissione = puntoEmissioneDAO.findAll();
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

            if (!mezzi.isEmpty()) {
                Mezzo mezzo = faker.options().nextElement(mezzi);
                abbonamento.setMezzo(mezzo);
            }

            if (!tratte.isEmpty()) {
                abbonamento.setTratta(faker.options().nextElement(tratte));
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