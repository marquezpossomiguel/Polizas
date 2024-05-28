package org.example;

import org.example.dominio.*;
import org.example.integracion.RepositorioPolizas;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        //Objetos y varibales para manejar la correcta entrada de datos
        Scanner scanner = new Scanner(System.in);
        SistemaPolizas sistemaPolizas = new SistemaPolizas();
        sistemaPolizas.setNombre("PUJ");
        RepositorioPolizas repositorioPolizas = new RepositorioPolizas();
        String usuarioCuenta = null;
        String contraseniaCuenta = null;
        boolean datoCorrecto;
        boolean usuarioEncontrado;
        boolean opcionCorrecta;
        int opcionMenu = -1;
        MiembroPersonal usuarioLogIn = null;
        actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);

        //Validar inicio de sesión
        do {
            datoCorrecto = false;
            usuarioEncontrado = false;
            while (datoCorrecto == false) {
                try {
                    System.out.println("------------------------------------------------");
                    System.out.println("LogIn");
                    System.out.println("------------------------------------------------");
                    System.out.println("Digite el nombre de usuario: ");
                    usuarioCuenta = scanner.nextLine();
                    System.out.println("Digite la contrasenia: ");
                    contraseniaCuenta = scanner.next();
                    datoCorrecto = true;
                    scanner.nextLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (MiembroPersonal miembroPersonal : sistemaPolizas.getMiembrosPersonal()) {
                if(miembroPersonal.getNombre().equals(usuarioCuenta) && miembroPersonal.getContrasenia().equals(contraseniaCuenta)){
                    usuarioLogIn = miembroPersonal;
                    usuarioEncontrado = true;
                }
            }
        } while (usuarioEncontrado == false);

        //Menú del sistema de polizas
        do {
            opcionCorrecta = false;
            System.out.println("------------------------------------------------");
            System.out.println("Sistema de Polizas " + sistemaPolizas.getNombre());
            System.out.println("------------------------------------------------");
            System.out.println("Digite la opcion que quiere ejecutar: ");
            System.out.println("\t(1) Iniciar registro cita");
            System.out.println("\t(2) Actualizar monto pagado");
            System.out.println("\t(3) Salir");
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
                    opcionA(sistemaPolizas, repositorioPolizas, usuarioLogIn, scanner);
                    System.out.println();
                    break;
                case 2:
                    System.out.println("-----------------");
                    actualizarMontoPagado();
                    System.out.println();
                    break;
                case 3:
                    System.out.println("-----------------");
                    System.out.println("Gracias, vuelva pronto");
                    return;
                default:
                    System.out.println("-----------------");
                    System.out.println("El numero ingresado no corresponde a ninguna de las opciones permitidas");
                    System.out.println();
                    break;
            }
        } while (true);
    }

    //Actuliza el repositorio de polizas para que el usuario intertue con los datos actualizados
    public static void actulizarRepositorioPolizas(SistemaPolizas sistemaPolizas, RepositorioPolizas repositorioPolizas){
        repositorioPolizas.cargarMiembrosPersonal(sistemaPolizas);
        repositorioPolizas.cargarPacientes(sistemaPolizas);
        repositorioPolizas.cargarCitas(sistemaPolizas);
        repositorioPolizas.cargarPolizas(sistemaPolizas);
        repositorioPolizas.cargarReclamaciones(sistemaPolizas);
    }

    //Opción A, 1
    public static void iniciarRegistroCita(SistemaPolizas sistemaPolizas, RepositorioPolizas repositorioPolizas, MiembroPersonal miembroPersonal, Scanner scanner){
        Integer idPaciente;
        java.sql.Date fechaActual = new java.sql.Date(new Date().getTime());
        Cita cita = new Cita();

        sistemaPolizas.mostrarPacientes();
        System.out.println("Digite el id del paciente: ");
        idPaciente = scanner.nextInt();
        cita.setPaciente(sistemaPolizas.buscarPaciente(idPaciente));
        cita.setMiembroPersonal(miembroPersonal);
        cita.setFechaProgramada(fechaActual);
        repositorioPolizas.insertarCita(cita);
    }

    //Opción A, 2
    public static void agregarReclamoCita(){

    }

    //Opción A, 3
    public static void listarReclamosCita(){

    }

    //Opción A, 4
    public static void eliminarReclamoCita(){

    }

    //Opción A, 5
    public static void modificarMontoReclamadoReclamoCita(){

    }

    //Opción A, 6
    public static void totalizarCita(){

    }

    //Opción A, 7
    public static void consultarCitasAlmacenadas(){

    }

    //Menú, Opción A
    public static void opcionA(SistemaPolizas sistemaPolizas, RepositorioPolizas repositorioPolizas, MiembroPersonal miembroPersonal, Scanner scanner) {
        boolean opcionCorrecta;
        int opcionMenu = -1;
        do {
            opcionCorrecta = false;
            System.out.println("Digite la opcion que quiere ejecutar: ");
            System.out.println("\t(1) Iniciar registro cita");
            System.out.println("\t(2) Agregar reclamo a la cita");
            System.out.println("\t(3) Listar reclamos a la cita");
            System.out.println("\t(4) Eliminar reclamo de la cita");
            System.out.println("\t(5) Modificar monto reclamado del reclamo en una cita");
            System.out.println("\t(6) Totalizar cita");
            System.out.println("\t(7) Consultar citas almacenadas");
            System.out.println("\t(8) Salir");
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
                    iniciarRegistroCita(sistemaPolizas, repositorioPolizas, miembroPersonal, scanner);
                    actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
                    System.out.println();
                    break;
                case 2:
                    System.out.println("-----------------");
                    agregarReclamoCita();
                    actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("-----------------");
                    listarReclamosCita();
                    actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
                    System.out.println();
                    break;
                case 4:
                    System.out.println("-----------------");
                    eliminarReclamoCita();
                    actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
                    System.out.println();
                    break;
                case 5:
                    System.out.println("-----------------");
                    modificarMontoReclamadoReclamoCita();
                    actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
                    System.out.println();
                    break;
                case 6:
                    System.out.println("-----------------");
                    totalizarCita();
                    actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
                    System.out.println();
                    break;
                case 7:
                    System.out.println("-----------------");
                    consultarCitasAlmacenadas();
                    actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
                    System.out.println();
                    break;
                case 8:
                    System.out.println("-----------------");
                    actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
                    System.out.println();
                    return;
                default:
                    System.out.println("-----------------");
                    System.out.println("El numero ingresado no corresponde a ninguna de las opciones permitidas");
                    System.out.println();
                    break;
            }
        } while (true);
    }

    //Opción B
    public static void actualizarMontoPagado(){

    }
}
