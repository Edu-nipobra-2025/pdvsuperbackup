package br.com.pdvposto.api.contato.dto;

public record ContatoRequest (
    String telefone,
    String email,
    String endereco)
{}
