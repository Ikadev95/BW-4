package it.epicode.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Data
@Entity
@NamedQuery(name = "Trova_tutto_Biglietto", query = "SELECT a FROM Biglietto a")
@NamedQuery(name = "Trova_attivi", query = "SELECT a FROM Biglietto a WHERE a.validita = true")
@NamedQuery(name = "Trova_scaduti", query = "SELECT a FROM Biglietto a WHERE a.validita = false")
@DiscriminatorValue("BIGLIETTO")

public class Biglietto extends PreSet{

    @Column(name = "validita")
    private Boolean validita;

    @Override
    public String toString() {
        return "ID= " + getId() + " validita= " + validita + "nome utente= " + getTessera().getClienteId().getNome() + " tessera ID= " + getTessera().getId();
    }
}

