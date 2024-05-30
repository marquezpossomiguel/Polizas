package org.example;

import org.example.dominio.*;
import org.example.integracion.RepositorioPolizas;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        //Objetos y variables para manejar la correcta entrada de datos
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

        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\t\t\t\tLogIn");
        System.out.println("--------------------------------------------------------------------------------------------");
        //Validar inicio de sesión
        do {
            datoCorrecto = false;
            usuarioEncontrado = false;
            while (datoCorrecto == false) {
                try {
                    System.out.println("Digite el nombre de usuario: ");
                    usuarioCuenta = scanner.nextLine();
                    System.out.println("Digite la contrasenia: ");
                    contraseniaCuenta = scanner.next();
                    datoCorrecto = true;
                    scanner.nextLine();
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

        //Menú del sistema de polizas
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\t\tSISTEMA DE POLIZAS " + sistemaPolizas.getNombre());
        System.out.println("--------------------------------------------------------------------------------------------");
        do {
            opcionCorrecta = false;
            System.out.println("Digite la opcion que quiere ejecutar: ");
            System.out.println("\t(1) Iniciar registro cita");
            System.out.println("\t(2) Actualizar monto pagado");
            System.out.println("\t(3) Salir");
            while (opcionCorrecta == false) {
                try {
                    System.out.print("Respuesta: ");
                    opcionMenu = scanner.nextInt();
                    opcionCorrecta = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    scanner.nextLine();
                }
            }
            switch (opcionMenu) {
                case 1:
                    System.out.println("--------------------------------------------------------------------------------------------");
                    opcionA(sistemaPolizas, repositorioPolizas, usuarioLogIn, scanner);
                    System.out.println();
                    break;
                case 2:
                    System.out.println("--------------------------------------------------------------------------------------------");
                    actualizarMontoPagado(sistemaPolizas, repositorioPolizas, scanner);
                    actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("Gracias, vuelva pronto");
                    System.out.println();
                    break;
                default:
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("El numero ingresado no corresponde a ninguna de las opciones permitidas");
                    System.out.println();
                    break;
            }
        } while (opcionMenu != 3);
        scanner.close();
    }

    //Actuliza el repositorio de polizas para que el usuario interactue con los datos actualizados
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
    public static void agregarReclamoCita(SistemaPolizas sistemaPolizas, RepositorioPolizas repositorioPolizas, Scanner scanner){
        sistemaPolizas.mostrarPolizas();
        System.out.println("Seleccione el id de la poliza: ");
        Integer idPoliza = scanner.nextInt();

        // Buscar la póliza
        Poliza polizaSeleccionada = sistemaPolizas.buscarPoliza(idPoliza);
        if (polizaSeleccionada != null) {
            Integer idPaciente = polizaSeleccionada.getPaciente().getId();
            Cita citaSeleccionada = null;

            // Mostrar las citas del paciente asociado a la póliza anterior, basicamente la cita y la poliza se relacionan por el paciente id
            sistemaPolizas.mostrarCitas(idPaciente);
            System.out.println("Ingrese el id de la cita en donde desea añadir la reclamación: ");
            Integer idCita = scanner.nextInt();

            // Se busca la cita
            for (Cita cita : sistemaPolizas.getCitas()) {
                if (cita.getId().equals(idCita) && cita.getPaciente().getId().equals(idPaciente)) {
                    citaSeleccionada = cita;
                    break;
                }
            }
            if (citaSeleccionada != null) {
                System.out.println("Ingrese el monto de la reclamación: ");
                Integer montoReclamado = scanner.nextInt();

                // Crear y agregar una nueva reclamación a la cita anterior
                Reclamacion nuevaReclamacion = new Reclamacion();
                nuevaReclamacion.setMontoDemandado(montoReclamado);

                Integer montoPagado = 0;
                nuevaReclamacion.setMontoPagado(montoPagado);
                nuevaReclamacion.setFechaIncidente(new java.sql.Date(new Date().getTime()));
                nuevaReclamacion.setFechaRegistro(new java.sql.Date(new Date().getTime()));
                nuevaReclamacion.setFechaPago(new java.sql.Date(new Date().getTime()));
                nuevaReclamacion.setCita(citaSeleccionada);
                nuevaReclamacion.setPoliza(polizaSeleccionada);

                citaSeleccionada.getReclamaciones().add(nuevaReclamacion);
                repositorioPolizas.insertarReclamoCita(nuevaReclamacion);
                System.out.println("Reclamación agregada a la cita");
                repositorioPolizas.listarReclamosCita(citaSeleccionada);
                sistemaPolizas.totalizarCita(citaSeleccionada.getId());
            }
            else {
                System.out.println("No se encontró una cita para el paciente asociado a esta póliza");
            }
        }
        else {
            System.out.println("No se encontró la póliza con el id proporcionado");
        }
    }

    //Opción A, 3
    public static void listarReclamosCita(RepositorioPolizas repositorioPolizas, Scanner scanner) {
        // Solicitar al usuario el ID de la cita
        System.out.println("Ingrese el id de la cita: ");
        int idCita = scanner.nextInt();

        // Crear la cita con el ID proporcionado
        Cita cita = new Cita();
        cita.setId(idCita);

        // Llamar al método para listar los reclamos de la cita
        repositorioPolizas.listarReclamosCita(cita);
    }

    //Opción A, 4
    public static void eliminarReclamoCita(SistemaPolizas sistemaPolizas, RepositorioPolizas repositorioPolizas, Scanner scanner){
        sistemaPolizas.mostrarPacientes();
        System.out.println("Ingrese el id del paciente: ");
        Integer idPaciente = scanner.nextInt();

        Paciente paciente = sistemaPolizas.buscarPaciente(idPaciente);
        if (paciente != null) {
            sistemaPolizas.mostrarCitas(paciente.getId());
            System.out.println("Ingrese el id de la cita: ");
            Integer idCita = scanner.nextInt();
            Cita cita = sistemaPolizas.buscarCita(idCita);
            if (cita != null) {
                sistemaPolizas.mostrarReclamos(cita.getId());
                System.out.println("Ingrese el id de la reclamación que desea eliminar: ");
                Integer idReclamo = scanner.nextInt();
                for (Reclamacion reclamo : cita.getReclamaciones()) {
                    if(reclamo.getId().equals(idReclamo)) {
                        cita.getReclamaciones().remove(reclamo);
                        repositorioPolizas.elimReclamoCita(reclamo);
                        System.out.println("Reclamación eliminado con éxito");
                        repositorioPolizas.listarReclamosCita(cita);
                        sistemaPolizas.totalizarCita(cita.getId());
                        break;
                    }
                }
            }
        }
    }

    //Opción A, 5
    public static void modificarMontoReclamadoReclamoCita(SistemaPolizas sistemaPolizas, RepositorioPolizas repositorioPolizas, Scanner scanner) {
        // Listar citas
        sistemaPolizas.mostrarCitas();
        System.out.println("Digite el id de la cita: ");
        int idCita = scanner.nextInt();
        Cita cita = sistemaPolizas.buscarCita(idCita);

        // Listar reclamos de la cita
        repositorioPolizas.listarReclamosCita(cita);
        System.out.println("Digite el id del reclamo a modificar: ");
        int idReclamo = scanner.nextInt();

        // Solicitar el nuevo monto
        System.out.println("Digite el nuevo monto reclamado: ");
        Integer nuevoMonto = scanner.nextInt();

        repositorioPolizas.modificarMontoReclamado(idReclamo, nuevoMonto);
        actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
        repositorioPolizas.listarReclamosCita(cita);
        sistemaPolizas.totalizarCita(idCita);
    }

    //Opción A, 6
    public static void totalizarCita(SistemaPolizas sistemaPolizas, Scanner scanner) {
        System.out.println("Digite el id de la cita que desea totalizar: ");
        int idCita = scanner.nextInt();
        sistemaPolizas.totalizarCita(idCita);
    }


    //Opción A, 7
    public static void consultarCitasAlmacenadas(RepositorioPolizas repositorioPolizas, MiembroPersonal usuarioLogIn, Scanner scanner) {
        System.out.println("Digite el año (YYYY): ");
        int anio = scanner.nextInt();

        System.out.println("Digite el mes (MM): ");
        int mes = scanner.nextInt();

        repositorioPolizas.consultarCitasAlmacenadas(usuarioLogIn, anio, mes);
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
                    opcionCorrecta = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    scanner.nextLine();
                }
            }
            switch (opcionMenu) {
                case 1:
                    System.out.println("--------------------------------------------------------------------------------------------");
                    iniciarRegistroCita(sistemaPolizas, repositorioPolizas, miembroPersonal, scanner);
                    actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
                    break;
                case 2:
                    System.out.println("--------------------------------------------------------------------------------------------");
                    agregarReclamoCita(sistemaPolizas, repositorioPolizas, scanner);
                    actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
                    break;
                case 3:
                    System.out.println("--------------------------------------------------------------------------------------------");
                    listarReclamosCita(repositorioPolizas, scanner);
                    actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
                    break;
                case 4:
                    System.out.println("--------------------------------------------------------------------------------------------");
                    eliminarReclamoCita(sistemaPolizas, repositorioPolizas, scanner);
                    actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
                    break;
                case 5:
                    System.out.println("--------------------------------------------------------------------------------------------");
                    modificarMontoReclamadoReclamoCita(sistemaPolizas, repositorioPolizas, scanner);
                    actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
                    break;
                case 6:
                    System.out.println("--------------------------------------------------------------------------------------------");
                    totalizarCita(sistemaPolizas,scanner);
                    actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
                    break;
                case 7:
                    System.out.println("--------------------------------------------------------------------------------------------");
                    consultarCitasAlmacenadas(repositorioPolizas, miembroPersonal, scanner);
                    actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
                    break;
                case 8:
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("Saliendo...");
                    actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
                    break;
                default:
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("El numero ingresado no corresponde a ninguna de las opciones permitidas");
                    break;
            }
        } while (opcionMenu != 8);
    }

    //Opción B
    public static void actualizarMontoPagado(SistemaPolizas sistemaPolizas, RepositorioPolizas repositorioPolizas, Scanner scanner) {

        repositorioPolizas.listarCitasConReclamosSinMontoPagado();
        System.out.println("Digite el id de la cita: ");
        int idCita = scanner.nextInt();
        Cita cita = sistemaPolizas.buscarCita(idCita);

        repositorioPolizas.listarReclamosCita(cita);
        System.out.println("Digite el id del reclamo a modificar: ");
        int idReclamo = scanner.nextInt();

        System.out.println("Digite el monto pagado: ");
        double montoPagado = scanner.nextDouble();

        repositorioPolizas.actualizarMontoPagado(idReclamo, montoPagado);
        actulizarRepositorioPolizas(sistemaPolizas, repositorioPolizas);
        repositorioPolizas.listarReclamosCita(cita);
        sistemaPolizas.totalizarCita(idCita);
    }
}
