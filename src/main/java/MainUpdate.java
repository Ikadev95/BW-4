import com.github.javafaker.Faker;
import it.epicode.dao.*;
import it.epicode.entity.*;
import it.epicode.enums.Periodicita;
import it.epicode.enums.TipoDiRuolo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class MainUpdate {

    static Scanner scanner = new Scanner(System.in);
    static Utente utenteCor;

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
    static EntityManager em = emf.createEntityManager();
    static MezzoDAO mezzoDAO = new MezzoDAO(em);
    static TrattaDAO trattaDAO = new TrattaDAO(em);
    static PuntoEmissioneDAO puntoEmissioneDAO = new PuntoEmissioneDAO(em);

    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("it"));


        UtenteDAO utenteDAO = new UtenteDAO(em);



        System.out.println("LogIn - inserisci il tuo id utente");
        Long id = scanner.nextLong();
        Utente utenteCorrente = utenteDAO.findById(id);
        utenteCor = utenteCorrente;

        if (utenteCorrente.getTipoDiRuolo() != TipoDiRuolo.AMMINISTRATORE) {

            boolean continua = true;

            while (continua) {
                stampaMenuCli();
                System.out.println("inserisci input: ");
                int scelta = scanner.nextInt();

                switch (scelta) {
                    case 1:
                        creaBiglietto();
                        break;
                    case 2:
                        creaAbbonamento();
                        break;
                    case 3:

                        break;
                    case 4:
                        visulizzaTuttiMezzi();
                        break;
                    case 5:
                        visulizzaTuttiTratte();
                        break;
                    case 6:
                        visulizzaTuttiPuntiVendita();
                        break;
                    case 7:

                        break;
                    case 8:

                        break;
                    case 9:

                        break;
                    case 0:
                        continua = false;
                        System.out.println("Uscita dal programma. Arrivederci!");
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            }

        } else {

            boolean continua = true;

            while (continua) {
                stampaMenuAmm();
                System.out.println("inserisci input: ");
                int scelta = scanner.nextInt();

                switch (scelta) {
                    case 1:
                        creaBiglietto();
                        break;
                    case 2:
                        creaAbbonamento();
                        break;
                    case 3:

                        break;
                    case 4:
                        visulizzaTuttiMezzi();
                        break;
                    case 5:
                        visulizzaTuttiTratte();
                        break;
                    case 6:
                        visulizzaTuttiPuntiVendita();
                        break;
                    case 7:

                        break;
                    case 8:

                        break;
                    case 9:

                        break;
                    case 10:

                        break;
                    case 11:

                        break;
                    case 0:
                        continua = false;
                        System.out.println("Uscita dal programma. Arrivederci!");
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            }


        }


    }

        public static void stampaMenuAmm() {
            System.out.println("\n--- Menu Catalogo ---");
            System.out.println("1. Acquista biglietto");
            System.out.println("2. Acquista abbonamento");
            System.out.println("3. Verifica abbonamento");
            System.out.println("4. Visualizza mezzi");
            System.out.println("5. Visualizza tratte");
            System.out.println("6. Controllo mezzo");
            System.out.println("7. Rinnovo tessera");
            System.out.println("8. Visualizza biglietti attivi");
            System.out.println("9. Visualizza biglietti scaduti");
            System.out.println("10. Visualizza biglietti,abbonamenti per un periodo");
            System.out.println("11. Visualizza biglietti,abbonamenti per punto emissione");
            System.out.println("11. Visualizza biglietti,abbonamenti per mezzo");


            System.out.println("0. Esci");
        }

        public static void stampaMenuCli() {
            System.out.println("\n--- Menu Catalogo ---");
            System.out.println("1. Acquista biglietto");
            System.out.println("2. Acquista abbonamento");
            System.out.println("3. Verifica abbonamento");
            System.out.println("4. Visualizza mezzi");
            System.out.println("5. Visualizza tratte");
            System.out.println("7. Rinnovo tessera");
            System.out.println("8. Visualizza biglietti attivi");
            System.out.println("9. Visualizza biglietti scaduti");
            System.out.println("0. Esci");
        }


        public static void creaBiglietto() {
            Biglietto biglietto = new Biglietto();

//          Selezione punto vendita
            System.out.println("inserisci id punto di emissione tra quelli disponibili:");
            System.out.println(puntoEmissioneDAO.findAll());
            Long idPunto = scanner.nextLong();
            biglietto.setPuntoEmissione(puntoEmissioneDAO.findById(idPunto));

//          Selezione mezzo
            System.out.println("inverisci id mezzo tra quelli disponibili:");
            visulizzaTuttiMezzi();
            Long idMezzo = scanner.nextLong();
            biglietto.setMezzo(mezzoDAO.findById(idMezzo));

//          Selezione tratta
            System.out.println("inverisci id tratta tra quelli disponibili:");
            System.out.println(trattaDAO.findAll());
            Long idtratta = scanner.nextLong();
            biglietto.setTratta(trattaDAO.findById(idtratta));


//          Set data emissione e utente
            biglietto.setDataEmissione(LocalDate.now());
            biglietto.setTessera(utenteCor.getTessera());

            biglietto.setValidita(true);

            BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
            bigliettoDAO.save(biglietto);


            em.merge(utenteCor.getTessera().getListaBiglietti().add(biglietto));
        }

    public static void creaAbbonamento() {
        Abbonamento abbonamento = new Abbonamento();

//          Selezione punto vendita
        System.out.println("inserisci id punto di emissione tra quelli disponibili:");
        System.out.println(puntoEmissioneDAO.findAll());
        Long idPunto = scanner.nextLong();
        abbonamento.setPuntoEmissione(puntoEmissioneDAO.findById(idPunto));

//          Selezione mezzo
        System.out.println("inverisci id mezzo tra quelli disponibili:");
        visulizzaTuttiMezzi();
        Long idMezzo = scanner.nextLong();
        abbonamento.setMezzo(mezzoDAO.findById(idMezzo));

//          Selezione tratta
        System.out.println("inverisci id tratta tra quelli disponibili:");
        System.out.println(trattaDAO.findAll());
        Long idtratta = scanner.nextLong();
        abbonamento.setTratta(trattaDAO.findById(idtratta));


//      Set data emissione e utente
        abbonamento.setDataEmissione(LocalDate.now());
        abbonamento.setTessera(utenteCor.getTessera());

//      Selezione periodicita
        System.out.println("Inserisci periodicità: SETTIMANALE, MENSILE, SEMESTRALE");
        Periodicita periodicita = Periodicita.valueOf(scanner.nextLine().toUpperCase());
        abbonamento.setPeriodicita(periodicita);

        if (periodicita == Periodicita.SETTIMANALE) {
            abbonamento.setDataScadenza(LocalDate.now().plusWeeks(1));
        } else if (periodicita == Periodicita.MENSILE) {
            abbonamento.setDataScadenza(LocalDate.now().plusMonths(1));
        } else {
            abbonamento.setDataScadenza(LocalDate.now().plusYears(1));
        }

        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
        abbonamentoDAO.save(abbonamento);

        em.merge(utenteCor.getTessera().getListaAbbonamenti().add(abbonamento));
    }

        public static void visulizzaTuttiMezzi() {
            System.out.println(mezzoDAO.findAll());
        }

        public static void visulizzaTuttiTratte() {
            System.out.println(trattaDAO.findAll());
        }

        public static void visulizzaTuttiPuntiVendita() {
            System.out.println(puntoEmissioneDAO.findAll());
        }
}