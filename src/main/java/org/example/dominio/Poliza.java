package org.example.dominio;

import java.util.List;

public class Poliza {
    //Atributos de instancia
    private Integer id;
    private Integer numero;
    private Paciente paciente;
    private CompaniaSeguros companiaSeguros;
    private String cobertura;
    private List<Reclamacion> reclamaciones;

    //Métodos constructores
    public Poliza() {
    }

    public Poliza(Integer id, Integer numero, Paciente paciente, CompaniaSeguros companiaSeguros, String cobertura, List<Reclamacion> reclamaciones) {
        this.id = id;
        this.numero = numero;
        this.paciente = paciente;
        this.companiaSeguros = companiaSeguros;
        this.cobertura = cobertura;
        this.reclamaciones = reclamaciones;
    }

    //Métodos de acceso
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public CompaniaSeguros getCompaniaSeguros() {
        return companiaSeguros;
    }

    public void setCompaniaSeguros(CompaniaSeguros companiaSeguros) {
        this.companiaSeguros = companiaSeguros;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public List<Reclamacion> getReclamaciones() {
        return reclamaciones;
    }

    public void setReclamaciones(List<Reclamacion> reclamaciones) {
        this.reclamaciones = reclamaciones;
    }
}
