package org.example.dominio;

import java.sql.Date;
import java.util.List;

public class Cita {
    //Atributos de instancia
    private Integer id;
    private Paciente paciente;
    private MiembroPersonal miembroPersonal;
    private java.sql.Date fechaProgramada;
    private java.sql.Date fechaRegistro;
    List<Reclamacion> reclamaciones;

    //Métodos constructores
    public Cita() {
    }

    public Cita(Integer id, Paciente paciente, MiembroPersonal miembroPersonal, Date fechaProgramada, Date fechaRegistro, List<Reclamacion> reclamaciones) {
        this.id = id;
        this.paciente = paciente;
        this.miembroPersonal = miembroPersonal;
        this.fechaProgramada = fechaProgramada;
        this.fechaRegistro = fechaRegistro;
        this.reclamaciones = reclamaciones;
    }

    //Métodos de acceso
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public MiembroPersonal getMiembroPersonal() {
        return miembroPersonal;
    }

    public void setMiembroPersonal(MiembroPersonal miembroPersonal) {
        this.miembroPersonal = miembroPersonal;
    }

    public Date getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(Date fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Reclamacion> getReclamaciones() {
        return reclamaciones;
    }

    public void setReclamaciones(List<Reclamacion> reclamaciones) {
        this.reclamaciones = reclamaciones;
    }
}
