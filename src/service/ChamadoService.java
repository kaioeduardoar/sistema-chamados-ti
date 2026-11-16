package service;

import model.Chamado;
import model.Prioridade;
import model.StatusChamado;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ChamadoService {
    private final List<Chamado> chamados = new ArrayList<>();
    private int proximoId = 1;

    public Chamado abrirChamado(String titulo, String descricao, Prioridade prioridade, Usuario solicitante) {
        Chamado chamado = new Chamado(proximoId, titulo, descricao, prioridade, solicitante);
        chamados.add(chamado);
        proximoId++;
        return chamado;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public Chamado buscarPorId(int id) {
        for (Chamado c : chamados) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public List<Chamado> buscarPorUsuario(String nome) {
        List<Chamado> chamadosEncontrados = new ArrayList<>();
        for (Chamado c : chamados) {
            if (c.getSolicitante().getNome().equalsIgnoreCase(nome)) {
                chamadosEncontrados.add(c);
            }
        }
        return chamadosEncontrados;
    }

    public boolean alterarStatus(int id, StatusChamado novoStatus) {
        Chamado chamado = buscarPorId(id);
        if (chamado == null) {
            return false;
        }
        chamado.setStatus(novoStatus);
        return true;
    }
}
