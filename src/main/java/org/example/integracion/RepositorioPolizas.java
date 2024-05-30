package org.example.integracion;

import org.example.dominio.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPolizas {
    public String sexo(Integer idTipoSexo){
        if(idTipoSexo.equals(0)){
            return null;
        }
        String[] tipoSexo = new String[]{"Hombre", "Mujer"};
        return tipoSexo[idTipoSexo-1];
    }

    public String cargo(Integer idTipoCargo){
        if(idTipoCargo.equals(0)){
            return null;
        }
        String[] tipoCargo = new String[]{"Medico/a general", "Medico/a especialista", "Enfermero/a", "Psicologo/a Clinico"};
        return tipoCargo[idTipoCargo-1];
    }

    public String reclamacion(Integer idReclamacion){
        if(idReclamacion.equals(0)){
            return null;
        }
        String[] codigoReclamacion = new String[]{"Atencion preventiva", "Beneficios por incapacidad total y permanente", "Gastos medicos cubiertos", "Cobertura de enfermedades graves", "Cobertura de enfermedades terminales", "Hospitalizacion"};
        return codigoReclamacion[idReclamacion-1];
    }

    public String noPago(Integer idNoPago){
        if(idNoPago.equals(0)){
            return null;
        }
        String[] codigoNoPago = new String[]{"Falta de plata", "Fuera de plazo"};
        return codigoNoPago[idNoPago-1];
    }

    public String cobertura(Integer idCobertura){
        if(idCobertura.equals(0)){
            return null;
        }
        String[] tipoCobertura = new String[]{"Vida: Individual", "Vida: Grupo", "Vida: Accidentes personales", "Salud: Medicina prepagada", "Salud: Hospitalizacion", "Salud: Cirugia"};
        return tipoCobertura[idCobertura-1];
    }

    //-------------------------------------------------------------------------------------------

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
                paciente.setCedula(resultSet.getLong("CEDULA"));
                paciente.setNombre(resultSet.getString("NOMBRE"));
                Integer idTipoSexo = resultSet.getInt("ID_TIPO_SEXO");
                paciente.setSexo(sexo(idTipoSexo));
                paciente.setFechaNacimiento(resultSet.getDate("FECHA_NACIMIENTO"));
                paciente.setDireccion(resultSet.getString("DIRECCION"));
                //No es necesario cargar la lista de citas y de polizas
            }
            return paciente;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public CompaniaSeguros cargarCompaniaSeguros(Integer idCompaniaSeguros){
        String traerCompaniaSeguros = "SELECT * FROM COMPANIA_SEGUROS WHERE ID = ?";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerCompaniaSeguros);
            preparedStatement.setInt(1, idCompaniaSeguros);
            ResultSet resultSet = preparedStatement.executeQuery();
            CompaniaSeguros companiaSeguros = new CompaniaSeguros();
            if(resultSet.next()){
                companiaSeguros.setId(resultSet.getInt("ID"));
                companiaSeguros.setNit(resultSet.getLong("NIT"));
                companiaSeguros.setNombre(resultSet.getString("NOMBRE"));
                companiaSeguros.setDireccion(resultSet.getString("DIRECCION"));
                companiaSeguros.setDireccionIp(resultSet.getString("DIRECCION_IP"));
            }
            return companiaSeguros;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Poliza cargarPoliza(Integer idPoliza){
        String traerPoliza = "SELECT * FROM POLIZA WHERE ID = ?";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerPoliza);
            preparedStatement.setInt(1, idPoliza);
            ResultSet resultSet = preparedStatement.executeQuery();
            Poliza poliza = new Poliza();
            if(resultSet.next()){
                poliza.setId(resultSet.getInt("ID"));
                poliza.setNumero(resultSet.getLong("NUMERO"));
                Integer idPaciente = resultSet.getInt("ID_PACIENTE");
                poliza.setPaciente(cargarPaciente(idPaciente));
                Integer idCompaniaSeguros = resultSet.getInt("ID_COMPANIA_SEGUROS");
                cargarCompaniaSeguros(idCompaniaSeguros);
                Integer idCobertura = resultSet.getInt("ID_TIPO_COBERTURA");
                poliza.setCobertura(cobertura(idCobertura));
            }
            return poliza;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void cargarReclamaciones(Cita cita){
        if (cita.getReclamaciones() == null) {
            cita.setReclamaciones(new ArrayList<>());
        } else {
            cita.getReclamaciones().clear();
        }
        String traerReclamaciones = "SELECT * FROM RECLAMACION WHERE ID_CITA = ?";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerReclamaciones);
            preparedStatement.setInt(1, cita.getId());
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
                cita.getReclamaciones().add(reclamacion);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cargarCitas(MiembroPersonal miembroPersonal){
        if (miembroPersonal.getCitas() == null) {
            miembroPersonal.setCitas(new ArrayList<>());
        } else {
            miembroPersonal.getCitas().clear();
        }
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
                cita.setFechaRegistro(resultSet.getDate("FECHA_HORA_REGISTRO"));
                cargarReclamaciones(cita);
                miembroPersonal.getCitas().add(cita);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cargarMiembrosPersonal(SistemaPolizas sistemaPolizas){
        if (sistemaPolizas.getMiembrosPersonal() == null) {
            sistemaPolizas.setMiembrosPersonal(new ArrayList<>());
        } else {
            sistemaPolizas.getMiembrosPersonal().clear();
        }
        String traerMiembrosPersonal = "SELECT * FROM MIEMBRO_PERSONAL";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerMiembrosPersonal);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                MiembroPersonal miembroPersonal = new MiembroPersonal();
                miembroPersonal.setId(resultSet.getInt("ID"));
                miembroPersonal.setCedula(resultSet.getLong("CEDULA"));
                miembroPersonal.setNombre(resultSet.getString("NOMBRE"));
                Integer idTipoSexo = resultSet.getInt("ID_TIPO_SEXO");
                miembroPersonal.setSexo(sexo(idTipoSexo));
                miembroPersonal.setDireccion(resultSet.getString("DIRECCION"));
                Integer idTipoCargo = resultSet.getInt("ID_TIPO_CARGO");
                miembroPersonal.setCargo(cargo(idTipoCargo));
                miembroPersonal.setContrasenia(resultSet.getString("CONTRASENIA"));
                cargarCitas(miembroPersonal);
                sistemaPolizas.getMiembrosPersonal().add(miembroPersonal);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------------------------------------------

    public void cargarPolizas(Paciente paciente){
        if (paciente.getPolizas() == null) {
            paciente.setPolizas(new ArrayList<>());
        } else {
            paciente.getPolizas().clear();
        }
        String traerPolizas = "SELECT * FROM POLIZA WHERE ID_PACIENTE = ?";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerPolizas);
            preparedStatement.setInt(1, paciente.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Poliza poliza = new Poliza();
                poliza.setId(resultSet.getInt("ID"));
                poliza.setNumero(resultSet.getLong("NUMERO"));
                poliza.setPaciente(paciente);
                Integer idCompaniaSeguros = resultSet.getInt("ID_COMPANIA_SEGUROS");
                poliza.setCompaniaSeguros(cargarCompaniaSeguros(idCompaniaSeguros));
                Integer idCobertura = resultSet.getInt("ID_TIPO_COBERTURA");
                poliza.setCobertura(cobertura(idCobertura));
                cargarReclamaciones(poliza);
                paciente.getPolizas().add(poliza);
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
                miembroPersonal.setCedula(resultSet.getLong("CEDULA"));
                miembroPersonal.setNombre(resultSet.getString("NOMBRE"));
                Integer idTipoSexo = resultSet.getInt("ID_TIPO_SEXO");
                miembroPersonal.setSexo(sexo(idTipoSexo));
                miembroPersonal.setDireccion(resultSet.getString("DIRECCION"));
                Integer idTipoCargo = resultSet.getInt("ID_TIPO_CARGO");
                miembroPersonal.setCargo(cargo(idTipoCargo));
                miembroPersonal.setContrasenia(resultSet.getString("CONTRASENIA"));
                //No es necesario cargar la lista de citas
            }
            return miembroPersonal;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void cargarCitas(Paciente paciente){
        if(paciente.getCitas() == null) {
            paciente.setCitas(new ArrayList<>());
        } else {
            paciente.getCitas().clear();
        }
        String traerCitas = "SELECT * FROM CITA WHERE ID_PACIENTE = ?";
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
                cita.setFechaRegistro(resultSet.getDate("FECHA_HORA_REGISTRO"));
                cargarReclamaciones(cita);
                paciente.getCitas().add(cita);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cargarPacientes(SistemaPolizas sistemaPolizas){
        if (sistemaPolizas.getPacientes() == null) {
            sistemaPolizas.setPacientes(new ArrayList<>());
        } else {
            sistemaPolizas.getPacientes().clear();
        }
        String traerPacientes = "SELECT * FROM PACIENTE";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerPacientes);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Paciente paciente = new Paciente();
                paciente.setId(resultSet.getInt("ID"));
                paciente.setCedula(resultSet.getLong("CEDULA"));
                paciente.setNombre(resultSet.getString("NOMBRE"));
                Integer idTipoSexo = resultSet.getInt("ID_TIPO_SEXO");
                paciente.setSexo(sexo(idTipoSexo));
                paciente.setFechaNacimiento(resultSet.getDate("FECHA_NACIMIENTO"));
                paciente.setDireccion(resultSet.getString("DIRECCION"));
                cargarCitas(paciente);
                cargarPolizas(paciente);
                sistemaPolizas.getPacientes().add(paciente);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------------------------------------------

    public void cargarCitas(SistemaPolizas sistemaPolizas){
        if (sistemaPolizas.getCitas() == null) {
            sistemaPolizas.setCitas(new ArrayList<>());
        } else {
            sistemaPolizas.getCitas().clear();
        }
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
                cita.setFechaRegistro(resultSet.getDate("FECHA_HORA_REGISTRO"));
                cargarReclamaciones(cita);
                sistemaPolizas.getCitas().add(cita);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------------------------------------------

    public Cita cargarCita(Integer idCita){
        String traerCita = "SELECT * FROM CITA WHERE ID = ?";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerCita);
            preparedStatement.setInt(1, idCita);
            ResultSet resultSet = preparedStatement.executeQuery();
            Cita cita = new Cita();
            if(resultSet.next()){
                cita.setId(resultSet.getInt("ID"));
                Integer idPaciente = resultSet.getInt("ID_PACIENTE");
                cita.setPaciente(cargarPaciente(idPaciente));
                Integer idMiembroPersonal = resultSet.getInt("ID_MIEMBRO_PERSONAL");
                cita.setMiembroPersonal(cargarMiembroPersonal(idMiembroPersonal));
                cita.setFechaProgramada(resultSet.getDate("FECHA_HORA_PROGRAMADA"));
                cita.setFechaRegistro(resultSet.getDate("FECHA_HORA_REGISTRO"));
            }
            return cita;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void cargarReclamaciones(Poliza poliza){
        if (poliza.getReclamaciones() == null) {
            poliza.setReclamaciones(new ArrayList<>());
        } else {
            poliza.getReclamaciones().clear();
        }
        String traerReclamaciones = "SELECT * FROM RECLAMACION WHERE ID_POLIZA = ?";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerReclamaciones);
            preparedStatement.setInt(1, poliza.getId());
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
                Integer idReclamacion = resultSet.getInt("ID_CODIGO_RECLAMACION");
                reclamacion.setCodigoReclamacion(reclamacion(idReclamacion));
                Integer idNoPago = resultSet.getInt("ID_CODIGO_NO_PAGO");
                reclamacion.setCodigoNoPago(noPago(idNoPago));
                Integer idCita = resultSet.getInt("ID_CITA");
                reclamacion.setCita(cargarCita(idCita));
                reclamacion.setPoliza(poliza);
                poliza.getReclamaciones().add(reclamacion);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cargarPolizas(SistemaPolizas sistemaPolizas){
        if (sistemaPolizas.getPolizas() == null) {
            sistemaPolizas.setPolizas(new ArrayList<>());
        } else {
            sistemaPolizas.getPolizas().clear();
        }
        String traerPolizas = "SELECT * FROM POLIZA";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerPolizas);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Poliza poliza = new Poliza();
                poliza.setId(resultSet.getInt("ID"));
                poliza.setNumero(resultSet.getLong("NUMERO"));
                Integer idPaciente = resultSet.getInt("ID_PACIENTE");
                poliza.setPaciente(cargarPaciente(idPaciente));
                Integer idCompaniaSeguros = resultSet.getInt("ID_COMPANIA_SEGUROS");
                poliza.setCompaniaSeguros(cargarCompaniaSeguros(idCompaniaSeguros));
                Integer idCobertura = resultSet.getInt("ID_TIPO_COBERTURA");
                poliza.setCobertura(cobertura(idCobertura));
                cargarReclamaciones(poliza);
                sistemaPolizas.getPolizas().add(poliza);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------------------------------------------

    public void cargarReclamaciones(SistemaPolizas sistemaPolizas){
        if (sistemaPolizas.getReclamaciones() == null){
            sistemaPolizas.setReclamaciones(new ArrayList<>());
        } else {
            sistemaPolizas.getReclamaciones().clear();
        }
        String traerReclamaciones = "SELECT * FROM RECLAMACION";
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
                Integer idReclamacion = resultSet.getInt("ID_CODIGO_RECLAMACION");
                reclamacion.setCodigoReclamacion(reclamacion(idReclamacion));
                Integer idNoPago = resultSet.getInt("ID_CODIGO_NO_PAGO");
                reclamacion.setCodigoNoPago(noPago(idNoPago));
                Integer idCita = resultSet.getInt("ID_CITA");
                reclamacion.setCita(cargarCita(idCita));
                Integer idPoliza = resultSet.getInt("ID_POLIZA");
                reclamacion.setPoliza(cargarPoliza(idPoliza));
                sistemaPolizas.getReclamaciones().add(reclamacion);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------------------------------------------

    public void insertarCita(Cita cita){
        String insertarCita = "INSERT INTO CITA (ID_PACIENTE, ID_MIEMBRO_PERSONAL, FECHA_HORA_PROGRAMADA) VALUES (?, ?, ?)";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(insertarCita);
            preparedStatement.setInt(1, cita.getPaciente().getId());
            preparedStatement.setInt(2, cita.getMiembroPersonal().getId());
            preparedStatement.setDate(3, cita.getFechaProgramada());
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

    public void consultarCitasAlmacenadas(MiembroPersonal usuario, int year, int month) {
        String comprobarCitasUsuario = "SELECT COUNT(*) AS total FROM CITA WHERE ID_MIEMBRO_PERSONAL = ?";
        String consultarCitas = "SELECT c.ID, c.FECHA_HORA_PROGRAMADA, p.NOMBRE AS PACIENTE_NOMBRE, mp.NOMBRE AS MIEMBRO_PERSONAL_NOMBRE " +
                "FROM CITA c " +
                "JOIN PACIENTE p ON c.ID_PACIENTE = p.ID " +
                "JOIN MIEMBRO_PERSONAL mp ON c.ID_MIEMBRO_PERSONAL = mp.ID " +
                "WHERE c.ID_MIEMBRO_PERSONAL = ? " +
                "AND EXTRACT(YEAR FROM c.FECHA_HORA_PROGRAMADA) = ? " +
                "AND EXTRACT(MONTH FROM c.FECHA_HORA_PROGRAMADA) = ?";
        boolean citasEncontradas = false;

        try (Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
             PreparedStatement comprobarStatement = connection.prepareStatement(comprobarCitasUsuario);
             PreparedStatement consultarStatement = connection.prepareStatement(consultarCitas)) {

            comprobarStatement.setInt(1, usuario.getId());
            ResultSet comprobarResultSet = comprobarStatement.executeQuery();
            if (comprobarResultSet.next() && comprobarResultSet.getInt("total") == 0) {
                System.out.println("El usuario " + usuario.getNombre() + " no tiene citas asignadas.");
                return;  // Salir del método si el usuario no tiene citas asignadas
            }

            consultarStatement.setInt(1, usuario.getId());
            consultarStatement.setInt(2, year);
            consultarStatement.setInt(3, month);
            ResultSet resultSet = consultarStatement.executeQuery();

            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("\t\t\t\t\t\t\t\t\t\tCITAS");
            System.out.println("--------------------------------------------------------------------------------------------");

            while (resultSet.next()) {

                citasEncontradas = true;

                int id = resultSet.getInt("ID");
                Date fechaHoraProgramada = resultSet.getDate("FECHA_HORA_PROGRAMADA");
                //Date fechaHoraRegistro = resultSet.getDate("FECHA_HORA_REGISTRO");
                String pacienteNombre = resultSet.getString("PACIENTE_NOMBRE");
                String miembroPersonalNombre = resultSet.getString("MIEMBRO_PERSONAL_NOMBRE");

                System.out.println("ID de la Cita: " + id);
                System.out.println("Nombre del Paciente: " + pacienteNombre);
                System.out.println("Nombre del Miembro Personal: " + miembroPersonalNombre);
                System.out.println("Fecha Programada: " + fechaHoraProgramada);
                //System.out.println("Fecha Registro: " + fechaHoraRegistro);
                System.out.println("--------------------------------------------------------------------------------------------");
            }

            if (!citasEncontradas) {
                System.out.println("No se encontraron citas para el usuario " + usuario.getNombre() + " en la fecha especificada: " + month + "/" + year);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarReclamosCita(Cita cita) {
        String listarReclamos = "SELECT * FROM RECLAMACION WHERE ID_CITA = ?";
        try (Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
             PreparedStatement listarStatement = connection.prepareStatement(listarReclamos)) {
            listarStatement.setInt(1, cita.getId());
            ResultSet rs = listarStatement.executeQuery();

            while (rs.next()) {
                double montoReclamado = rs.getDouble("MONTO_DEMANDADO");
                double montoPagado = rs.getDouble("MONTO_PAGADO");
                double diferencia = montoReclamado - montoPagado;

                System.out.println("ID del Reclamo: " + rs.getInt("id"));
                System.out.println("Monto Reclamado: " + montoReclamado);
                System.out.println("Monto Pagado: " + montoPagado);
                System.out.println("Diferencia: " + diferencia);
                System.out.println("--------------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarCitasConReclamosSinMontoPagado() {
        String listarSinMonto = "SELECT c.ID, c.FECHA_HORA_PROGRAMADA, p.NOMBRE FROM CITA c " +
                "JOIN PACIENTE p ON c.ID_PACIENTE = p.ID " +
                "JOIN RECLAMACION r ON c.ID = r.ID_CITA " +
                "WHERE r.MONTO_PAGADO IS NULL OR r.MONTO_PAGADO = 0";
        try (Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(listarSinMonto);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Id de la Cita: " + rs.getInt("ID"));
                System.out.println("Fecha Cita Programada: " + rs.getDate("FECHA_HORA_PROGRAMADA"));
                System.out.println("Nombre del Paciente: " + rs.getString("NOMBRE"));
                System.out.println("--------------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public void modificarMontoReclamado(int idReclamo, int nuevoMontoReclamado) {
        String actualizarMonto = "UPDATE RECLAMACION SET MONTO_DEMANDADO = ? WHERE ID = ?";
        try (Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(actualizarMonto)) {
            preparedStatement.setFloat(1, nuevoMontoReclamado);
            preparedStatement.setInt(2, idReclamo);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("El monto reclamado ha sido actualizado exitosamente.");
            } else {
                System.out.println("No se encontró el reclamo con el ID proporcionado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarMontoPagado(int reclamoId, double montoPagado) {
        String actualizarMontoP = "UPDATE RECLAMACION SET MONTO_PAGADO = ? WHERE ID = ?";
        try (Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(actualizarMontoP)) {
            preparedStatement.setDouble(1, montoPagado);
            preparedStatement.setInt(2, reclamoId);
            preparedStatement.executeUpdate();
            System.out.println("Monto pagado actualizado exitosamente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertarReclamoCita(Reclamacion reclamacion) {
        String insertarCita = "INSERT INTO RECLAMACION (FECHA_INCIDENTE, FECHA_REGISTRO, MONTO_DEMANDADO, ID_CITA, ID_POLIZA) VALUES (?, ?, ?, ?, ?)";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(insertarCita);
            preparedStatement.setDate(1, reclamacion.getFechaIncidente());
            preparedStatement.setDate(2, reclamacion.getFechaRegistro());
            preparedStatement.setInt(3, reclamacion.getMontoDemandado());
            preparedStatement.setInt(4, reclamacion.getCita().getId());
            preparedStatement.setInt(5, reclamacion.getPoliza().getId());
            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas < 1) {
                System.out.println("No se pudo agregar la reclamacion " + reclamacion.getId() + " a la base de datos");
                return;
            }
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void elimReclamoCita(Reclamacion reclamacion) {
        String insertarCita = "DELETE FROM RECLAMACION WHERE ID = ?";
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(insertarCita);
            preparedStatement.setInt(1, reclamacion.getId());
            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas < 1) {
                System.out.println("No se pudo eliminar la reclamacion " + reclamacion.getId() + " a la base de datos");
                return;
            }
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
