package org.example.integracion;

import org.example.dominio.*;

import java.sql.*;
import java.util.List;

public class RepositorioPolizas {
    public String sexo(Integer idTipoSexo){
        String[] tipoSexo = new String[]{"Hombre", "Mujer"};
        return tipoSexo[idTipoSexo-1];
    }

    public String cargo(Integer idTipoCargo){
        String[] tipoCargo = new String[]{"Medico", "Enfermero"};
        return tipoCargo[idTipoCargo-1];
    }

    public void cargarMiembrosPersonal(List<MiembroPersonal> miembrosPersonal){
        miembrosPersonal.clear();
        String traerMiembrosPersonal = "SELECT * FROM MIEMBRO_PERSONAL";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerMiembrosPersonal);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                MiembroPersonal miembroPersonal = new MiembroPersonal();
                miembroPersonal.setId(resultSet.getInt("ID"));
                miembroPersonal.setCedula(resultSet.getInt("CEDULA"));
                miembroPersonal.setNombre(resultSet.getString("NOMBRE"));
                Integer idTipoSexo = resultSet.getInt("ID_TIPO_SEXO");
                miembroPersonal.setSexo(sexo(idTipoSexo));
                miembroPersonal.setDireccion(resultSet.getString("DIRECCION"));
                Integer idTipoCargo = resultSet.getInt("ID_TIPO_CARGO");
                miembroPersonal.setCargo(cargo(idTipoCargo));
                miembrosPersonal.add(miembroPersonal);
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void cargarPacientes(List<Paciente> pacientes){
        pacientes.clear();
        String traerPacientes = "SELECT * FROM PACIENTE";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerPacientes);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Paciente paciente = new Paciente();
                paciente.setId(resultSet.getInt("ID"));
                paciente.setCedula(resultSet.getInt("CEDULA"));
                paciente.setNombre(resultSet.getString("NOMBRE"));
                Integer idTipoSexo = resultSet.getInt("ID_TIPO_SEXO");
                paciente.setSexo(sexo(idTipoSexo));
                paciente.setFechaNacimiento(resultSet.getDate("FECHANACIMIENTO"));
                paciente.setDireccion(resultSet.getString("DIRECCION"));
                pacientes.add(paciente);
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void insertarCita(Cita cita){
        String insertarCita = "INSERT INTO VALUES (ID_PACIENTE, ID_MIEMBRO_PERSONAL, FECHA_REGISTRO) VALUES (?, ?, ?)";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(insertarCita);
            preparedStatement.setInt(1, cita.getPaciente().getId());
            preparedStatement.setInt(2, cita.getMiembroPersonal().getId());
            preparedStatement.setDate(3, (Date) cita.getFechaRegistro());
            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas < 1) {
                System.out.println("No se pudo agregar la cita " + cita.getId() + " a la base de datos");
                return;
            }
            connection.commit();
        }catch (Exception e){
            e.getMessage();
        }
    }
}
