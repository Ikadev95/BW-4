package it.epicode.entity;

import it.epicode.enums.TipoPuntoEmissione;
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


     @OneToMany(mappedBy = "puntoEmissione", cascade = CascadeType.ALL)
     private List<Biglietto> biglietti;

     @OneToMany(mappedBy = "puntoEmissione", cascade = CascadeType.ALL)
     private List<Abbonamento> abbonamenti;

    @Override
    public String toString() {
        return "PuntoEmissione :" +
                "id=" + id +
                ", disponibile=" + disponibile +
                ", tipo=" + tipo +
                ' ';
    }
}
