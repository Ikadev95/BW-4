package it.epicode.entity;





import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private Cliente clienteId;

    @ElementCollection
    @CollectionTable(name = "abbonamenti", joinColumns = @JoinColumn(name = "tessera_id"))
    @Column(name = "lista_abbonamento")
    private List<Long> listaAbbonamento;

    @ElementCollection
    @CollectionTable(name = "biglietti", joinColumns = @JoinColumn(name = "tessera_id"))
    @Column(name = "lista_biglietto")
    private List<Long> listaBiglietto;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_emissione", nullable = false)
    private Date dataEmissione;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_scadenza", nullable = false)
    private Date dataScadenza;


    public Tessera() {
    }






    @Override
    public String toString() {
        return "Tessera{" +
                "id=" + id +
                ", utenteId=" + clienteId +
                ", listaAbbonamento=" + listaAbbonamento +
                ", listaBiglietto=" + listaBiglietto +
                ", dataEmissione=" + dataEmissione +
                ", dataScadenza=" + dataScadenza +
                '}';
    }
}
