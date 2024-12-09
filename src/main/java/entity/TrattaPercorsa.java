package entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class TrattaPercorsa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_di_percorrenza", nullable = false)
    private LocalDate dataDiPercorrenza;

    @Column(name = "durata", nullable = false)
    private int durata;

  //  @ManyToOne
   // @JoinColumn(name = "mezzo_id")
   // private Mezzo mezzo;

   // @ManyToOne
   // @JoinColumn(name = "tratta_id")
   // private Tratta tratta;
}
