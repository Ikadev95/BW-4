package it.epicode.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_biglietto", discriminatorType = DiscriminatorType.STRING)
@NamedQuery(name="Trova_tutto_PreSet ", query="SELECT a FROM PreSet  a")

public abstract class PreSet  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Getter
    @Setter
    @Column(name = "data_emissione")
    private LocalDate dataEmissione;

    @Getter
    @Setter
    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "punto_emissione")
    private PuntoEmissione puntoEmissione;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "tratta_id")
    private Tratta tratta;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "tessera_id")
    private Tessera tessera;

}
