package com.ada.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Ator {
    private String nome;
    private String movie;
    private int year;
    private LocalDateTime birthDate;
    private int ageMovie;
    private int ageNow;

    public Ator(String nome, String movie, int year, LocalDateTime birthDate) {
        this.nome = nome;
        this.movie = movie;
        this.year = year;
        this.birthDate = birthDate;
        this.ageMovie = calcularIdadeDoAtorNaPremiacao();
        this.ageNow = calcularIdadeDoAtorAtualmente();
    }

    private int calcularIdadeDoAtorAtualmente() {
        var dataNascimento = LocalDate.of(birthDate.getYear(), birthDate.getMonth(), birthDate.getDayOfMonth());
        var dataAtual = LocalDate.now();
        return Period.between(dataNascimento, dataAtual).getYears();
    }

    private int calcularIdadeDoAtorNaPremiacao() {
        var dataNascimento = LocalDate.of(birthDate.getYear(), birthDate.getMonth(), birthDate.getDayOfMonth());
        var dataFilme = LocalDate.of(this.year, 1, 1);
        return Period.between(dataNascimento, dataFilme).getYears();
    }

    public String getNome() {
        return nome;
    }

    public String getMovie() {
        return movie;
    }

    public int getYear() {
        return year;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public int getAgeMovie() {
        return ageMovie;
    }

    public int getAgeNow() {
        return ageNow;
    }


}
