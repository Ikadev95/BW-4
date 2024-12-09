package it.epicode.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Mezzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "capienza", nullable = false)
    private int capienza;

    @Column(name = "in_servizio", nullable = false)
    private boolean inServizio;

    @Enumerated(EnumType.STRING)
    private TipoMezzo tipo;

    @Enumerated(EnumType.STRING)
    private StatoMezzo stato;

    public enum TipoMezzo {
        AUTOBUS,
        TRAM
    }

    public enum StatoMezzo {
        IN_SERVIZIO,
        IN_MANUTENZIONE
    }

   // @OneToMany(mappedBy = "mezzo", cascade = CascadeType.ALL)
   // private List<Biglietto> biglietti;


   // @OneToMany(mappedBy = "mezzo", cascade = CascadeType.ALL)
   // private List<TrattaPercorsa> trattePercorse;
}
