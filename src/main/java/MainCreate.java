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
        ConteggioTrattaDAO conteggioTrattaDAO = new ConteggioTrattaDAO(em);

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
            puntoEmissione.setNome(faker.company().name());
            puntoEmissioneDAO.save(puntoEmissione);

        }

        List<Mezzo> mezzi = mezzoDAO.findAll();

// Prima tratta
        Tratta trattaBuild1 = new Tratta();
        trattaBuild1.setNome("roma milano");
        trattaBuild1.setPartenza("Roma");
        trattaBuild1.setArrivo("Milano");
        trattaDAO.save(trattaBuild1);

        ConteggioTratta trattaConteggio1 = new ConteggioTratta();
        trattaConteggio1.setMezzo(mezzi.get(0)); // Mezzo 1
        trattaConteggio1.setDurata(180);
        trattaConteggio1.setTratta(trattaBuild1);
        conteggioTrattaDAO.save(trattaConteggio1);

// Seconda tratta
        Tratta trattaBuild2 = new Tratta();
        trattaBuild2.setNome("napoli torino");
        trattaBuild2.setPartenza("Napoli");
        trattaBuild2.setArrivo("Torino");
        trattaDAO.save(trattaBuild2);

        ConteggioTratta trattaConteggio2 = new ConteggioTratta();
        trattaConteggio2.setMezzo(mezzi.get(1)); // Mezzo 2
        trattaConteggio2.setDurata(240);
        trattaConteggio2.setTratta(trattaBuild2);
        conteggioTrattaDAO.save(trattaConteggio2);

// Terza tratta
        Tratta trattaBuild3 = new Tratta();
        trattaBuild3.setNome("genova bologna");
        trattaBuild3.setPartenza("Genova");
        trattaBuild3.setArrivo("Bologna");
        trattaDAO.save(trattaBuild3);

        ConteggioTratta trattaConteggio3 = new ConteggioTratta();
        trattaConteggio3.setMezzo(mezzi.get(2)); // Mezzo 3
        trattaConteggio3.setDurata(120);
        trattaConteggio3.setTratta(trattaBuild3);
        conteggioTrattaDAO.save(trattaConteggio3);

// Quarta tratta
        Tratta trattaBuild4 = new Tratta();
        trattaBuild4.setNome("cagliari palermo");
        trattaBuild4.setPartenza("Cagliari");
        trattaBuild4.setArrivo("Palermo");
        trattaDAO.save(trattaBuild4);

        ConteggioTratta trattaConteggio4 = new ConteggioTratta();
        trattaConteggio4.setMezzo(mezzi.get(3)); // Mezzo 4
        trattaConteggio4.setDurata(300);
        trattaConteggio4.setTratta(trattaBuild4);
        conteggioTrattaDAO.save(trattaConteggio4);

// Quinta tratta
        Tratta trattaBuild5 = new Tratta();
        trattaBuild5.setNome("venezia firenze");
        trattaBuild5.setPartenza("Venezia");
        trattaBuild5.setArrivo("Firenze");
        trattaDAO.save(trattaBuild5);

        ConteggioTratta trattaConteggio5 = new ConteggioTratta();
        trattaConteggio5.setMezzo(mezzi.get(4)); // Mezzo 5
        trattaConteggio5.setDurata(150);
        trattaConteggio5.setTratta(trattaBuild5);
        conteggioTrattaDAO.save(trattaConteggio5);

// Sesta tratta
        Tratta trattaBuild6 = new Tratta();
        trattaBuild6.setNome("trieste bari");
        trattaBuild6.setPartenza("Trieste");
        trattaBuild6.setArrivo("Bari");
        trattaDAO.save(trattaBuild6);

        ConteggioTratta trattaConteggio6 = new ConteggioTratta();
        trattaConteggio6.setMezzo(mezzi.get(5)); // Mezzo 6
        trattaConteggio6.setDurata(420);
        trattaConteggio6.setTratta(trattaBuild6);
        conteggioTrattaDAO.save(trattaConteggio6);


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