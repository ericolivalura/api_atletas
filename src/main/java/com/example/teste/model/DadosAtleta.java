package com.example.teste.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAtleta(
        @JsonAlias("strPlayer")
        String nome,
        @JsonAlias("strTeam")
        String time,
        @JsonAlias("dateBorn")
        String dataDeNascimento
) {
}
