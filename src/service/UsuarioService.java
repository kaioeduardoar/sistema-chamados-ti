package service;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();
    private int idUsuario = 1;
    public Usuario cadastrarUsuario(String nome, String email){
        Usuario usuario = new Usuario(idUsuario, nome, email);
        usuarios.add(usuario);
        idUsuario++;
        return usuario;
    }
    public List<Usuario> listarUsuarios(){
        return usuarios;
    }
    public Usuario buscarPorEmail(String email){
        for (Usuario u : usuarios){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }
    public boolean validarUsuarioPorEmail(String email){
        for (Usuario u : usuarios){
            if (u.getEmail() == email){
                return true;
            }
        }
        return false;
    }
}
