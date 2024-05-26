package org.example.dominio;

import java.util.Date;
import java.util.List;

public class Paciente {
    private Integer id;
    private String cedula;
    private String nombre;
    private Integer sexo;
    private Date fechaNacimiento;
    private String direccion;
    private String contrasenia;
    private List<Cita> citas;
}
