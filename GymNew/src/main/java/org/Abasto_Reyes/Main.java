package org.Abasto_Reyes;

import org.Abasto_Reyes.Abasto_ReyesEntidades.Usuario;
import org.Abasto_Reyes.Dao.IUsuarioDao;
import org.Abasto_Reyes.Dao.UsuarioDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        abastoReyes();
    }

    private static void abastoReyes(){
        var opcion = 0;
        do {
            var sc = new Scanner(System.in);
            System.out.println("""
                    Bienvenido al gymbros
                    
                    1. Lista de clientes
                    2. Agregue un nuevo cliente
                    3. Elimine un cliente
                    4. Actualize un cliente
                    5. Buscar usuario
                    6. Salir
                    """);

            opcion = sc.nextInt();

            if (opcion > 0 && opcion < 6) {
                opciones(opcion);

            }else if(opcion!=6)
                System.out.println("Reingrese una opcion valida");


        }while(opcion!=6);

    }

    private static void opciones(int opcion){
        var usuarioDao = new UsuarioDao();
        var sc = new Scanner(System.in);
        switch (opcion){
            case 1 -> usuarioDao.listarUsuarios().forEach(System.out::println);

            case 2 ->{
                System.out.print("Agregue el nombre del usuario nuevo: ");
                var nombre = sc.next();

                System.out.print("Agregue el apellido del usuario nuevo: ");
                var apellido = sc.next();

                System.out.print("Agregue la membresia del usuario: ");
                var membresia = sc.nextInt();

                var clienteNuevo = new Usuario(nombre,apellido,membresia);
                usuarioDao.agregarUsuarios(clienteNuevo);
            }

            case 3 ->{
                System.out.print("Ingrese el id del usuario a eliminar: ");
                var idBuscada = sc.nextInt();
                var clienteBuscado = new Usuario(idBuscada);
                usuarioDao.eliminarUsuario(clienteBuscado);
            }

            case 4 ->{
                System.out.print("Agregue el nombre del usuario actualizado: ");
                var nombre = sc.next();

                System.out.print("Agregue el apellido del usuario actualizado: ");
                var apellido = sc.next();

                System.out.print("Agregue la membresia del usuario actualizado: ");
                var membresia = sc.nextInt();

                System.out.print("Agregue la id del usuario actualizado: ");
                var id = sc.nextInt();

                var clienteNuevo = new Usuario(id,nombre,apellido,membresia);
                usuarioDao.actualizarUsuario(clienteNuevo);
            }

            case 5 ->{
                System.out.print("Agregue la id del usuario buscado: ");
                var id = sc.nextInt();

                var clienteBuscado = new Usuario(id);
                usuarioDao.buscarUsuarioId(clienteBuscado);
                System.out.println(clienteBuscado);
            }
        }
    }
}