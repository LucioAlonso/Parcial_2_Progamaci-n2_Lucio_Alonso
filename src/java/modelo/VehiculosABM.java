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
public class VehiculosABM {

    Connection conexion;

    public VehiculosABM() {
        Conexion con = new Conexion();
        conexion = con.getConexion();
    }

    public List<Vehiculos> listarVehiculos() {

        PreparedStatement ps;
        ResultSet rs;

        List<Vehiculos> lista = new ArrayList<>();

        try {

            ps = conexion.prepareStatement("SELECT patente, marca, modelo, color, tanque, precio, estado FROM vehiculos");
            rs = ps.executeQuery();

            while (rs.next()) {
                String patente = rs.getString("patente");
                String marca = rs.getString("marca");
                int modelo = rs.getInt("modelo");
                String color = rs.getString("color");
                int tanque = rs.getInt("tanque");
                Double precio = rs.getDouble("precio");
                boolean estado = rs.getBoolean("estado");

                Vehiculos vehiculo = new Vehiculos(patente, marca, modelo, color, tanque, precio, estado);

                lista.add(vehiculo);
            }

            return lista;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return null;
        }

    }

    public List<Vehiculos> listarVehiculos(String disponibilidad) {

        PreparedStatement ps;
        ResultSet rs;

        List<Vehiculos> lista = new ArrayList<>();
        try {
            if ("si".equals(disponibilidad)) {
                ps = conexion.prepareStatement("SELECT patente, marca, modelo, color, tanque, precio, estado FROM vehiculos WHERE estado = 'si'");
                rs = ps.executeQuery();

                while (rs.next()) {
                    String patente = rs.getString("patente");
                    String marca = rs.getString("marca");
                    int modelo = rs.getInt("modelo");
                    String color = rs.getString("color");
                    int tanque = rs.getInt("tanque");
                    Double precio = rs.getDouble("precio");
                    boolean estado = rs.getBoolean("estado");

                    Vehiculos vehiculo = new Vehiculos(patente, marca, modelo, color, tanque, precio, estado);
                    lista.add(vehiculo);
                }

            }
            return lista;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return null;
        }

    }

    public Vehiculos motrarVehiculos(int _id) {

        PreparedStatement ps;
        ResultSet rs;

        Vehiculos vehiculo = null;

        try {

            ps = conexion.prepareStatement("SELECT id, codigo, nombre, precio, existencia FROM vehiculos WHERE id = ?");
            ps.setInt(1, _id);
            rs = ps.executeQuery();

            while (rs.next()) {
                String patente = rs.getString("patente");
                String marca = rs.getString("marca");
                int modelo = rs.getInt("modelo");
                String color = rs.getString("color");
                int tanque = rs.getInt("tanque");
                Double precio = rs.getDouble("precio");
                boolean estado = rs.getBoolean("estado");

                vehiculo = new Vehiculos(patente, marca, modelo, color, tanque, precio, estado);
            }

            return vehiculo;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return null;
        }

    }

    public boolean insertar(Vehiculos vehiculo) {

        PreparedStatement ps;

        try {

            ps = conexion.prepareStatement("INSERT INTO vehiculos (patente, marca, modelo, color, tanque, precio, estado) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, vehiculo.getPatente());
            ps.setString(2, vehiculo.getMarca());
            ps.setInt(3, vehiculo.getModelo());
            ps.setString(4, vehiculo.getColor());
            ps.setInt(5, vehiculo.getTanque());
            ps.setDouble(6, vehiculo.getPrecio());
            ps.setBoolean(7, vehiculo.isEstado());
            ps.execute();

            return true;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return false;
        }

    }


    public boolean eliminar(int patente) {

        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("DELETE FROM vehiculos WHERE patente=?");
            ps.setInt(1, patente);
            ps.execute();

            return true;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return false;
        }

    }

    public Vehiculos buscarVehiculo(String patenteb) {

        PreparedStatement ps;
        ResultSet rs;

        Vehiculos vehiculo = null;

        try {

            ps = conexion.prepareStatement("SELECT patente, marca, modelo, color, tanque, precio, estado FROM vehiculos WHERE patente = ?");
            ps.setString(1, patenteb);
            rs = ps.executeQuery();

            while (rs.next()) {
                String patente = rs.getString("patente");
                String marca = rs.getString("marca");
                int modelo = rs.getInt("modelo");
                String color = rs.getString("color");
                int tanque = rs.getInt("tanque");
                Double precio = rs.getDouble("precio");
                boolean estado = rs.getBoolean("estado");

                vehiculo = new Vehiculos(patente, marca, modelo, color, tanque, precio, estado);
            }

            return vehiculo;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return null;
        }

    }

    public boolean actualizarEstado(Vehiculos vehiculo, String patente, boolean estados) {

        PreparedStatement ps;
        try {

            ps = conexion.prepareStatement("UPDATE vehiculos SET marca=?, modelo=?, color=?, tanque=?, precio=?, estado=? WHERE patente=?");
            ps.setString(1, vehiculo.getMarca());
            ps.setInt(2, vehiculo.getModelo());
            ps.setString(3, vehiculo.getColor());
            ps.setInt(4, vehiculo.getTanque());
            ps.setDouble(5, vehiculo.getPrecio());
            ps.setBoolean(6, estados);
            ps.setString(7, patente);
            ps.execute();
            
            return true;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return false;
        } 

    }


}
