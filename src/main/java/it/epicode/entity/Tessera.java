package it.epicode.entity;





import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "tessera")
public class Tessera {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Utente clienteId;
    
    @OneToMany(mappedBy = "tessera")
    private List<Abbonamento> listaAbbonamenti;

    @OneToMany(mappedBy = "tessera")
    private List<Biglietto> listaBiglietti;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_emissione", nullable = false)
    private LocalDate dataEmissione;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_scadenza", nullable = false)
    private LocalDate dataScadenza;


    public Tessera() {
    }



    @Override
    public String toString() {
        return "Tessera{" +
                "id=" + id +
                ", utenteId=" + clienteId +
                ", listaAbbonamento=" + listaAbbonamenti +
                ", listaBiglietto=" + listaBiglietti +
                ", dataEmissione=" + dataEmissione +
                ", dataScadenza=" + dataScadenza +
                '}';
    }
}
