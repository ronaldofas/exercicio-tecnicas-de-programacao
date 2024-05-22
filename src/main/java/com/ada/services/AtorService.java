package com.ada.services;

import com.ada.helpers.ManipuladorDeArquivos;
import com.ada.models.Ator;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

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

    public Optional<Ator> obterAtorMaisJovem(List<Ator> atores){
        return atores.stream()
                .filter(a -> a.getAward().equals("Best actor"))
                .min(Comparator.comparing(Ator::getAgeMovie));
    }

    public String obterAtrizMaisPremiada(List<Ator> atores){
        var pesquisa = atores.stream()
                .filter(a -> a.getAward().equals("Best actress"))
                .collect(groupingBy(Ator::getNome, counting()));

        var maior = pesquisa.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        var resultado = maior.orElse(null);

        if (resultado == null){
            return "Não localizado";
        } else{
            return resultado.getKey();
        }
    }

    public String obterMaiorVencedoraEntreVinteETrintaAnos(List<Ator> atores){
        var pesquisa = atores.stream()
                .filter(a -> a.getAward().equals("Best actress") && a.getAgeMovie() >= 20 && a.getAgeMovie() <=30 )
                .collect(groupingBy(Ator::getNome, counting()));

        var maior = pesquisa.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        var resultado = maior.orElse(null);

        if (resultado == null){
            return "Não localizado";
        } else{
            return resultado.getKey();
        }
    }

    public List<Map.Entry<String, Long>> obterAtoresOuAtrizesComMaisDeUmaPremiacao(List<Ator> atores){
        var pesquisa = atores.stream()
                .collect(groupingBy(Ator::getNome, counting()));

        return pesquisa.entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .toList();
    }

    public List<Ator> obterDadosDeAtorPorNome(List<Ator> atores, String nomeAhFiltrar){
        return atores.stream()
                .filter(x -> x.getNome().contains(nomeAhFiltrar))
                .toList();
    }
}
