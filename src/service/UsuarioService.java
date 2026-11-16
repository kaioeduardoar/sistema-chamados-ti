package service;

import model.Tecnico;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private static final String SENHA_ADMIN = "admin123";

    private final List<Usuario> usuarios = new ArrayList<>();
    private int idUsuario = 1;

    private final List<Tecnico> tecnicos = new ArrayList<>();
    private int idTecnico = 1;

    public boolean validarSenhaAdmin(String tentativa) {
        return SENHA_ADMIN.equals(tentativa);
    }

    public Usuario cadastrarUsuario(String nome, String email) {
        Usuario usuario = new Usuario(idUsuario, nome, email);
        usuarios.add(usuario);
        idUsuario++;
        return usuario;
    }

    public Tecnico cadastrarTecnico(String nome, String email, String especialidade) {
        Tecnico tecnico = new Tecnico(idTecnico, nome, email, especialidade);
        tecnicos.add(tecnico);
        idTecnico++;
        return tecnico;
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public Usuario buscarPorEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    public boolean validarUsuarioPorEmail(String email) {
        return buscarPorEmail(email) != null;
    }
}
