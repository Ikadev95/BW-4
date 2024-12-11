package it.epicode.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NamedQuery( name = "Trova_tutto_Tratta" , query = "SELECT p FROM Tratta p")
public class Tratta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "partenza", nullable = false, length = 100)
    private String partenza;

    @Column(name = "arrivo", nullable = false, length = 100)
    private String arrivo;

    @Column(name = "durata (minuti)", nullable = false, length = 50)
    private int durata;

    @OneToMany(mappedBy = "tratta", orphanRemoval = true)
    private Set<PreSet> preSets = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;

    @Override
    public String toString() {
        return "ID= " + id + " nome= " + nome + " partenza= " + partenza + " arrivo= " + arrivo + " durata= " + durata + " " + mezzo + '\'';
    }
}
