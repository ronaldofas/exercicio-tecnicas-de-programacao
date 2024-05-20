package com.ada.services;

import com.ada.models.Ator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ManipuladorDeArquivos {

    private static List<Ator> learArquivoAtores(final String filename)
            throws IOException {
        List<Ator> retorno = new ArrayList<>();

        final Path path = Paths.get(filename);
        var lines = Files.readAllLines(path);

        for (String linha : lines){
            var extracao = linha.split(",");
            if (extracao[0].equals("oscar_no")){
                continue;
            }
            String nome = extracao[3];
            String movie = extracao[4];
            int ano = Integer.parseInt(extracao[1]);
            var anoAniversario = Integer.parseInt(extracao[10]);
            var mesAniversario = Integer.parseInt(extracao[8]);
            var diaAniversario = Integer.parseInt(extracao[9]);
            LocalDateTime dataAniversario =
                    LocalDateTime.of(anoAniversario, mesAniversario, diaAniversario, 0, 0);
            retorno.add(new Ator(nome, movie, ano,dataAniversario));
        }

        return retorno;
    }
}
