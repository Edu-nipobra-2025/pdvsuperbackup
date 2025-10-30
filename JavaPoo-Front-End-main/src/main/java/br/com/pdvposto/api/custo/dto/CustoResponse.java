package br.com.pdvposto.api.custo.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record CustoResponse(
        Double imposto,
        Double custoVariavel,
        Double custoFixo,
        Double margemLucro,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate dataProcessamento)
{ }