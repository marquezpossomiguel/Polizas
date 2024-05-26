package org.example.dominio;

import java.util.List;

public class SistemaPolizas {
    private String nombre;
    List<Paciente> pacientes;
    List<Usuario> usuarios;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
