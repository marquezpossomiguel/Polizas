package org.example.dominio;

public class Poliza {
    //Atributos de instancia
    private Integer id;
    private Integer numero;
    private Paciente paciente;
    private CompaniaSeguros companiaSeguros;
    private String cobertura;

    //Métodos constructores
    public Poliza() {
    }

    public Poliza(Integer id, Integer numero, Paciente paciente, CompaniaSeguros companiaSeguros, String cobertura) {
        this.id = id;
        this.numero = numero;
        this.paciente = paciente;
        this.companiaSeguros = companiaSeguros;
        this.cobertura = cobertura;
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
}
