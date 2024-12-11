package it.epicode.entity;

import it.epicode.enums.StatoMezzo;
import it.epicode.enums.TipoPuntoEmissione;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@NamedQuery( name = "Trova_tutto_PuntoEmissione" , query = "SELECT p FROM PuntoEmissione p")
public class PuntoEmissione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoPuntoEmissione tipo;

     @OneToMany(mappedBy = "puntoEmissione", cascade = CascadeType.ALL)
     private List<Biglietto> biglietti;

     @OneToMany(mappedBy = "puntoEmissione", cascade = CascadeType.ALL)
     private List<Abbonamento> abbonamenti;

    @Override
    public String toString() {
        return "ID = " + id + "  tipo = " + tipo + " nome = " + nome;
    }

}
