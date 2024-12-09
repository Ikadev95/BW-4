package it.epicode.entity;

import it.epicode.enums.TipoDiRuolo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    @Getter
    @Setter
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Getter
    @Setter
    @Column(name = "cognome", nullable = false, length = 50)
    private String cognome;

    @OneToOne(mappedBy = "clienteId", cascade = CascadeType.ALL)
    private Tessera tessera;

    @Enumerated
    @Column(name = "tipo_di_ruolo")
    private TipoDiRuolo tipoDiRuolo;



}
