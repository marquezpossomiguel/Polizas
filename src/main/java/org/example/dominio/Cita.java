package org.example.dominio;

import java.util.Date;
import java.util.List;

public class Cita {
    private Integer id;
    private Date fechaHoraProgramada;
    private Date fechaHoraRegistro;
    private Integer costoConsulta;
    private Integer costoPagado;
    private Paciente paciente;
    List<Motivo> motivos;
    List<Reclamacion> reclamaciones;
}
