package com.musica.musica.repositorio;

import com.musica.musica.modelo.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Artistarepositorio extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNameContainingIgnoreCase(String nameArtista);
}
