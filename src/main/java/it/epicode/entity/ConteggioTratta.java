package it.epicode.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ConteggioTratta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tratta_id")
    private Tratta tratta;

    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;

    @Column(name = "durata (minuti)", nullable = false, length = 50)
    private int durata;

}
