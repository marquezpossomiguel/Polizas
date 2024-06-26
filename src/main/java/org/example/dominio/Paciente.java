package org.example.dominio;

import java.sql.Date;
import java.util.List;

public class Paciente {
    //Atributos de instancia
    private Integer id;
    private Long cedula;
    private String nombre;
    private String sexo;
    private java.sql.Date fechaNacimiento;
    private String direccion;
    private List<Cita> citas;
    private List<Poliza> polizas;

    //Métodos constructores
    public Paciente() {
    }

    public Paciente(Integer id, Long cedula, String nombre, String sexo, Date fechaNacimiento, String direccion, List<Cita> citas, List<Poliza> polizas) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.citas = citas;
        this.polizas = polizas;
    }

    //Métodos de acceso
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
}
