package it.epicode.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Cliente extends Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    @Setter
    @Getter
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Long tessera;



}
