package com.musica.musica.principal;

import com.musica.musica.modelo.Artista;
import com.musica.musica.modelo.Estilo;
import com.musica.musica.modelo.Musica;
import com.musica.musica.repositorio.Artistarepositorio;
import com.musica.musica.service.ConsultaChatGPT;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private final Scanner read = new Scanner(System.in);
    private final Artistarepositorio repositorio;
    private List<Artista> artista =new ArrayList<>();

    public Principal(Artistarepositorio artistarepositorio) {
        this.repositorio = artistarepositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    ***** Screen Sound Músicas **********
                    1 - Cadastrar artistas
                    2 - Cadastrar músicas
                    3-  Listar músicas
                    4-  Buscar músicas por artistas
                    5-  Pesquisar dados sobre um artista
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = read.nextInt();
            read.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtistas();
                    break;
                case 5:
                    pesquisarDadosSobreUmArtista();
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void pesquisarDadosSobreUmArtista() {
        System.out.println("pesquisar dados sobre qual artista?");
        var dadosArtista = read.nextLine();
        var respostata =ConsultaChatGPT.obterInformacao(dadosArtista);
        System.out.println(respostata.trim());
    }

    private void buscarMusicasPorArtistas() {
        listarArtista();
        System.out.println("Escolha um dos Artistas");
        var nomeArtista = read.nextLine();
        Optional<Artista> ArtistaBuscar = repositorio.findByNameContainingIgnoreCase(nomeArtista);
        if(ArtistaBuscar.isPresent()){
            var artistabuscado = ArtistaBuscar;
            artistabuscado.stream().forEach(s-> s.getMusica().forEach(e->
            System.out.println("Artista: " + s.getName()+ " | Música: " + e.getTitulo())));
        }
    }

    private void listarMusicas() {
        artista = repositorio.findAll();
        artista.forEach(s-> s.getMusica().forEach(e->
                System.out.println("Artista: " + s.getName() + " | Música: "+ e.getTitulo())));

    }

    private void cadastrarMusicas() {
        listarArtista();
        System.out.println("Escolha um dos Artistas");
        var nameArtista = read.nextLine();
        Optional<Artista> artista = repositorio.findByNameContainingIgnoreCase(nameArtista);
        if(artista.isPresent()){
            var artistaEncontrado = artista.get();
            System.out.println("informe o titulo da musica!");
            var musicainformada = read.nextLine();
            Musica musica = new Musica(musicainformada);
            musica.setArtista(artistaEncontrado);
            artista.get().getMusica().add(musica);
            repositorio.save(artistaEncontrado);
        }else {
            System.out.println("Artista não encontrado");
        }
    }



    private void cadastrarArtista() {
        var cadastrarNovo = "S";
        while(cadastrarNovo.equalsIgnoreCase("s")){
            System.out.println("Digite o nome do Artista");
            var nomeArtista = read.nextLine();
            System.out.println("digite o estilo do artista (Solo/Dupla/Banda)!");
            var estiloartista = read.nextLine().toUpperCase();

            Estilo estilo = Estilo.valueOf(estiloartista.toUpperCase());

            Artista artista = new Artista(nomeArtista, estilo);

            repositorio.save(artista);
            System.out.println(artista.getName()+" : " +artista.getEstilo());
            System.out.println("Deseja Listar outro Artista S/N");
            cadastrarNovo= read.nextLine();
        }
    }

    private void listarArtista() {
        artista = new ArrayList<>();
        artista = repositorio.findAll();
        artista.stream().forEach(s->
                System.out.println("name: "+ s.getName()));

    }
}
