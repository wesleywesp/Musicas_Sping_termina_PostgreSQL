package com.musica.musica.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
@Getter
@Setter
public class Artista {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private Estilo estilo;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Musica> musica = new ArrayList<>();

    public Artista(){}

    public Artista(String name, Estilo estilo){
        this.name =name;
        this.estilo = estilo;
    }
}
