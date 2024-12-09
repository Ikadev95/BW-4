package it.epicode.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
public class Cliente extends Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;



    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "tessera_id")
    private Tessera tessera;


}
