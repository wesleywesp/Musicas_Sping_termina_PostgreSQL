package com.musica.musica;

import com.musica.musica.principal.Principal;
import com.musica.musica.repositorio.Artistarepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicaApplication implements CommandLineRunner {
	@Autowired
	private Artistarepositorio artistarepositorio;

	public static void main(String[] args) {
		SpringApplication.run(MusicaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(artistarepositorio);
		principal.exibeMenu();
	}
}
