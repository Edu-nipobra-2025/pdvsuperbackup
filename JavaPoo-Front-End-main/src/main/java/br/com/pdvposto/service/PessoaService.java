package br.com.pdvposto.service;

import br.com.pdvposto.dto.PageResponse;
import br.com.pdvposto.dto.PessoaRequest;
import br.com.pdvposto.dto.PessoaResponse;
import br.com.pdvposto.model.Pessoa;
import br.com.pdvposto.util.HttpClient;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class PessoaService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public PessoaService() {
    }

    public List<Pessoa> listPessoas() {
        try {
            // Usar o método getPage para respostas paginadas
            String response = java.net.http.HttpClient.newHttpClient()
                .send(java.net.http.HttpRequest.newBuilder()
                    .uri(java.net.URI.create("http://localhost:8080/api/v1/pessoas?size=1000"))
                    .GET()
                    .build(),
                    java.net.http.HttpResponse.BodyHandlers.ofString())
                .body();

            PageResponse<PessoaResponse> pageResponse = objectMapper.readValue(
                response,
                objectMapper.getTypeFactory().constructParametricType(PageResponse.class, PessoaResponse.class)
            );

            List<Pessoa> pessoas = new ArrayList<>();
            for (PessoaResponse pessoaResponse : pageResponse.getContent()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(pessoaResponse.getId());
                pessoa.setNome(pessoaResponse.getNomeCompleto());
                pessoa.setCpf(pessoaResponse.getCpfCnpj());
                pessoa.setDataNascimento(pessoaResponse.getDataNascimento() != null ?
                    pessoaResponse.getDataNascimento().toString() : "");
                // Agora o tipoPessoa vem do back-end!
                pessoa.setTipo(pessoaResponse.getTipoPessoa() != null ?
                    pessoaResponse.getTipoPessoa() : "");
                pessoa.setRole(pessoaResponse.getRole() != null ?
                    pessoaResponse.getRole() : "USUÁRIO"); // Pegar role do backend
                pessoas.add(pessoa);
            }
            return pessoas;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao listar pessoas: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }

    public void addPessoa(Pessoa pessoa) {
        try {
            PessoaRequest request = new PessoaRequest();
            request.setNomeCompleto(pessoa.getNome());
            request.setCpfCnpj(pessoa.getCpf());

            // Converter String para Long se o campo CTPS for usado no futuro
            request.setNumeroCtps(null); // Por enquanto null

            // Converter String para LocalDate
            if (pessoa.getDataNascimento() != null && !pessoa.getDataNascimento().isEmpty()) {
                request.setDataNascimento(LocalDate.parse(pessoa.getDataNascimento(), DATE_FORMATTER));
            }

            // Converter tipo para enum
            request.setTipoPessoa(pessoa.getTipo().toUpperCase());

            HttpClient.post("/v1/pessoas", request, PessoaResponse.class);
            JOptionPane.showMessageDialog(null,
                "Pessoa adicionada com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao adicionar pessoa: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removePessoa(Long id) {
        try {
            HttpClient.delete("/v1/pessoas/" + id);
            JOptionPane.showMessageDialog(null,
                "Pessoa removida com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao remover pessoa: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updatePessoa(Long id, Pessoa pessoa) {
        try {
            PessoaRequest request = new PessoaRequest();
            request.setNomeCompleto(pessoa.getNome());
            request.setCpfCnpj(pessoa.getCpf());

            if (pessoa.getDataNascimento() != null && !pessoa.getDataNascimento().isEmpty()) {
                request.setDataNascimento(LocalDate.parse(pessoa.getDataNascimento(), DATE_FORMATTER));
            }

            request.setTipoPessoa(pessoa.getTipo().toUpperCase());

            // Adicionar role ao request
            request.setRole(pessoa.getRole());

            HttpClient.put("/v1/pessoas/" + id, request, PessoaResponse.class);
            JOptionPane.showMessageDialog(null,
                "Pessoa atualizada com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao atualizar pessoa: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}