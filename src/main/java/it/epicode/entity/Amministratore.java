package it.epicode.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Amministratore extends Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    @Getter
    @Setter
    @Column (name ="ruolo")
    private String ruolo;

    public void gestioneMezzi() {

    }

    public void gestioneTratte(){

    }


}
