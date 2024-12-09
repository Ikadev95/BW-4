package it.epicode.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "tessera_id")
    private Long tessera;

}
