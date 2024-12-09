package it.epicode.entity;

import it.epicode.enums.Periodicita;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@NamedQuery(name = "Trova_tutto_Abbonamento", query = "SELECT a FROM Abbonamento a")
public class Abbonamento extends PreSet {

    @Enumerated
    @Column(name = "periodicita")
    private Periodicita periodicita;

}
