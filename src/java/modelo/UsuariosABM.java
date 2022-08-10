/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import config.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucio
 */
public class UsuariosABM {

    Connection conexion;

    public UsuariosABM() {
        Conexion con = new Conexion();
        conexion = con.getConexion();
    }

    public boolean validar(String contr, String user) {
        PreparedStatement ps;
        ResultSet rs;

        try {

            ps = conexion.prepareStatement("SELECT usuario, contr, tipo, nombre, apellido, dni, mail, telefono FROM usuarios WHERE (contr =?) AND (usuario =?)");
            ps.setString(1, contr);
            ps.setString(2, user);
            rs = ps.executeQuery();
            boolean existencia = false;
            Usuarios usuario = null;

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int dni = rs.getInt("dni");
                String mail = rs.getString("mail");
                int telefono = rs.getInt("telefono");
                System.out.println("ESTOY ACA");
                if (tipo.isEmpty()) {
                    existencia = false;
                } else {
                    existencia = true;
                }

            }

            return existencia;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return false;
        }
    }

    public Usuarios validarUsuario(String contr, String user) {

        PreparedStatement ps;
        ResultSet rs;

        try {

            ps = conexion.prepareStatement("SELECT usuario, contr, tipo, nombre, apellido, dni, mail, telefono FROM usuarios WHERE (contr =?) AND (usuario =?)");
            ps.setString(1, contr);
            ps.setString(2, user);
            rs = ps.executeQuery();

            Usuarios usuario = null;

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int dni = rs.getInt("dni");
                String mail = rs.getString("mail");
                int telefono = rs.getInt("telefono");

                if (tipo.isEmpty()) {
                    usuario = new Usuarios(null, null, null, null, null, 0, null, 0);
                } else {
                    usuario = new Usuarios(user, contr, tipo, nombre, apellido, dni, mail, telefono);
                }

            }

            return usuario;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return null;
        }

    }

}
