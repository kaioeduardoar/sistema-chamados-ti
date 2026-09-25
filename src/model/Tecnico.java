package model;

public class Tecnico extends Usuario {
    private String especialidade;

    public Tecnico(int id, String nome, String email, String especialidade) {
        super(id, nome, email);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    @Override
    public String toString() {
        return "Tecnico{id=" + getId() + ", nome='" + getNome() + "', especialidade='" + especialidade + "'}";
    }
}
