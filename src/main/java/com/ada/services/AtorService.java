package com.ada.services;

import com.ada.helpers.ManipuladorDeArquivos;
import com.ada.models.Ator;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class AtorService {
    public List<Ator> obterAtores() throws IOException {
        String nomeDoArquivo = "oscars_actors.csv";
        URL arquivoOrigem = getClass().getClassLoader().getResource(nomeDoArquivo);
        if (arquivoOrigem != null){
            // Fazer a leitura dos arquivos
            return ManipuladorDeArquivos.lerArquivoAtores(arquivoOrigem.getPath());
        } else {
            throw new IllegalArgumentException("File not found!");
        }
    }

    public void gravarAtrizes(List<Ator> listaDePremiados) throws IOException {
        List<Ator> atrizes;

        atrizes = listaDePremiados.stream()
                .filter(x -> x.getAward().equals("Best actress"))
                .toList();

        var caminhoArquivo = Objects.requireNonNull(
                getClass().getClassLoader().getResource("oscars_actors.csv")).getPath();
        caminhoArquivo = caminhoArquivo.replace("oscars_actors.csv", "atrizes.csv");
        ManipuladorDeArquivos.gravarAtoresNoArquivo(atrizes, caminhoArquivo);
    }

    public void gravarAtores(List<Ator> listaDePremiados) throws IOException {
        List<Ator> atrizes;

        atrizes = listaDePremiados.stream()
                .filter(x -> x.getAward().equals("Best actor"))
                .toList();

        var caminhoArquivo = Objects.requireNonNull(
                getClass().getClassLoader().getResource("oscars_actors.csv")).getPath();
        caminhoArquivo = caminhoArquivo.replace("oscars_actors.csv", "atores.csv");
        ManipuladorDeArquivos.gravarAtoresNoArquivo(atrizes, caminhoArquivo);
    }
}
