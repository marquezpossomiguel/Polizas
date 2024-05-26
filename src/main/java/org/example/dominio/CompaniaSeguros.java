package org.example.dominio;

import java.util.List;

public class CompaniaSeguros {
    private Integer id;
    private Integer nit;
    private String nombre;
    private String direccion;
    private String direccionIp;
    private Contacto representante;
    private Pais pais;
    List<Poliza> polizas;
}
