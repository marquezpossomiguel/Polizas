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

    public String reclamacion(Integer idReclamacion){
        String[] codigoReclamacion = new String[]{"Neglicencia", "Demora"};
        return codigoReclamacion[idReclamacion-1];
    }

    public String noPago(Integer idNoPago){
        String[] codigoNoPago = new String[]{"Falta de plata", "Fuera de plazo"};
        return codigoNoPago[idNoPago-1];
    }

    public Paciente cargarPaciente(Integer idPaciente){
        String traerPaciente = "SELECT * FROM PACIENTE WHERE ID = ?";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerPaciente);
            preparedStatement.setInt(1, idPaciente);
            ResultSet resultSet = preparedStatement.executeQuery();
            Paciente paciente = new Paciente();
            if(resultSet.next()){
                paciente.setId(resultSet.getInt("ID"));
                paciente.setCedula(resultSet.getInt("CEDULA"));
                paciente.setNombre(resultSet.getString("NOMBRE"));
                Integer idTipoSexo = resultSet.getInt("ID_TIPO_SEXO");
                paciente.setSexo(sexo(idTipoSexo));
                paciente.setFechaNacimiento(resultSet.getDate("FECHANACIMIENTO"));
                paciente.setDireccion(resultSet.getString("DIRECCION"));
            }
            return paciente;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void cargarCitas(MiembroPersonal miembroPersonal){
        String traerCitas = "SELECT * FROM CITA WHERE ID_MIEMBRO_PERSONAL = ?";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerCitas);
            preparedStatement.setInt(1, miembroPersonal.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Cita cita = new Cita();
                cita.setId(resultSet.getInt("ID"));
                Integer idPaciente = resultSet.getInt("ID_PACIENTE");
                cita.setPaciente(cargarPaciente(idPaciente));
                cita.setMiembroPersonal(miembroPersonal);
                cita.setFechaProgramada(resultSet.getDate("FECHA_HORA_PROGRAMADA"));
                miembroPersonal.getCitas().add(cita);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cargarMiembrosPersonal(SistemaPolizas sistemaPolizas){
        sistemaPolizas.getMiembrosPersonal().clear();
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
                cargarCitas(miembroPersonal);
                sistemaPolizas.getMiembrosPersonal().add(miembroPersonal);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public MiembroPersonal cargarMiembroPersonal(Integer idMiembroPersonal){
        String traerMiembroPersonal = "SELECT * FROM MIEMBRO_PERSONAL WHERE ID = ?";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerMiembroPersonal);
            preparedStatement.setInt(1, idMiembroPersonal);
            ResultSet resultSet = preparedStatement.executeQuery();
            MiembroPersonal miembroPersonal = new MiembroPersonal();
            if(resultSet.next()){
                miembroPersonal.setId(resultSet.getInt("ID"));
                miembroPersonal.setCedula(resultSet.getInt("CEDULA"));
                miembroPersonal.setNombre(resultSet.getString("NOMBRE"));
                Integer idTipoSexo = resultSet.getInt("ID_TIPO_SEXO");
                miembroPersonal.setSexo(sexo(idTipoSexo));
                miembroPersonal.setDireccion(resultSet.getString("DIRECCION"));
                Integer idTipoCargo = resultSet.getInt("ID_TIPO_CARGO");
                miembroPersonal.setCargo(cargo(idTipoCargo));
            }
            return miembroPersonal;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void cargarCitas(Paciente paciente){
        String traerCitas = "SELECT * FROM CITA WHERE ID_MIEMBRO_PERSONAL = ?";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerCitas);
            preparedStatement.setInt(1, paciente.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Cita cita = new Cita();
                cita.setId(resultSet.getInt("ID"));
                cita.setPaciente(paciente);
                Integer idMiembroPersonal = resultSet.getInt("ID_MIEMBRO_PERSONAL");
                cita.setMiembroPersonal(cargarMiembroPersonal(idMiembroPersonal));
                cita.setFechaProgramada(resultSet.getDate("FECHA_HORA_PROGRAMADA"));
                paciente.getCitas().add(cita);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cargarPacientes(SistemaPolizas sistemaPolizas){
        sistemaPolizas.getPacientes().clear();
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
                cargarCitas(paciente);
                sistemaPolizas.getPacientes().add(paciente);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------

    public Poliza cargarPoliza(Integer idPoliza){
        String traerPolizas = "SELECT * FROM POLIZA WHERE ID = ?";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerPolizas);
            preparedStatement.setInt(1, idPoliza);
            ResultSet resultSet = preparedStatement.executeQuery();
            Poliza poliza = new Poliza();
            if(resultSet.next()){

            }
            return poliza;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void cargarReclamacion(Cita cita){
        String traerReclamaciones = "SELECT * FROM RECLAMACION WHERE ID = ?";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerReclamaciones);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Reclamacion reclamacion = new Reclamacion();
                reclamacion.setId(resultSet.getInt("ID"));
                reclamacion.setAccion(resultSet.getString("ACCION"));
                reclamacion.setFechaIncidente(resultSet.getDate("FECHA_INCIDENTE"));
                reclamacion.setFechaRegistro(resultSet.getDate("FECHA_REGISTRO"));
                reclamacion.setMontoDemandado(resultSet.getInt("MONTO_DEMANDADO"));
                reclamacion.setMontoPagado(resultSet.getInt("MONTO_PAGADO"));
                reclamacion.setFechaPago(resultSet.getDate("FECHA_PAGO"));
                Integer idCodigoReclamacion = resultSet.getInt("ID_CODIGO_RECLAMACION");
                reclamacion.setCodigoReclamacion(reclamacion(idCodigoReclamacion));
                Integer idCodigoNoPago = resultSet.getInt("ID_CODIGO_NO_PAGO");
                reclamacion.setCodigoNoPago(noPago(idCodigoNoPago));
                reclamacion.setCita(cita);
                Integer idPoliza = resultSet.getInt("ID_POLIZA");
                reclamacion.setPoliza(cargarPoliza(idPoliza));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cargarCitas(SistemaPolizas sistemaPolizas){
        sistemaPolizas.getCitas().clear();
        String traerCitas = "SELECT * FROM CITA";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerCitas);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Cita cita = new Cita();
                cita.setId(resultSet.getInt("ID"));
                Integer idPaciente = resultSet.getInt("ID_PACIENTE");
                cita.setPaciente(cargarPaciente(idPaciente));
                Integer idMiembroPersonal = resultSet.getInt("ID_MIEMBRO_PERSONAL");
                cita.setMiembroPersonal(cargarMiembroPersonal(idMiembroPersonal));
                cita.setFechaProgramada(resultSet.getDate("FECHA_HORA_PROGRAMADA"));
                sistemaPolizas.getCitas().add(cita);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cargarReclamaciones(Cita cita){
        String traerReclamaciones = "SELECT * FROM RECLAMACION WHERE ID_CITA = ?";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerReclamaciones);
            preparedStatement.setInt(1, cita.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Reclamacion reclamacion = new Reclamacion();

            }
        }catch (Exception e){
            e.printStackTrace();
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
            preparedStatement.setDate(3, cita.getFechaRegistro());
            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas < 1) {
                System.out.println("No se pudo agregar la cita " + cita.getId() + " a la base de datos");
                return;
            }
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cargarCita(Reclamacion reclamacion){
        String traerCitas = "SELECT * FROM CITA WHERE ID = ?";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerCitas);
            preparedStatement.setInt(1, reclamacion.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Cita cita = new Cita();
                cita.setId(resultSet.getInt("ID"));
                Integer idPaciente = resultSet.getInt("ID_PACIENTE");
                cita.setPaciente(cargarPaciente(idPaciente));
                Integer idMiembroPersonal = resultSet.getInt("ID_MIEMBRO_PERSONAL");
                cita.setMiembroPersonal(cargarMiembroPersonal(idMiembroPersonal));
                cita.setFechaProgramada(resultSet.getDate("FECHA_HORA_PROGRAMADA"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cargarReclamaciones(SistemaPolizas sistemaPolizas){
        sistemaPolizas.getReclamaciones().clear();
        String traerReclamaciones = "SELECT * FROM RECLAMACION";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerReclamaciones);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Reclamacion reclamacion = new Reclamacion();
                reclamacion.setId(resultSet.getInt("ID"));
                reclamacion.setMontoDemandado(resultSet.getInt("MONTO_DEMANDADO"));
                reclamacion.setMontoPagado(resultSet.getInt("MONTO_PAGADO"));
                Integer idCita = resultSet.getInt("ID_CITA");
                cargarCita(reclamacion);
                sistemaPolizas.getReclamaciones().add(reclamacion);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cargarPolizas(SistemaPolizas sistemaPolizas){
        sistemaPolizas.getPolizas().clear();
        String traerPolizas = "SELECT * FROM POLIZA";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerPolizas);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Poliza poliza = new Poliza();
                sistemaPolizas.getPolizas().add(poliza);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
