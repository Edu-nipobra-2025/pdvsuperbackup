package br.com.pdvposto.api.produto.dto;

public record ProdutoRequest (
    String nome,
    String referencia,
    String fornecedor,
    String categoria,
    String marca)
{}
