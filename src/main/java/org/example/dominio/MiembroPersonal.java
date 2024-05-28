package org.example.dominio;

import java.util.List;

public class MiembroPersonal {
    //Atributos de instancia
    private Integer id;
    private Long cedula;
    private String nombre;
    private String sexo;
    private String direccion;
    private String cargo;
    private String contrasenia;
    private List<Cita> citas;

    //Métodos constructores
    public MiembroPersonal() {
    }

    public MiembroPersonal(Integer id, Long cedula, String nombre, String sexo, String direccion, String cargo, String contrasenia, List<Cita> citas) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.sexo = sexo;
        this.direccion = direccion;
        this.cargo = cargo;
        this.contrasenia = contrasenia;
        this.citas = citas;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
