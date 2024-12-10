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
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MainUpdate {

    static Scanner scanner = new Scanner(System.in);
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

                        break;
                    case 12:

                        break;
                    case 13:
                        groupedByMezzo();
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
            Biglietto biglietto = new Biglietto();

//          Selezione punto vendita
            System.out.println("inserisci ID punto di emissione tra quelli disponibili:");
            System.out.println(puntoEmissioneDAO.findAll());
            Long idPunto = scanner.nextLong();
            biglietto.setPuntoEmissione(puntoEmissioneDAO.findById(idPunto));

//          Selezione mezzo
            System.out.println("inverisci ID mezzo tra quelli disponibili:");
            visulizzaTuttiMezzi();
            Long idMezzo = scanner.nextLong();
            biglietto.setMezzo(mezzoDAO.findById(idMezzo));

//          Selezione tratta
            System.out.println("inverisci ID tratta tra quelli disponibili:");
            System.out.println(trattaDAO.findAll());
            Long idtratta = scanner.nextLong();
            biglietto.setTratta(trattaDAO.findById(idtratta));


//          Set data emissione e utente
            biglietto.setDataEmissione(LocalDate.now());
            biglietto.setTessera(utenteCor.getTessera());

            biglietto.setValidita(true);


            bigliettoDAO.save(biglietto);

            Tessera tesseraCor = utenteCor.getTessera();
            tesseraCor.getListaBiglietti().add(biglietto);

            em.merge(tesseraCor);
        }

    public static void creaAbbonamento() {
        Abbonamento abbonamento = new Abbonamento();

//          Selezione punto vendita
        System.out.println("inserisci ID punto di emissione tra quelli disponibili:");
        System.out.println(puntoEmissioneDAO.findAll());
        Long idPunto = scanner.nextLong();
        abbonamento.setPuntoEmissione(puntoEmissioneDAO.findById(idPunto));

//          Selezione mezzo
        System.out.println("inverisci ID mezzo tra quelli disponibili:");
        visulizzaTuttiMezzi();
        Long idMezzo = scanner.nextLong();
        abbonamento.setMezzo(mezzoDAO.findById(idMezzo));

//          Selezione tratta
        System.out.println("inverisci ID tratta tra quelli disponibili:");
        System.out.println(trattaDAO.findAll());
        Long idtratta = scanner.nextLong();
        abbonamento.setTratta(trattaDAO.findById(idtratta));


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

        public static void verificaAbbonamento() {
            System.out.println("inserici ID abbonamento");
            Abbonamento abbDaVerifica = abbonamentoDAO.findById(scanner.nextLong());
            if (LocalDate.now().isAfter(abbDaVerifica.getDataScadenza())) {
                System.out.println("l'abbonamento è scaduto");
            } else {
                System.out.println("abbonamento ancora in corso di validita");
            }

        }


        public static void trovaBigliettiAttivi (){
        if(bigliettoDAO.findActive() != null){
            System.out.println(bigliettoDAO.findActive());
        }
        else System.out.println("nulla");

        }

        public static void trovaBigliettiScaduti (){
            System.out.println(bigliettoDAO.findExpired());
        }

    public static void controlloMezzo() {
        System.out.println("Inserisci l'ID del mezzo da controllare:");
        Long idMezzo = scanner.nextLong();


        Mezzo mezzo = mezzoDAO.findById(idMezzo);

        if (mezzo == null) {
            System.out.println("Errore: Nessun mezzo trovato con l'ID specificato.");
            return;
        }


        System.out.println("Mezzo trovato:");
        System.out.println(mezzo);
        System.out.println("Stato attuale: " + mezzo.getStato());


        System.out.println("Vuoi aggiornare lo stato del mezzo? (Sì: 1 / No: 0)");
        int scelta = scanner.nextInt();

        if (scelta == 1) {
            System.out.println("Inserisci il nuovo stato del mezzo: (IN_SERVIZIO, FUORI_SERVIZIO, MANUTENZIONE)");
            String nuovoStato = scanner.next().toUpperCase();

            try {
                StatoMezzo statoMezzo = StatoMezzo.valueOf(nuovoStato);
                mezzo.setStato(statoMezzo);


                mezzoDAO.update(mezzo);
                System.out.println("Stato del mezzo aggiornato correttamente a: " + statoMezzo);
            } catch (IllegalArgumentException e) {
                System.out.println("Errore: Stato non valido. Operazione annullata.");
            }
        } else {
            System.out.println("Nessuna modifica apportata al mezzo.");
        }
    }

    public static void rinnovoTessera() {
        System.out.println("Inserisci l'ID della tessera da rinnovare:");
        Long idTessera = scanner.nextLong();


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


        tessera.setDataScadenza(LocalDate.now().plusYears(1));
        tesseraDAO.update(tessera);

        System.out.println("Tessera rinnovata con successo! Nuova data di scadenza: " + tessera.getDataScadenza());
    }

    public static void groupedByMezzo() {
        List<PreSet> presets = preSetDAO.getOrderedByMezzo();
        System.out.println("Raggruppamento per Mezzo:");

        Mezzo currentMezzo = null;
        for (PreSet preset : presets) {
            if (preset.getMezzo() == null) {
                System.out.println("Mezzo non specificato:");
            } else if (!preset.getMezzo().equals(currentMezzo)) {
                currentMezzo = preset.getMezzo();
                System.out.println("Mezzo: " + currentMezzo.getTipo() + " " + currentMezzo.getId());
            }
            System.out.println("  - PreSet: " + preset.getId() + ", Tessera utente: " + preset.getTessera());
        }


}


}