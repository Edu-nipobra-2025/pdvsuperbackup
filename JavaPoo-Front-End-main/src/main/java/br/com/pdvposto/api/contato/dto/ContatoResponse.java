package br.com.pdvposto.api.contato.dto;

public record ContatoResponse (
        String telefone,
        String email,
        String endereco)
{}