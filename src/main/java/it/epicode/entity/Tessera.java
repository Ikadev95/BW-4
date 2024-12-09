package it.epicode.entity;





import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tessera")
public class Tessera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "utente_id", nullable = false)
    private long utenteId;

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


    public Tessera(long id, long utenteId, List<Long> listaAbbonamento, List<Long> listaBiglietto, Date dataEmissione, Date dataScadenza) {
        this.id = id;
        this.utenteId = utenteId;
        this.listaAbbonamento = listaAbbonamento;
        this.listaBiglietto = listaBiglietto;
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataScadenza;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(long utenteId) {
        this.utenteId = utenteId;
    }

    public List<Long> getListaAbbonamento() {
        return listaAbbonamento;
    }

    public void setListaAbbonamento(List<Long> listaAbbonamento) {
        this.listaAbbonamento = listaAbbonamento;
    }

    public List<Long> getListaBiglietto() {
        return listaBiglietto;
    }

    public void setListaBiglietto(List<Long> listaBiglietto) {
        this.listaBiglietto = listaBiglietto;
    }

    public Date getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(Date dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "id=" + id +
                ", utenteId=" + utenteId +
                ", listaAbbonamento=" + listaAbbonamento +
                ", listaBiglietto=" + listaBiglietto +
                ", dataEmissione=" + dataEmissione +
                ", dataScadenza=" + dataScadenza +
                '}';
    }
}
