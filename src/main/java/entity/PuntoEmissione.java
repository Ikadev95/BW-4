package entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class PuntoEmissione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "disponibile", nullable = false)
    private boolean disponibile;

    @Enumerated(EnumType.STRING)
    private TipoPuntoEmissione tipo;

    public enum TipoPuntoEmissione {
        DISTRIBUTORE_AUTOMATICO,
        RIVENDITORE_AUTORIZZATO
    }


    // @OneToMany(mappedBy = "puntoEmissione", cascade = CascadeType.ALL)
    // private List<Biglietto> biglietti;

    // @OneToMany(mappedBy = "puntoEmissione", cascade = CascadeType.ALL)
    // private List<Abbonamento> abbonamenti;
}
