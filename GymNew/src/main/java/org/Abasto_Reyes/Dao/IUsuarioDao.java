package org.Abasto_Reyes.Dao;

import org.Abasto_Reyes.Abasto_ReyesEntidades.Usuario;

import java.util.List;

public interface IUsuarioDao {
    List<Usuario> listarUsuarios();
    boolean agregarUsuarios(Usuario usuario);
    boolean eliminarUsuario(Usuario usuario);
    boolean actualizarUsuario(Usuario usuario);
    boolean buscarUsuarioId(Usuario usuario);
}
