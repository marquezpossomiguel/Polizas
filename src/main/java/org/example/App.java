package org.example;

import org.example.dominio.*;
import org.example.integracion.RepositorioPolizas;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        SistemaPolizas sistemaPolizas = new SistemaPolizas();
        RepositorioPolizas repositorioPolizas = new RepositorioPolizas();
        String usuarioCuenta = new String();
        String contraseniaCuenta = new String();
        boolean datoCorrecto;
        boolean usuarioEncontrado;
        boolean opcionCorrecta;
        int opcionMenu = -1;
        MiembroPersonal usuarioLogIn = null;
        repositorioPolizas.cargarMiembrosPersonal(sistemaPolizas.getMiembrosPersonal());
        repositorioPolizas.cargarPacientes(sistemaPolizas.getPacientes());

        do {
            datoCorrecto = false;
            usuarioEncontrado = false;
            while (datoCorrecto == false) {
                try {
                    System.out.println("Digite el nombre de usuario: ");
                    usuarioCuenta = scanner.nextLine();
                    System.out.println("Digite la contrasenia: ");
                    scanner.nextLine();
                    contraseniaCuenta = scanner.next();
                    scanner.nextLine();
                    datoCorrecto = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    scanner.nextLine();
                }
            }
            for (MiembroPersonal miembroPersonal : sistemaPolizas.getMiembrosPersonal()) {
                if(miembroPersonal.getNombre().equals(usuarioCuenta) && miembroPersonal.getContrasenia().equals(contraseniaCuenta)){
                    usuarioLogIn = miembroPersonal;
                    usuarioEncontrado = true;
                }
            }
        } while (usuarioEncontrado == false);

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
                    scanner.nextLine();
                    opcionCorrecta = true;
                } catch (Exception e) {
                    e.printStackTrace();
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

    public void iniciarRegistroCita(SistemaPolizas sistemaPolizas, RepositorioPolizas repositorioPolizas, MiembroPersonal miembroPersonal, Scanner scanner) {
        Integer idPaciente;
        Cita cita = new Cita();
        sistemaPolizas.mostrarPacientes();
        System.out.println("Digite el id del paciente: ");
        idPaciente = scanner.nextInt();
        scanner.nextLine();
        cita.setPaciente(buscarPaciente(sistemaPolizas, idPaciente));
        cita.setMiembroPersonal(miembroPersonal);
        repositorioPolizas.insertarCita(cita);
    }

    public Paciente buscarPaciente(SistemaPolizas sistemaPolizas, Integer idPaciente) {
        for(Paciente paciente : sistemaPolizas.getPacientes()){
            if(paciente.getId().equals(idPaciente)){
                return paciente;
            }
        }
        return null;
    }
}
