package it.epicode.entity;

import it.epicode.enums.TipoDiRuolo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name ="tipo_user", discriminatorType = DiscriminatorType.STRING)
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

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "tessera_id")
    private Tessera tessera;

    @Enumerated
    @Column(name = "tipo_di_ruolo")
    private TipoDiRuolo tipoDiRuolo;



}
