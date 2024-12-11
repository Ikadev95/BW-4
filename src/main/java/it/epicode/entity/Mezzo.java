package it.epicode.entity;

import it.epicode.enums.StatoMezzo;
import it.epicode.enums.TipoMezzo;
import jakarta.persistence.*;
import lombok.Data;

import javax.lang.model.element.Name;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NamedQuery( name = "Trova_tutto_Mezzo" , query = "SELECT p FROM Mezzo p")
@NamedQuery(name ="Trova_tutti_Disponibili", query = "SELECT m FROM Mezzo m WHERE m.stato = SERVIZIO")
public class Mezzo {

    @Id
    @Column(name = "id")
    private String id;

    @PrePersist
    public void generateId() {
        if (id == null) {
            this.id = UUID.randomUUID().toString().substring(0, 5);
        }
    }

    @Column(name = "capienza", nullable = false)
    private int capienza;

    @Enumerated(EnumType.STRING)
    private TipoMezzo tipo;

    @Enumerated(EnumType.STRING)
    private StatoMezzo stato;

    @Column(name = "data_inizio", nullable = false)
    private LocalDate dataInizio;

    @Column(name = "data_fine", nullable = false)
    private LocalDate dataFine;

    @Column(name =  "num_manutenzioni", nullable = false)
    private int NumManutenzioni;

    @OneToMany(mappedBy = "mezzo", orphanRemoval = true)
    private Set<Tratta> viaggiPerTratta = new LinkedHashSet<>();

    @OneToMany(mappedBy = "mezzo", cascade = CascadeType.ALL)
    private List<Biglietto> biglietti;

    @Override
    public String toString() {
        return "Mezzo: " +
                "stato=" + stato +
                ", tipo=" + tipo +
                ", capienza=" + capienza +
                ", id=" + id +
                ' ';
    }
}
