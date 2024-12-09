package it.epicode.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_inizio", nullable = false)
    private LocalDate dataInizio;

    @Column(name = "data_fine", nullable = false)
    private LocalDate dataFine;

    @Enumerated(EnumType.STRING)
    private TipoPeriodo tipo;

    public enum TipoPeriodo {
        SERVIZIO,
        MANUTENZIONE
    }


    // @ManyToOne
    // @JoinColumn(name = "mezzo_id")
    // private Mezzo mezzo;
}
