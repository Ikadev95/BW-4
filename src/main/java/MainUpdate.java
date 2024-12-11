import com.github.javafaker.Faker;
import it.epicode.dao.*;
import it.epicode.entity.*;
import it.epicode.enums.Periodicita;
import it.epicode.enums.StatoMezzo;
import it.epicode.enums.TipoDiRuolo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class MainUpdate {

    static Scanner scanner = new Scanner(System.in);
   // private static final Logger LOGGER = LoggerFactory.getLogger(MainUpdate.class);
    static Utente utenteCor;

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
    static EntityManager em = emf.createEntityManager();
    static MezzoDAO mezzoDAO = new MezzoDAO(em);
    static BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
    static AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
    static TrattaDAO trattaDAO = new TrattaDAO(em);
    static PuntoEmissioneDAO puntoEmissioneDAO = new PuntoEmissioneDAO(em);
    static TesseraDAO tesseraDAO = new TesseraDAO(em);
    static PreSetDAO preSetDAO = new PreSetDAO(em);

    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("it"));


        UtenteDAO utenteDAO = new UtenteDAO(em);



        System.out.println("LogIn - inserisci il tuo id utente");
        Long id = scanner.nextLong();
        Utente utenteCorrente = utenteDAO.findById(id);
        utenteCor = utenteCorrente;

            boolean continua = true;

        if (utenteCorrente.getTipoDiRuolo() != TipoDiRuolo.AMMINISTRATORE) {


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
                        verificaAbbonamento();
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
                        rinnovoTessera();
                        break;
                    case 8:
                        trovaBigliettiAttivi();
                        break;
                    case 9:
                        trovaBigliettiScaduti();
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
                        verificaAbbonamento();
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
                        controlloMezzo();
                        break;
                    case 8:
                        rinnovoTessera();
                        break;
                    case 9:
                        trovaBigliettiAttivi();
                        break;
                    case 10:
                        trovaBigliettiScaduti();
                        break;
                    case 11:
                        visualizzaPerPeriodo();
                        break;
                    case 12:
                        visualizzaBiglAbbPerPuntoDiEmissione();
                        break;
                    case 13:
                        groupedByMezzo();
                        break;
                    case 14:
                        creaTratta();
                        break;
                    case 15:
                        eliminaTratta();
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
            System.out.println("6. Visualizza punti vendita");
            System.out.println("7. Controllo mezzo");
            System.out.println("8. Rinnovo tessera");
            System.out.println("9. Visualizza biglietti attivi");
            System.out.println("10. Visualizza biglietti scaduti");
            System.out.println("11. Visualizza biglietti,abbonamenti per un periodo");
            System.out.println("12. Visualizza biglietti,abbonamenti per punto emissione");
            System.out.println("13. Visualizza biglietti,abbonamenti per mezzo");
            System.out.println("14. Crea tratta");
            System.out.println("15. Gestisci tratta");


            System.out.println("0. Esci");
        }

        public static void stampaMenuCli() {
            System.out.println("\n--- Menu Catalogo ---");
            System.out.println("1. Acquista biglietto");
            System.out.println("2. Acquista abbonamento");
            System.out.println("3. Verifica abbonamento");
            System.out.println("4. Visualizza mezzi");
            System.out.println("5. Visualizza tratte");
            System.out.println("6. Visualizza punti vendita");
            System.out.println("7. Rinnovo tessera");
            System.out.println("8. Visualizza biglietti attivi");
            System.out.println("9. Visualizza biglietti scaduti");
            System.out.println("0. Esci");
        }


        public static void creaBiglietto() {

        try {
            Biglietto biglietto = new Biglietto();

//          Selezione punto vendita
            System.out.println("inserisci ID punto di emissione tra quelli disponibili:");
            System.out.println(puntoEmissioneDAO.findAll());
            Long idPunto = scanner.nextLong();
            biglietto.setPuntoEmissione(puntoEmissioneDAO.findById(idPunto));

//

//          Selezione tratta
            System.out.println("inserisci nome tratta tra quelli disponibili:");
            System.out.println(trattaDAO.findAll());
            scanner.nextLine();
            String tratta = scanner.nextLine();
            biglietto.setTratta(trattaDAO.getTrattaByName(tratta));

            Mezzo trattaMezzo = trattaDAO.getTrattaByName(tratta).getMezzo();
            biglietto.setMezzo(trattaMezzo);


//          Set data emissione e utente
            biglietto.setDataEmissione(LocalDate.now());
            biglietto.setTessera(utenteCor.getTessera());

            biglietto.setValidita(true);


            bigliettoDAO.save(biglietto);

            Tessera tesseraCor = utenteCor.getTessera();
            tesseraCor.getListaBiglietti().add(biglietto);

            em.merge(tesseraCor);
            System.out.println("Biglietto creato con sucesso.");
        }
        catch (InputMismatchException e) {
            System.out.println("Errore: Input non valido. Assicurati di inserire il dato corretto.");
            scanner.nextLine();
        }
        catch (Exception e) {
            System.out.println("Errore durante la creazione del biglietto: " + e.getMessage());
        }
        }

    public static void creaAbbonamento() {
        try {
            Abbonamento abbonamento = new Abbonamento();

//          Selezione punto vendita
            System.out.println("inserisci ID punto di emissione tra quelli disponibili:");
            System.out.println(puntoEmissioneDAO.findAll());
            Long idPunto = scanner.nextLong();
            abbonamento.setPuntoEmissione(puntoEmissioneDAO.findById(idPunto));

            //          Selezione tratta
            System.out.println("inverisci ID tratta tra quelli disponibili:");
            System.out.println(trattaDAO.findAll());
            String tratta = scanner.nextLine();
            abbonamento.setTratta(trattaDAO.getTrattaByName(tratta));

//          Selezione mezzo
            System.out.println("inverisci ID mezzo tra quelli disponibili:");
            visulizzaTuttiMezzi();
            scanner.nextLine();
            String idMezzo = scanner.nextLine();
            abbonamento.setMezzo(mezzoDAO.findById(idMezzo));


//      Set data emissione e utente
            abbonamento.setDataEmissione(LocalDate.now());
            abbonamento.setTessera(utenteCor.getTessera());

//      Selezione periodicita
            System.out.println("Inserisci periodicità: SETTIMANALE, MENSILE, SEMESTRALE");
            Periodicita periodicita = Periodicita.valueOf(scanner.next().toUpperCase());
            abbonamento.setPeriodicita(periodicita);

            if (periodicita == Periodicita.SETTIMANALE) {
                abbonamento.setDataScadenza(LocalDate.now().plusWeeks(1));
            } else if (periodicita == Periodicita.MENSILE) {
                abbonamento.setDataScadenza(LocalDate.now().plusMonths(1));
            } else {
                abbonamento.setDataScadenza(LocalDate.now().plusYears(1));
            }


            abbonamentoDAO.save(abbonamento);

            Tessera tesseraCor = utenteCor.getTessera();
            tesseraCor.getListaAbbonamenti().add(abbonamento);
            em.merge(tesseraCor);
            System.out.println("Abbonamento creato con successo.");
        }
        catch (InputMismatchException e) {
            System.out.println("Errore: Input non valido. Assicurati di inserire il dato corretto.");
            scanner.nextLine();
        }
        catch (Exception e) {
            System.out.println("Errore durante la creazione dell'abbonamento." + e.getMessage());
        }

    }

        public static void visulizzaTuttiMezzi() {
        try {
            System.out.println("Mezzi disponibili: ");
            List<Mezzo> mezziDisponibili = mezzoDAO.findAll();
            mezziDisponibili.forEach(mezzo -> {
                String msg = "ID: " + mezzo.getId() + ", tipo: " + mezzo.getTipo() + ", stato: " + mezzo.getStato() + ", capienza: " + mezzo.getCapienza();

                if (mezzo.getStato() == StatoMezzo.MANUTENZIONE) {
                    System.out.println(msg + " In manutenzione da: " + mezzo.getDataInizio() + " fino a: " + mezzo.getDataFine());
                }
                System.out.println(msg);

            });
        } catch (Exception e) {
            System.out.println("Errore durante l'operazione: " + e.getMessage());
        }
        }

        public static void visulizzaTuttiTratte() {
        try {
            List<Tratta> tratte = trattaDAO.findAll();
            for (Tratta tratta : tratte) {
                System.out.println(tratta);
            }
        } catch (Exception e) {
            System.out.println("Errore durante l'operazione: " + e.getMessage());
        }
        }

        public static void visulizzaTuttiPuntiVendita() {
        try{
            List<PuntoEmissione> punti = puntoEmissioneDAO.findAll();
            for (PuntoEmissione punto : punti) {
                System.out.println(punto);
            }
        } catch (Exception e) {
            System.out.println("Errore durante l'operazione: " + e.getMessage());
        }
        }

        public static void verificaAbbonamento() {
        try {
            System.out.println("inserici ID abbonamento");
            Abbonamento abbDaVerifica = abbonamentoDAO.findById(scanner.nextLong());
            if (LocalDate.now().isAfter(abbDaVerifica.getDataScadenza())) {
                System.out.println("l'abbonamento è scaduto");
            } else {
                System.out.println("abbonamento ancora in corso di validità");
            }
        }
        catch (Exception e) {
            System.out.println("Errore durante l'operazione: " + e.getMessage());
        }
        }


    public static void trovaBigliettiAttivi() {
        try {
            List<Biglietto> biglietti = bigliettoDAO.findActive();
            if (biglietti != null && !biglietti.isEmpty()) {
                for (Biglietto biglietto : biglietti) {
                    System.out.println(biglietto);
                }
            } else {
                System.out.println("Errore: non ci sono biglietti attivi.");
            }
        } catch (Exception e) {
            System.out.println("Errore durante l'operazione: " + e.getMessage());
        }
    }

    public static void trovaBigliettiScaduti() {
        try {
            List<Biglietto> biglietti = bigliettoDAO.findExpired();
            if (biglietti != null && !biglietti.isEmpty()) {
                for (Biglietto biglietto : biglietti) {
                    System.out.println(biglietto);
                }
            } else {
                System.out.println("Errore: non ci sono biglietti scaduti.");
            }
        }
        catch (Exception e) {
            System.out.println("Errore durante l'operazione: " + e.getMessage());
        }
    }

    public static void controlloMezzo() {
        try {
            System.out.println("Inserisci l'ID del mezzo da controllare:");
            scanner.nextLine();
            String idMezzo = scanner.nextLine();

            Mezzo mezzo = mezzoDAO.findById(idMezzo);
            if (mezzo != null) {
                System.out.println("Mezzo trovato:");
                System.out.println(mezzo);

                System.out.println("Vuoi aggiornare lo stato del mezzo? (Sì: 1 / No: 0)");
                int scelta = scanner.nextInt();
                scanner.nextLine();

                if (scelta == 1) {
                    System.out.println("Inserisci il nuovo stato del mezzo: (SERVIZIO, MANUTENZIONE)");
                    String nuovoStato = scanner.nextLine().toUpperCase();

                    try {
                        StatoMezzo statoMezzo = StatoMezzo.valueOf(nuovoStato);
                        mezzo.setStato(statoMezzo);

                        mezzoDAO.update(mezzo);
                        System.out.println("Stato del mezzo aggiornato correttamente a: " + statoMezzo);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Errore: Stato non valido. Operazione annullata.");
                    }
                } else if (scelta == 0) {
                    System.out.println("Nessuna modifica apportata al mezzo.");
                } else {
                    System.out.println("Scelta non valida. Nessuna modifica.");
                }
            } else {
                System.out.println("Errore: Nessun mezzo trovato con l'ID specificato.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Errore: Input non valido. Assicurati di inserire il dato corretto.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Errore durante l'operazione: " + e.getMessage());
        }
    }

    public static void rinnovoTessera() {
        try{
            System.out.println("Inserisci l'ID della tessera da rinnovare:");
            scanner.nextLine();
            String idTessera = scanner.nextLine();
            Tessera tessera = tesseraDAO.findById(idTessera);

            if (tessera == null) {
                System.out.println("Errore: Nessuna tessera trovata con l'ID specificato.");
                return;
            }

            System.out.println("Tessera trovata:");
            System.out.println("Data di scadenza attuale: " + tessera.getDataScadenza());

            if (tessera.getDataScadenza().isBefore(LocalDate.now())) {
                System.out.println("La tessera è scaduta. È necessario rinnovarla. (Si: 1, No: 0 )");
                int scelta = scanner.nextInt();
            } else {
                System.out.println("La tessera è ancora valida. Vuoi comunque rinnovarla? (Sì: 1 / No: 0)");
                int scelta = scanner.nextInt();

                if (scelta != 1) {
                    System.out.println("Operazione annullata.");
                    return;
                }
            }
            tessera.setDataScadenza(LocalDate.now().plusYears(2));
            tesseraDAO.update(tessera);

            System.out.println("Tessera rinnovata con successo! Nuova data di scadenza: " + tessera.getDataScadenza());
        }
        catch (InputMismatchException e) {
            System.out.println("Errore: Input non valido. Assicurati di inserire il dato corretto.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Errore durante l'operazione: " + e.getMessage());
        }

    }

    public static void visualizzaBiglAbbPerPuntoDiEmissione() {

        try{
        System.out.println("insersci ID del punto vendita");
        visulizzaTuttiPuntiVendita();
        int idPunto = scanner.nextInt();
        System.out.println(preSetDAO.getPreSetbyPunto(idPunto));
        }
        catch (InputMismatchException e) {
            System.out.println("Errore: Input non valido. Assicurati di inserire il dato corretto.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Errore durante l'operazione: " + e.getMessage());
        }
    }


    public static void groupedByMezzo() {
        try {
            System.out.println("Raggruppamento per Mezzo:");
            List<PreSet> presets = preSetDAO.getOrderedByMezzo();

            Mezzo currentMezzo = null;
            for (PreSet preset : presets) {
                if (preset.getMezzo() == null) {
                    System.out.println("Mezzo non specificato:");
                } else if (!preset.getMezzo().equals(currentMezzo)) {
                    currentMezzo = preset.getMezzo();
                    System.out.println("Mezzo: " + currentMezzo.getTipo() + " " + currentMezzo.getId());
                }
                System.out.println(" - Ticket ID: " + preset.getId() + ", Tessera utente: " + preset.getTessera());
            }
        }
        catch (Exception e) {
            System.out.println("Errore durante l'operazione: " + e.getMessage());
        }
}

    public static void visualizzaPerPeriodo() {
        try {
            System.out.println("Inserisci data di inizio: ");
            LocalDate dataInizio = LocalDate.parse(scanner.next());
            System.out.println("Inserisci data di fine: ");
            LocalDate dataFine = LocalDate.parse(scanner.next());

            System.out.println("cosa vuoi visualizzare? (1.Biglietti, 2.Abbonamenti)");
            int scelta = scanner.nextInt();

            if (scelta == 1) {
                List<Biglietto> biglietti = em.createQuery("SELECT b FROM Biglietto b WHERE b.dataEmissione BETWEEN :dataInizio AND :dataFine", Biglietto.class)
                        .setParameter("dataInizio", dataInizio)
                        .setParameter("dataFine", dataFine)
                        .getResultList();
                System.out.println("Biglietti trovati: ");
                biglietti.forEach(System.out::println);
            } else if (scelta == 2) {
                List<Abbonamento> abbonamenti = em.createQuery("SELECT a FROM Abbonamento a WHERE a.dataEmissione BETWEEN :dataInizio AND :dataFine", Abbonamento.class)
                        .setParameter("dataInizio", dataInizio)
                        .setParameter("dataFine", dataFine)
                        .getResultList();
                System.out.println("Abbonamenti trovati: ");
                abbonamenti.forEach(System.out::println);
            } else {
                System.out.println("Scelta non valida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Errore: Input non valido. Assicurati di inserire il dato corretto.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Errore durante l'operazione: " + e.getMessage());
        }
    }

    public static void creaTratta() {
        try {
            Tratta tratta = new Tratta();
            System.out.println("Inserisci nome tratta");
            scanner.nextLine();
            tratta.setNome(scanner.nextLine());

            System.out.println("Inserisci stazione di partenza");
            tratta.setPartenza(scanner.next());


            System.out.println("Inserisci stazione di arrivo");
            tratta.setArrivo(scanner.next());


            System.out.println("Seleziona mezzo per la tratta");
            visulizzaTuttiMezzi();
            Mezzo mezzo = mezzoDAO.findById(scanner.nextLine());
            tratta.setMezzo(mezzo);

            System.out.println("Inserisci durata");
            tratta.setDurata(scanner.nextInt());

            System.out.println("Tratta creata con successo!😁");
            trattaDAO.save(tratta);
        } catch (InputMismatchException e) {
            System.out.println("Errore: Input non valido. Assicurati di inserire il dato corretto.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Errore durante l'operazione: " + e.getMessage());
        }
    }

    public static void eliminaTratta() {

        try {
            System.out.println("Seleziona tratta da eliminare");
            System.out.println(trattaDAO.findAll());
            scanner.nextLine();
            String tratta = scanner.nextLine();

            trattaDAO.delete(trattaDAO.getTrattaByName(tratta));
            System.out.println("Tratta eliminata con successo!👌");
        }
        catch (InputMismatchException e) {
            System.out.println("Errore: Input non valido. Assicurati di inserire il dato corretto.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Errore durante l'operazione: " + e.getMessage());
        }

    }

}