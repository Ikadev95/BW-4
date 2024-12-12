package it.epicode.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@NamedQuery(name = "Trova_tutto_ConteggioTratta", query = "SELECT a FROM ConteggioTratta a")
@NamedQuery(name ="Durata_tratta_media", query = "SELECT ct.tratta, ct.mezzo, AVG(ct.durata) as durata_media FROM ConteggioTratta ct GROUP BY ct.tratta, ct.mezzo")
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
