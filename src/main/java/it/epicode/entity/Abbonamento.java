package it.epicode.entity;

import it.epicode.enums.Periodicita;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@NamedQuery(name = "Trova_tutto_Abbonamento", query = "SELECT a FROM Abbonamento a")
@DiscriminatorValue("ABBONAMENTO")

public class Abbonamento extends PreSet {

    @Enumerated (EnumType.STRING)
    @Column(name = "periodicita")
    private Periodicita periodicita;

    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;

    @Override
    public String toString() {
        return "ID= " + getId() + " periodicita= " + periodicita + " dataScadenza=" + dataScadenza + "nome utente= " + getTessera().getClienteId().getNome() + " tessera ID= " + getTessera().getId();
    }
}