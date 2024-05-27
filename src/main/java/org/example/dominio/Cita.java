package org.example.dominio;

import java.util.Date;
import java.util.List;

public class Cita {
    private Integer id;
    private java.sql.Date fechaRegistro;
    private Paciente paciente;
    private MiembroPersonal miembroPersonal;
    List<Reclamacion> reclamaciones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.sql.Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(java.sql.Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getCostoConsulta() {
        return costoConsulta;
    }

    public void setCostoConsulta(Integer costoConsulta) {
        this.costoConsulta = costoConsulta;
    }

    public Integer getCostoPagado() {
        return costoPagado;
    }

    public void setCostoPagado(Integer costoPagado) {
        this.costoPagado = costoPagado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Reclamacion> getReclamaciones() {
        return reclamaciones;
    }

    public void setReclamaciones(List<Reclamacion> reclamaciones) {
        this.reclamaciones = reclamaciones;
    }

    public MiembroPersonal getMiembroPersonal() {
        return miembroPersonal;
    }

    public void setMiembroPersonal(MiembroPersonal miembroPersonal) {
        this.miembroPersonal = miembroPersonal;
    }
}
