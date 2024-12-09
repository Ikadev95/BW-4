import com.github.javafaker.Faker;
import it.epicode.dao.AbbonamentoDAO;
import it.epicode.dao.BigliettoDAO;
import it.epicode.dao.PreSetDAO;
import it.epicode.dao.UtenteDAO;
import it.epicode.entity.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
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


        List<Long> idTessere = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Utente utente = new Utente();
            utente.setNome(faker.name().fullName());
            utente.setCognome(faker.name().lastName());
            utente.setTessera((long) faker.number().numberBetween(1, 100));


        }







    }
}