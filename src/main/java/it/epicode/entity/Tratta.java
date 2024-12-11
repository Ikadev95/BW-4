package it.epicode.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NamedQuery( name = "Trova_tutto_Tratta" , query = "SELECT t FROM Tratta t WHERE t.nome IN (SELECT DISTINCT t2.nome FROM Tratta t2)")
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

    @OneToMany(mappedBy = "tratta", orphanRemoval = true)
    private Set<PreSet> preSets = new LinkedHashSet<>();


    @Override
    public String toString() {
        return "ID= " + id + " nome= " + nome + " partenza= " + partenza + " arrivo= " + arrivo ;
    }
}
