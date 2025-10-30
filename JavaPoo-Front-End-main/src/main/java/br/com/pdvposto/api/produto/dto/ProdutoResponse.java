package br.com.pdvposto.api.produto.dto;

public record ProdutoResponse (
        String nome,
        String referencia,
        String fornecedor,
        String categoria,
        String marca)
{}
