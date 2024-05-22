package com.ada.helpers;

import com.ada.models.Ator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ManipuladorDeArquivos {

    public static List<Ator> lerArquivoAtores(final String filename)
            throws IOException {
        List<Ator> retorno = new ArrayList<>();
        String regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        final Path path = Paths.get(filename);
        var lines = Files.readAllLines(path);

        for (String linha : lines){
            var extracao = linha.split(regex);
            if (extracao[0].equals("oscar_no")){
                continue;
            }
            String nome = extracao[3];
            String movie = extracao[4];
            int ano = Integer.parseInt(extracao[1]);
            var anoAniversario = Integer.parseInt(extracao[10]);
            var mesAniversario = Integer.parseInt(extracao[8]);
            var diaAniversario = Integer.parseInt(extracao[9]);
            var premio = extracao[2];
            LocalDateTime dataAniversario =
                    LocalDateTime.of(anoAniversario, mesAniversario, diaAniversario, 0, 0);
            retorno.add(new Ator(nome, movie, ano,dataAniversario, premio));
        }

        return retorno;
    }

    public static void gravarAtoresNoArquivo(final List<Ator> atores,
                                             final String filename) throws IOException {

        Path path = Paths.get(filename);

        var content = atores.stream()
                .sorted(Comparator.comparing(Ator::getNome)
                        .thenComparingInt(Ator::getYear))
                .map(ator -> ator.getNome() + ","
                        + ator.getMovie() + ","
                        + ator.getYear() + ","
                        + ator.getBirthDate() + ","
                        + ator.getAward() + ","
                        + ator.getAgeMovie() + ","
                        + ator.getAgeNow()
                )
                .collect(Collectors.joining("\n"));

        Files.writeString(path, content);

    }
}
