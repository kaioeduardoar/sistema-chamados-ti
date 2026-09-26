    package service;
    import model.*;

    import java.util.ArrayList;
    import java.util.List;

    public class ChamadoService {
        private List<Chamado> chamados =new ArrayList<>();
        private int proximoId = 1;
        public Chamado abrirChamado(String titulo, String descricao, Prioridade prioridade, Usuario solicitante){
            Chamado chamado = new Chamado(proximoId, titulo, descricao, prioridade, solicitante);
            chamados.add(chamado);
            proximoId++;
            return chamado;
        }
        public List<Chamado> listarChamados(){
            return chamados;
        }
        public Chamado buscarPorId(int id){
            for(Chamado c : chamados){
                if (c.getId() == id){
                    return c;
                }
            }
            return null;
        }
        public boolean alterarStatus(int id, StatusChamado novoStatus){
            Chamado chamado = buscarPorId(id);
            if(chamado == null){
                return false;
            }else{
                chamado.setStatus(novoStatus);
                return true;

            }
        }
    }
