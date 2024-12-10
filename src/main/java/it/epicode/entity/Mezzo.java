package it.epicode.entity;

import it.epicode.enums.StatoMezzo;
import it.epicode.enums.TipoMezzo;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NamedQuery( name = "Trova_tutto_Mezzo" , query = "SELECT p FROM Mezzo p")
public class Mezzo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

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
