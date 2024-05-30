package org.example.dominio;

import java.util.List;

public class SistemaPolizas {
    //Atributos de instancia
    private String nombre;
    private List<Paciente> pacientes;
    private List<MiembroPersonal> miembrosPersonal;
    private List<Cita> citas;
    private List<Poliza> polizas;
    private List<Reclamacion> reclamaciones;

    //Métodos constructores
    public SistemaPolizas() {
    }

    public SistemaPolizas(String nombre, List<Paciente> pacientes, List<MiembroPersonal> miembrosPersonal, List<Cita> citas, List<Poliza> polizas, List<Reclamacion> reclamaciones) {
        this.nombre = nombre;
        this.pacientes = pacientes;
        this.miembrosPersonal = miembrosPersonal;
        this.citas = citas;
        this.polizas = polizas;
        this.reclamaciones = reclamaciones;
    }

    //Métodos de acceso
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public List<MiembroPersonal> getMiembrosPersonal() {
        return miembrosPersonal;
    }

    public void setMiembrosPersonal(List<MiembroPersonal> miembrosPersonal) {
        this.miembrosPersonal = miembrosPersonal;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public List<Poliza> getPolizas() {
        return polizas;
    }

    public void setPolizas(List<Poliza> polizas) {
        this.polizas = polizas;
    }

    public List<Reclamacion> getReclamaciones() {
        return reclamaciones;
    }

    public void setReclamaciones(List<Reclamacion> reclamaciones) {
        this.reclamaciones = reclamaciones;
    }

    //Métodos de instancia
    public void mostrarPacientes() {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("PACIENTES");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Paciente paciente : pacientes) {
            System.out.println("Id: " + paciente.getId());
            System.out.println("Cedula: " + paciente.getCedula());
            System.out.println("Nombre: " + paciente.getNombre());
            System.out.println("Sexo: " + paciente.getSexo());
            System.out.println("Fecha nacimiento: " + paciente.getFechaNacimiento());
            System.out.println("Direccion: " + paciente.getDireccion());
            System.out.println("--------------------------------------------------------------------------------------------");
        }
    }

    public void mostrarCitas() {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("                             CITAS                               ");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Cita cita : citas) {
            System.out.println("ID de la Cita: " + cita.getId());
            System.out.println("Fecha Cita Programada: " + cita.getFechaProgramada());
            System.out.println("Fecha Cita Registro: " + cita.getFechaRegistro());
            System.out.println("Nombre del Paciente: " + cita.getPaciente().getNombre());
            System.out.println("Nombre del Miembro del Personal: " + cita.getMiembroPersonal().getNombre());
            System.out.println("--------------------------------------------------------------------------------------------");
        }
    }

    public void mostrarCitas(Integer idPaciente) {
        Paciente paciente = buscarPaciente(idPaciente);
        if (paciente != null) {
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("CITAS DEL PACIENTE: " + paciente.getNombre());
            System.out.println("--------------------------------------------------------------------------------------------");
            if (paciente.getCitas() != null && !paciente.getCitas().isEmpty()) {
                for (Cita cita : paciente.getCitas()) {
                    System.out.println("ID: " + cita.getId());
                    System.out.println("ID del Paciente: " + cita.getPaciente().getId());
                    System.out.println("ID del Miembro de Personal: " + cita.getMiembroPersonal().getId());
                    System.out.println("Fecha Programada: " + cita.getFechaProgramada());
                    System.out.println("Fecha de Registro: " + cita.getFechaRegistro());
                    System.out.println("--------------------------------------------------------------------------------------------");
                }
            } else {
                System.out.println("No hay citas disponibles para este paciente.");
            }
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    public Paciente buscarPaciente(Integer idPaciente){
        for (Paciente paciente : pacientes) {
            if(paciente.getId().equals(idPaciente)){
                return paciente;
            }
        }
        return null;
    }

    public Cita buscarCita(Integer idCita){
        for (Cita cita : citas) {
            if(cita.getId().equals(idCita)){
                return cita;
            }
        }
        return null;
    }

    public void totalizarCita(int idCita) {
        Cita cita = buscarCita(idCita);
        if (cita != null) {
            double totalMontosReclamados = calcularTotalMontosReclamados(cita);
            double totalMontosPagados = calcularTotalMontosPagados(cita);
            double diferencia = totalMontosReclamados - totalMontosPagados;

            System.out.println("Total Montos Reclamados: " + totalMontosReclamados);
            System.out.println("Total Montos Pagados: " + totalMontosPagados);
            System.out.println("Diferencia: " + diferencia);
        } else {
            System.out.println("No se encontró una cita con el ID proporcionado.");
        }
    }

    private double calcularTotalMontosReclamados(Cita cita) {
        double total = 0.0;
        for (Reclamacion reclamacion : cita.getReclamaciones()) {
            total += reclamacion.getMontoDemandado();
        }
        return total;
    }

    private double calcularTotalMontosPagados(Cita cita) {
        double total = 0.0;
        for (Reclamacion reclamacion : cita.getReclamaciones()) {
            if (reclamacion.getMontoPagado() != null) {
                total += reclamacion.getMontoPagado();
            }
        }
        return total;
    }

    public void mostrarPolizas () {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("PÓLIZAS");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Poliza poliza : polizas) {
            System.out.println("ID: " + poliza.getId());
            System.out.println("Número de Póliza: " + poliza.getNumero());
            System.out.println("Cobertura: " + poliza.getCobertura());
            System.out.println("Paciente: " + poliza.getPaciente().getNombre());
            System.out.println("Compañía de Seguros: " + poliza.getCompaniaSeguros().getNombre());
            System.out.println("--------------------------------------------------------------------------------------------");
        }
    }

    public Poliza buscarPoliza(Integer idPoliza){
        for (Poliza poliza : polizas) {
            if(poliza.getId().equals(idPoliza)){
                return poliza;
            }
        }
        return null;
    }

    public void mostrarReclamos(Integer idCita) {
        Cita cita = buscarCita(idCita);
        if (cita != null) {
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("RECLAMOS DE LA CITA: " + cita.getId());
            System.out.println("--------------------------------------------------------------------------------------------");
            if (cita.getReclamaciones() != null && !cita.getReclamaciones().isEmpty()) {
                for (Reclamacion reclamacion : cita.getReclamaciones()) {
                    System.out.println("ID: " + reclamacion.getId());
                    System.out.println("Accion: " + reclamacion.getAccion());
                    System.out.println("Fecha Incidente: " + reclamacion.getFechaIncidente());
                    System.out.println("Fecha Registro: " + reclamacion.getFechaRegistro());
                    System.out.println("Monto Demandado: " + reclamacion.getMontoDemandado());
                    System.out.println("Monto Pagado: " + reclamacion.getMontoPagado());
                    System.out.println("Fecha Pago: " + reclamacion.getFechaPago());
                    System.out.println("Codigo Reclamacion: " + reclamacion.getCodigoReclamacion());
                    System.out.println("Codigo No Pago: " + reclamacion.getCodigoNoPago());
                    System.out.println("--------------------------------------------------------------------------------------------");
                }
            } else {
                System.out.println("No hay reclamaciones disponibles para esta cita.");
            }
        } else {
            System.out.println("Cita no encontrada.");
        }
    }
}
