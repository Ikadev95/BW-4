package it.epicode.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;

@Entity
@Data
public class Tratta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "partenza", nullable = false, length = 100)
    private String partenza;

    @Column(name = "arrivo", nullable = false, length = 100)
    private String arrivo;

    @Column(name = "durata", nullable = false, length = 50)
    private String durata;

    // @OneToMany(mappedBy = "tratta", cascade = CascadeType.ALL)
    // private List<TrattaPercorsa> trattePercorse;
}
