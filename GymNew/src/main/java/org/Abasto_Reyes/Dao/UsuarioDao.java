package org.Abasto_Reyes.Dao;

import org.Abasto_Reyes.Abasto_ReyesEntidades.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.Abasto_Reyes.Abasto_ReyesConexion.Conexion.getConnection;

public class UsuarioDao implements IUsuarioDao {
    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();

        PreparedStatement ps;
        ResultSet rs;
        Connection connection = getConnection();
        var consultaBD = "SELECT * FROM Usuario ORDER BY idUsuario";

        try {
            ps = connection.prepareStatement(consultaBD);
            rs = ps.executeQuery();
            while (rs.next()){
                var usuario = new Usuario();
                usuario.setId(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setMembresia(rs.getInt("membresia"));
                listaUsuarios.add(usuario);
            }
        }catch (Exception e){
            System.out.println("Error al intentar conectar con la base de datos " + e.getMessage());
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                System.out.println("Error al intentar cerrar la conexion "+ e.getMessage());
            }
        }

        return listaUsuarios;
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) {
        PreparedStatement ps;
        var connection = getConnection();
        var sql = "UPDATE usuario SET nombre=?, apellido=?, membresia=? WHERE idUsuario=?";

        try {
            ps=connection.prepareStatement(sql);
            ps.setString(1,usuario.getNombre());
            ps.setString(2,usuario.getApellido());
            ps.setInt(3,usuario.getMembresia());
            ps.setInt(4, usuario.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al intentar actualizar usuario "+e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error al intentar cerrar la conexion" + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarUsuario(Usuario usuario) {
        PreparedStatement ps;
        var connection = getConnection();
        var sql = "DELETE FROM usuario WHERE idUsuario=?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,usuario.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al intentar borrar el usuario " +e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error al intentar cerrar la conexion a la base de datos");
            }
        }
        return false;
    }

    @Override
    public boolean agregarUsuarios(Usuario usuario) {
        PreparedStatement ps;
        var connection = getConnection();
        var sql = "INSERT INTO usuario(nombre, apellido, membresia) " + " VALUES(?, ?, ?)";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setInt(3, usuario.getMembresia());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al intentar agregar el usuario "+e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion a la base de datos" + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean buscarUsuarioId(Usuario usuario) {
        PreparedStatement ps;
        ResultSet rs;
        var connection = getConnection();
        var sql = "SELECT * FROM usuario WHERE IdUsuario = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,usuario.getId());
            rs = ps.executeQuery();
            if (rs.next()){
                usuario.setNombre(rs.getString("nombre"));
                usuario.setMembresia(rs.getInt("membresia"));
                usuario.setApellido(rs.getString("apellido"));
                return true;
            }
        }catch (Exception e){
            System.out.println("Error al buscar usuarios: " + e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error al intentar cerrar la base de datos "+e.getMessage());
            }
        }
        return false;
    }
}
