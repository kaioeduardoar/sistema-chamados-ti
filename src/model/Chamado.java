package model;

public class Chamado {
    private int id;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private StatusChamado status;
    private Usuario solicitante;
    private Tecnico tecnico;

    public Chamado(int id, String titulo, String descricao, Prioridade prioridade, StatusChamado status, Usuario solicitante, Tecnico tecnico){
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.solicitante = solicitante;
        this.status = StatusChamado.ABERTO;
    }
    public int getId(){
        return id;
    }
    public String getTitulo(){
        return titulo;
    }
    public String getDescricao(){
        return descricao;
    }
    public Prioridade getPrioridade(){
        return prioridade;
    }
    public Usuario getSolicitante(){
        return solicitante;
    }
    public Tecnico getTecnico(){
        return tecnico;
    }
    public void atribuirTecnico(Tecnico tecnico){
        this.tecnico = tecnico;
        this.status = StatusChamado.ABERTO;
    }
    @Override
    public String toString() {
        String nomeTecnico = (tecnico != null) ? tecnico.getNome() : "Não atribuído";
        return "Chamado #" + id +
                " | Título: " + titulo +
                " | Prioridade: " + prioridade +
                " | Status: " + status +
                " | Solicitante: " + solicitante.getNome() +
                " | Técnico: " + nomeTecnico;
    }
}
