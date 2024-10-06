package com.musica.musica.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "musicas")
@Getter
@Setter
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private Artista artista;

    public Musica(){}


    public Musica(String musicainformada) {
        this.titulo =musicainformada;
    }
}
