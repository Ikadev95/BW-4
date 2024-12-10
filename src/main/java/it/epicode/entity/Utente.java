package it.epicode.entity;

import it.epicode.enums.TipoDiRuolo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@NamedQuery(name = "Trova_tutto_Utente", query = "SELECT u FROM Utente u")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;


    @Column(name = "nome", nullable = false, length = 50)
    private String nome;


    @Column(name = "cognome", nullable = false, length = 50)
    private String cognome;

    @OneToOne(mappedBy = "clienteId", cascade = CascadeType.ALL)
    private Tessera tessera;

    @Enumerated
    @Column(name = "tipo_di_ruolo")
    private TipoDiRuolo tipoDiRuolo;

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", tessera=" + tessera.getId() +
                ", tipoDiRuolo=" + tipoDiRuolo +
                '}';
    }
}
