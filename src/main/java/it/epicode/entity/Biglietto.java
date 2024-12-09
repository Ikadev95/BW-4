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
@DiscriminatorValue("BIGLIETTO")

public class Biglietto extends PreSet{

    @Column(name = "validita")
    private Boolean validita;



}
