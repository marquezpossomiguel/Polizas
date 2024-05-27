package org.example.dominio;

import java.sql.Date;

public class Reclamacion {
    private Integer id;
    private String accion;
    private java.sql.Date fechaIncidente;
    private java.sql.Date fechaRegistro;
    private Integer montoDemandado;
    private Integer montoPagado;
    private java.sql.Date fechaPago;
    private String codigoReclamacion;
    private String codigoNoPago;
    private Cita cita;
    private Poliza poliza;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Date getFechaIncidente() {
        return fechaIncidente;
    }

    public void setFechaIncidente(Date fechaIncidente) {
        this.fechaIncidente = fechaIncidente;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getMontoDemandado() {
        return montoDemandado;
    }

    public void setMontoDemandado(Integer montoDemandado) {
        this.montoDemandado = montoDemandado;
    }

    public Integer getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(Integer montoPagado) {
        this.montoPagado = montoPagado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getCodigoReclamacion() {
        return codigoReclamacion;
    }

    public void setCodigoReclamacion(String codigoReclamacion) {
        this.codigoReclamacion = codigoReclamacion;
    }

    public String getCodigoNoPago() {
        return codigoNoPago;
    }

    public void setCodigoNoPago(String codigoNoPago) {
        this.codigoNoPago = codigoNoPago;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Poliza getPoliza() {
        return poliza;
    }

    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }
}
