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
    }
}