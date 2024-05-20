package com.ada.services;

import com.ada.helpers.ManipuladorDeArquivos;
import com.ada.models.Ator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AtorService {
    public List<Ator> obterAtores() throws IOException {
        String nomeDoArquivo = "oscar_actors.csv";
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
                getClass().getClassLoader().getResource("oscar_actors.csv")).getPath();
        ManipuladorDeArquivos.gravarAtoresNoArquivo(atrizes, caminhoArquivo);
    }

    public void gravarAtores(List<Ator> listaDePremiados) throws IOException {
        List<Ator> atrizes;

        atrizes = listaDePremiados.stream()
                .filter(x -> x.getAward().equals("Best actor"))
                .toList();

        var caminhoArquivo = Objects.requireNonNull(
                getClass().getClassLoader().getResource("oscar_actors.csv")).getPath();
        ManipuladorDeArquivos.gravarAtoresNoArquivo(atrizes, caminhoArquivo);
    }
}
