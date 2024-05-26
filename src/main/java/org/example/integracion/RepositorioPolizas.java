package org.example.integracion;

import org.example.dominio.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class RepositorioPolizas {
    public void cargarUsuarios(List<Usuario> usarios){
        String traerUsuarios = new String("SELECT * FROM USUARIO");
        try{
            Connection connection = DriverManager.getConnection(Constantes.URL, Constantes.USERNAME, Constantes.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(traerUsuarios);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Usuario usuario = new Usuario();
                usuario.setNombre(resultSet.getString("NOMBRE"));
                usuario.setContrasenia(resultSet.getString("CONTRASENA"));
                usarios.add(usuario);
            }
        }catch (Exception e){
            e.getMessage();
        }
    }
}
