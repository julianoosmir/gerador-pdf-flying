package com.generate.models;

import lombok.Value;

import java.time.LocalDate;

@Value
public class DadoDeclaracao {
    String nome;
    String cpf;
    LocalDate dataDeclaracao;
}
