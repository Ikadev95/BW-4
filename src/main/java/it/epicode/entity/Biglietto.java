package it.epicode.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
@Entity
@NamedQuery(name = "Trova_tutto_Biglietto", query = "SELECT a FROM Biglietto a")
public class Biglietto extends PreSet{

    @Column(name = "validita")
    private Boolean validita;



}
