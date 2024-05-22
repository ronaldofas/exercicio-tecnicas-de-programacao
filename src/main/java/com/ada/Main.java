package com.ada;

import com.ada.models.Ator;
import com.ada.services.AtorService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AtorService service = new AtorService();
        List<Ator> atores = new ArrayList<>();

        System.out.println("Lendo o arquivo de origem ('oscar_actors.csv')");
        try {
            atores = service.obterAtores();
        } catch (IOException ex){
            System.out.println("Erro na leitura do arquivo: " + ex.getMessage());
        }

        System.out.println("Gravando o arquivo de destino ('atrizes.csv')");
        if (!atores.isEmpty()){
            try {
                service.gravarAtrizes(atores);
            } catch (IOException ex){
                System.out.println("Erro na gravação do arquivo de atrizes: " + ex.getMessage());
            }
        }

        System.out.println("Gravando o arquivo de destino ('atores.csv')");
        if (!atores.isEmpty()){
            try {
                service.gravarAtores(atores);
            } catch (IOException ex){
                System.out.println("Erro na gravação do arquivo de atrizes: " + ex.getMessage());
            }
        }

        System.out.println("Ator mais novo a ser premiado: ");
        System.out.println(service.obterAtorMaisJovem(atores).orElse(null));

        System.out.println("Atriz com maior número de premiações:");
        System.out.println(service.obterAtrizMaisPremiada(atores));

        System.out.println("Atriz com idade entre 20 e 30 anos com maior quantidade de permiação: ");
        System.out.println(service.obterMaiorVencedoraEntreVinteETrintaAnos(atores));

        System.out.println("Atrizes ou atores com mais de uma premiação: ");
        var atoresPremiados = service.obterAtoresOuAtrizesComMaisDeUmaPremiacao(atores);
        atoresPremiados.forEach(entry -> System.out.println(
                "Item: " + entry.getKey() + " -> Quantidade: " + entry.getValue())
        );

        System.out.println("Dados por ator ou atriz (Tom Hanks): ");
        var resultadoDoAtor = service.obterDadosDeAtorPorNome(atores, "Tom Hanks");
        System.out.println("Quantidade de prêmios: " + resultadoDoAtor.size());
        resultadoDoAtor.forEach(item -> System.out.println(
                "Ano do filme: " + item.getYear() +
                ", idade do ator na premiação: " + item.getAgeMovie() +
                " e nome do filme: " + item.getMovie())
        );

    }
}