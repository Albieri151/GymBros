package org.Abasto_Reyes.Abasto_ReyesConexion;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConnection(){
        Connection connection = null;
        var baseDatos = "gymbros";
        var url = "jdbc:mysql://localhost:3306/"+baseDatos;
        var usuario = "root";
        var password = "30481636";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,usuario,password);
        } catch (Exception e) {
            System.out.println("Error al intentar conectarte a la base de datos " + e.getMessage());
        }
        return connection;
    };
}
