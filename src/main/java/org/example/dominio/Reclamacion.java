package org.example.dominio;

import java.sql.Date;

public class Reclamacion {
    //Atributos de instancia
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

    //Métodos constructores
    public Reclamacion() {
    }

    public Reclamacion(Integer id, String accion, Date fechaIncidente, Date fechaRegistro, Integer montoDemandado, Integer montoPagado, Date fechaPago, String codigoReclamacion, String codigoNoPago, Cita cita, Poliza poliza) {
        this.id = id;
        this.accion = accion;
        this.fechaIncidente = fechaIncidente;
        this.fechaRegistro = fechaRegistro;
        this.montoDemandado = montoDemandado;
        this.montoPagado = montoPagado;
        this.fechaPago = fechaPago;
        this.codigoReclamacion = codigoReclamacion;
        this.codigoNoPago = codigoNoPago;
        this.cita = cita;
        this.poliza = poliza;
    }

    //Métodos de acceso
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
