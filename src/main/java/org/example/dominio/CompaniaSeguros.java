package org.example.dominio;

import java.util.List;

public class CompaniaSeguros {
    //Atributos de instancia
    private Integer id;
    private Long nit;
    private String nombre;
    private String direccion;
    private String direccionIp;
    private List<Poliza> polizas;

    //Métodos constructores
    public CompaniaSeguros() {
    }

    public CompaniaSeguros(Integer id, Long nit, String nombre, String direccion, String direccionIp, List<Poliza> polizas) {
        this.id = id;
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.direccionIp = direccionIp;
        this.polizas = polizas;
    }

    //Métodos de acceso
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public List<Poliza> getPolizas() {
        return polizas;
    }

    public void setPolizas(List<Poliza> polizas) {
        this.polizas = polizas;
    }
}
