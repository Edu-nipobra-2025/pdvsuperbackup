package br.com.pdvposto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class PessoaRequest {
    @JsonProperty("nomeCompleto")
    private String nomeCompleto;

    @JsonProperty("cpfCnpj")
    private String cpfCnpj;

    @JsonProperty("dataNascimento")
    private LocalDate dataNascimento;

    @JsonProperty("tipoPessoa")
    private String tipoPessoa;

    @JsonProperty("role")
    private String role;

    @JsonProperty("numeroCtps")
    private String numeroCtps;


    public PessoaRequest() {}

    public PessoaRequest(String nomeCompleto, String cpfCnpj, LocalDate dataNascimento, String tipoPessoa,
                         String numeroCtps) {
        this.nomeCompleto = nomeCompleto;
        this.cpfCnpj = cpfCnpj;
        this.dataNascimento = dataNascimento;
        this.tipoPessoa = tipoPessoa;
        this.numeroCtps = numeroCtps;

    }

    // Getters e Setters
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
public String getNumeroCtps(){
        return numeroCtps;
}
    public void setNumeroCtps(Object o) {
    }
}
