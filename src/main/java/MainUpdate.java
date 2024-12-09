import com.github.javafaker.Faker;
import it.epicode.dao.UtenteDAO;
import it.epicode.enums.TipoDiRuolo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Locale;
import java.util.Scanner;

public class MainUpdate {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("it"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
        EntityManager em = emf.createEntityManager();

        UtenteDAO utenteDAO = new UtenteDAO(em);


        System.out.println("LogIn - inserisci il tuo id utente");
        Long id = scanner.nextLong();

        if (utenteDAO.findById(id).getTipoDiRuolo() != TipoDiRuolo.AMMINISTRATORE) {

        } else {

            boolean continua = true;

            while (continua) {
                stampaMenuAmm();
                System.out.println("inserisci input: ");
                int scelta = scanner.nextInt();

                switch (scelta) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

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



}