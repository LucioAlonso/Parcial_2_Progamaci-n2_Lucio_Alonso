/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import config.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucio
 */
public class ClientesABM {

    Connection conexion;

    public ClientesABM() {
        Conexion con = new Conexion();
        conexion = con.getConexion();
    }

  /*  public List<Clientes> listarClientes() {

        PreparedStatement ps;
        ResultSet rs;

        List<Clientes> lista = new ArrayList<>();

        try {

            ps = conexion.prepareStatement("SELECT nombre, dni, telefono, direccion FROM clientes");
            rs = ps.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int dni = rs.getInt("dni");
                int telefono = rs.getInt("telefono");                    //tener cuidado con pasar el dato tipo date
                String direccion = rs.getString("direccion");          //tener cuidado con esto

                Clientes cliente = new Clientes(nombre, direccion, dni, telefono);

                lista.add(cliente);
            }

            return lista;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return null;
        }

    } */

    /*public Clientes motrarCliente(int _id) {

        PreparedStatement ps;
        ResultSet rs;

        Clientes cliente = null;

        try {

            ps = conexion.prepareStatement("SELECT id_cliente, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total FROM clientes WHERE id_cliente = ?");
            ps.setInt(1, _id);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id_cliente = rs.getInt("id_cliente");
                int dni_cliente = rs.getInt("dni_cliente");
                String patente = rs.getString("patente");
                String fecha_inicio = rs.getString("fecha_inicio");
                String fecha_fin = rs.getString("fecha_fin");                    //tener cuidado con pasar el dato tipo date
                Double precio_total = rs.getDouble("precio_total");          //tener cuidado con esto

                cliente = new Clientes(id_cliente, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total);
            }

            return cliente;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return null;
        }

    }

    public List<Clientes> listarClientes(String dni) {        //polimorfismo

        PreparedStatement ps;
        ResultSet rs;

        List<Clientes> lista = new ArrayList<>();

        try {

            ps = conexion.prepareStatement("SELECT id_cliente, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total FROM clientes WHERE dni_cliente = ?");
            ps.setString(1, dni);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id_cliente = rs.getInt("id_cliente");
                int dni_cliente = rs.getInt("dni_cliente");
                String patente = rs.getString("patente");
                String fecha_inicio = rs.getString("fecha_inicio");
                String fecha_fin = rs.getString("fecha_fin");                    //tener cuidado con pasar el dato tipo date
                Double precio_total = rs.getDouble("precio_total");          //tener cuidado con esto
                
                Clientes cliente = new Clientes(id_cliente, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total);
                
                lista.add(cliente);
                
            }
            
            return lista;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return null;
        }

    }
*/
    public boolean insertar(Clientes cliente) {

        PreparedStatement ps;

        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String fecha_inicio = dtf.format(LocalDateTime.now());
            
            ps = conexion.prepareStatement("INSERT INTO clientes (nombre, dni, telefono, direccion) VALUES (?,?,?,?)");
            ps.setString(1, cliente.getNombre());
            ps.setInt(2, cliente.getDni());
            ps.setInt(3, cliente.getTelefono());          
            ps.setString(4, cliente.getDireccion());              
            ps.execute();

            return true;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return false;
        }

    }
/*
    public boolean actualizar(Clientes cliente) {

        PreparedStatement ps;

        try {

            ps = conexion.prepareStatement("UPDATE clientes SET dni_cliente=?, patente=?, fecha_inicio=?, fecha_fin=?, precio_total=? WHERE id_cliente=?");
            ps.setInt(1, cliente.getDni_cliente());
            ps.setString(2, cliente.getPatente());
            ps.setString(3, cliente.getFecha_inicio());           //tener cuidado aca
            ps.setString(4, cliente.getFecha_fin());              //tener cuidado aca
            ps.setDouble(5, cliente.getPrecio_total());
            ps.setInt(6, cliente.getId_cliente());
            ps.execute();

            return true;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return false;
        }
    }

    public boolean eliminar(int _id_cliente) {    //tener cuidado con el _

        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("DELETE FROM clientes WHERE id_cliente=?");
            ps.setInt(1, _id_cliente);
            ps.execute();

            return true;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return false;
        }

    }

    public List<Clientes> buscar(String dni) {
        PreparedStatement ps;
        ResultSet rs;

        List<Clientes> lista = new ArrayList<>();

        try {

            ps = conexion.prepareStatement("SELECT id_cliente, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total FROM clientes WHERE dni_cliente = ?");
            ps.setString(1, dni);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id_cliente = rs.getInt("id_cliente");
                int dni_cliente = rs.getInt("dni_cliente");
                String patente = rs.getString("patente");
                String fecha_inicio = rs.getString("fecha_inicio");
                String fecha_fin = rs.getString("fecha_fin");                    //tener cuidado con pasar el dato tipo date
                Double precio_total = rs.getDouble("precio_total");          //tener cuidado con esto

                Clientes cliente = new Clientes(id_cliente, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total);

                lista.add(cliente);
            }

            return lista;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return null;
        }
    } */
}
