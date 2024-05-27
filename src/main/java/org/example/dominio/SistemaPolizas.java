package org.example.dominio;

import java.util.List;

public class SistemaPolizas {
    private String nombre;
    List<Paciente> pacientes;
    List<MiembroPersonal> miembrosPersonal;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<MiembroPersonal> getMiembrosPersonal() {
        return miembrosPersonal;
    }

    public void setMiembrosPersonal(List<MiembroPersonal> miembrosPersonal) {
        this.miembrosPersonal = miembrosPersonal;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

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
