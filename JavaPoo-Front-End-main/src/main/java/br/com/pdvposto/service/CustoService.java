package br.com.pdvposto.service;

import br.com.pdvposto.dto.CustoRequest;
import br.com.pdvposto.dto.CustoResponse;
import br.com.pdvposto.model.Custo;
import br.com.pdvposto.util.HttpClient;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CustoService {

    public CustoService() {
    }

    public void addCusto(Custo custo) {
        try {
            CustoRequest request = new CustoRequest(
                custo.getImposto(),
                custo.getFrete(),
                custo.getCustoVariavel(),
                custo.getCustoFixo(),
                custo.getMargemLucro(),
                custo.getDataProcessamento()
            );

            HttpClient.post("/custos", request, CustoResponse.class);
            JOptionPane.showMessageDialog(null,
                "Custo adicionado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao adicionar custo: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Custo> getAllCustos() {
        try {
            CustoResponse[] responses = HttpClient.getArray("/custos", CustoResponse[].class);
            List<Custo> custos = new ArrayList<>();
            for (CustoResponse response : responses) {
                Custo custo = new Custo(
                    response.getId(), // PASSAR ID NO CONSTRUTOR
                    response.getImposto(),
                    response.getFrete(),
                    response.getCustoVariavel(),
                    response.getCustoFixo(),
                    response.getMargemLucro(),
                    response.getDataProcessamento()
                );
                custos.add(custo);
            }
            return custos;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao listar custos: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }

    public void removeCusto(Long id) {
        try {
            HttpClient.delete("/custos/" + id);
            JOptionPane.showMessageDialog(null,
                "Custo removido com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao remover custo: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateCusto(Long id, Custo custo) {
        try {
            CustoRequest request = new CustoRequest(
                custo.getImposto(),
                custo.getFrete(),
                custo.getCustoVariavel(),
                custo.getCustoFixo(),
                custo.getMargemLucro(),
                custo.getDataProcessamento()
            );

            HttpClient.put("/custos/" + id, request, CustoResponse.class);
            JOptionPane.showMessageDialog(null,
                "Custo atualizado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao atualizar custo: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
