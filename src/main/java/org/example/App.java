package org.example;

import org.example.dominio.SistemaPolizas;
import org.example.dominio.Usuario;

import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        SistemaPolizas sistemaPolizas = new SistemaPolizas();
        String usuarioCuenta = new String();
        String contraseniaCuenta = new String();
        boolean datoCorrecto;
        boolean usuarioLogIn;
        boolean opcionCorrecta;
        int opcionMenu = -1;



        do {
            datoCorrecto = false;
            usuarioLogIn = false;
            while (datoCorrecto == false) {
                try {
                    System.out.println("Digite el nombre de usuario: ");
                    usuarioCuenta = scanner.nextLine();
                    System.out.println("Digite la contrasenia: ");
                    contraseniaCuenta = scanner.next();
                    datoCorrecto = true;
                } catch (Exception e) {
                    System.out.println("Excepcion: " + e.getMessage());
                    scanner.nextLine();
                }
            }
            for(Usuario usuario : sistemaPolizas.getUsuarios()){
                if(usuario.getNombre().equals(usuarioCuenta) && usuario.getContrasenia().equals(contraseniaCuenta)){
                    usuarioLogIn = true;
                }
            }
        } while (usuarioLogIn == false);

        do {
            opcionCorrecta = false;
            System.out.println("------------------------------------------------");
            System.out.println("Sistema de Polizas " + sistemaPolizas.getNombre());
            System.out.println("------------------------------------------------");
            System.out.println("Digite la opcion que quiere ejecutar: ");
            System.out.println("\t(1) Iniciar registro cita");
            System.out.println("\t(2) Actualizar monto pagado");
            while (opcionCorrecta == false) {
                try {
                    System.out.print("Respuesta: ");
                    opcionMenu = scanner.nextInt();
                    opcionCorrecta = true;
                } catch (Exception e) {
                    System.out.println("Excepcion: " + e.getMessage());
                    scanner.nextLine();
                }
            }
            switch (opcionMenu) {
                case 1:
                    System.out.println("-----------------");
                    System.out.println();
                    break;
                case 2:
                    System.out.println("-----------------");
                    System.out.println();
                    break;
                default:
                    System.out.println("-----------------");
                    System.out.println("El numero ingresado no corresponde a ninguna de las opciones permitidas");
                    System.out.println();
                    break;
            }
        } while (true);
    }
}
