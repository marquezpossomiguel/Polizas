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
        System.out.println("-----------------------------------------------------------------");
        System.out.println("PACIENTES");
        System.out.println("-----------------------------------------------------------------");
        for (Paciente paciente : pacientes) {
            System.out.println("Id: " + paciente.getId());
            System.out.println("Cedula: " + paciente.getCedula());
            System.out.println("Nombre: " + paciente.getNombre());
            System.out.println("Sexo: " + paciente.getSexo());
            System.out.println("Fecha nacimiento: " + paciente.getFechaNacimiento());
            System.out.println("Direccion: " + paciente.getDireccion());
            System.out.println("-----------------------------------------------------------------");
        }
    }
}
