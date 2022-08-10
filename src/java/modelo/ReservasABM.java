/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import config.Conexion;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucio
 */
public class ReservasABM {

    Connection conexion;

    public ReservasABM() {
        Conexion con = new Conexion();
        conexion = con.getConexion();
    }

    public List<Reservas> listarReservas(boolean estado) {

        PreparedStatement ps;
        ResultSet rs;

        List<Reservas> lista = new ArrayList<>();

        try {
            if (estado == true) {
                ps = conexion.prepareStatement("SELECT id_reserva, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total FROM reservas WHERE (fecha_fin != 'NULL')");
                rs = ps.executeQuery();

                while (rs.next()) {
                    int id_reserva = rs.getInt("id_reserva");
                    int dni_cliente = rs.getInt("dni_cliente");
                    String patente = rs.getString("patente");
                    String fecha_inicio = rs.getString("fecha_inicio");
                    String fecha_fin = rs.getString("fecha_fin");                    //tener cuidado con pasar el dato tipo date
                    Double precio_total = rs.getDouble("precio_total");          //tener cuidado con esto

                    Reservas reserva = new Reservas(id_reserva, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total);

                    lista.add(reserva);
                }

                return lista;
            } else {
                ps = conexion.prepareStatement("SELECT id_reserva, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total FROM reservas WHERE (fecha_fin IS NULL)");
                rs = ps.executeQuery();

                while (rs.next()) {
                    int id_reserva = rs.getInt("id_reserva");
                    int dni_cliente = rs.getInt("dni_cliente");
                    String patente = rs.getString("patente");
                    String fecha_inicio = rs.getString("fecha_inicio");
                    String fecha_fin = rs.getString("fecha_fin");                    //tener cuidado con pasar el dato tipo date
                    Double precio_total = rs.getDouble("precio_total");          //tener cuidado con esto

                    Reservas reserva = new Reservas(id_reserva, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total);

                    lista.add(reserva);
                }

                return lista;
            }

        } catch (SQLException e) {
            System.out.print(e.toString());
            return null;
        }

    }

    public Reservas motrarReserva(int _id) {

        PreparedStatement ps;
        ResultSet rs;

        Reservas reserva = null;

        try {

            ps = conexion.prepareStatement("SELECT id_reserva, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total FROM reservas WHERE id_reserva = ?");
            ps.setInt(1, _id);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id_reserva = rs.getInt("id_reserva");
                int dni_cliente = rs.getInt("dni_cliente");
                String patente = rs.getString("patente");
                String fecha_inicio = rs.getString("fecha_inicio");
                String fecha_fin = rs.getString("fecha_fin");                    //tener cuidado con pasar el dato tipo date
                Double precio_total = rs.getDouble("precio_total");          //tener cuidado con esto

                reserva = new Reservas(id_reserva, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total);
            }

            return reserva;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return null;
        }

    }

    public List<Reservas> listarReservas(String dni, boolean estado) throws ParseException {        //polimorfismo

        PreparedStatement ps;
        ResultSet rs;

        List<Reservas> lista = new ArrayList<>();

        try {

            if (estado == true) {
                ps = conexion.prepareStatement("SELECT id_reserva, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total FROM reservas WHERE (dni_cliente = ?) AND (fecha_fin != 'NULL')");
                ps.setString(1, dni);
                rs = ps.executeQuery();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String fecha_hoy = dtf.format(LocalDateTime.now());

                while (rs.next()) {
                    int id_reserva = rs.getInt("id_reserva");
                    int dni_cliente = rs.getInt("dni_cliente");
                    String patente = rs.getString("patente");
                    String fecha_inicio = rs.getString("fecha_inicio");
                    String fecha_fin = rs.getString("fecha_fin");                    //tener cuidado con pasar el dato tipo date
                    Double precio_total = rs.getDouble("precio_total");          //tener cuidado con esto

                    if (fecha_fin == null) {
                        precio_total = precio_total * diferenciarDias(fecha_inicio, fecha_hoy);
                    } else {
                        precio_total = precio_total * diferenciarDias(fecha_inicio, fecha_fin);
                    }

                    Reservas reserva = new Reservas(id_reserva, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total);

                    lista.add(reserva);

                }

                return lista;
            } else {
                ps = conexion.prepareStatement("SELECT id_reserva, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total FROM reservas WHERE (dni_cliente = ?) AND (fecha_fin IS NULL)");
                ps.setString(1, dni);
                rs = ps.executeQuery();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String fecha_hoy = dtf.format(LocalDateTime.now());

                while (rs.next()) {
                    int id_reserva = rs.getInt("id_reserva");
                    int dni_cliente = rs.getInt("dni_cliente");
                    String patente = rs.getString("patente");
                    String fecha_inicio = rs.getString("fecha_inicio");
                    String fecha_fin = rs.getString("fecha_fin");                    //tener cuidado con pasar el dato tipo date
                    Double precio_total = rs.getDouble("precio_total");          //tener cuidado con esto

                    if (fecha_fin == null) {
                        precio_total = precio_total * diferenciarDias(fecha_inicio, fecha_hoy);
                    } else {
                        precio_total = precio_total * diferenciarDias(fecha_inicio, fecha_fin);
                    }

                    Reservas reserva = new Reservas(id_reserva, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total);

                    lista.add(reserva);

                }

                return lista;
            }

        } catch (SQLException e) {
            System.out.print(e.toString());
            return null;
        }

    }

    public boolean insertar(Reservas reserva, Double precio) {

        PreparedStatement ps;

        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fecha_inicio = dtf.format(LocalDateTime.now());
            ps = conexion.prepareStatement("INSERT INTO reservas (dni_cliente, patente, fecha_inicio, fecha_fin, precio_total) VALUES (?,?,?,?,?)");
            ps.setInt(1, reserva.getDni_cliente());
            ps.setString(2, reserva.getPatente());
            ps.setString(3, fecha_inicio);           //tener cuidado aca
            ps.setString(4, null);              //tener cuidado aca
            ps.setDouble(5, precio);
            ps.execute();

            return true;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return false;
        }

    }

    public boolean actualizar(Reservas reserva) {

        PreparedStatement ps;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha_fin = dtf.format(LocalDateTime.now());
        try {

            ps = conexion.prepareStatement("UPDATE reservas SET dni_cliente=?, patente=?, fecha_inicio=?, fecha_fin=?, precio_total=? WHERE id_reserva=?");
            ps.setInt(1, reserva.getDni_cliente());
            ps.setString(2, reserva.getPatente());
            ps.setString(3, reserva.getFecha_inicio());           //tener cuidado aca
            ps.setString(4, fecha_fin);              //tener cuidado aca
            ps.setDouble(5, reserva.getPrecio_total());
            ps.setInt(6, reserva.getId_reserva());
            ps.execute();
            
            return true;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return false;
        }
    }

    public boolean eliminar(int _id_reserva) throws ParseException {    //tener cuidado con el _

        PreparedStatement ps;
        ResultSet rs;
        List<Reservas> lista = new ArrayList<>();
        VehiculosABM vehiculosABM = new VehiculosABM();
        Reservas reserva = null;
        String patente2 = null;
        try {
            ps = conexion.prepareStatement("SELECT id_reserva, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total FROM reservas WHERE id_reserva = ?");
            ps.setInt(1, _id_reserva);
            rs = ps.executeQuery();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fecha_fin = dtf.format(LocalDateTime.now());
            
            while (rs.next()) {
                int id_reserva = rs.getInt("id_reserva");
                int dni_cliente = rs.getInt("dni_cliente");
                String patente = rs.getString("patente");
                String fecha_inicio = rs.getString("fecha_inicio");        
                Double precio_total = rs.getDouble("precio_total");

                patente2 = patente;
                precio_total = precio_total * diferenciarDias(fecha_inicio, fecha_fin);
                vehiculosABM.actualizarEstado(vehiculosABM.buscarVehiculo(patente), fecha_fin, false);
                reserva = new Reservas(id_reserva, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total);
                lista.add(reserva);

            }
            
            
            return true;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return false;
        }

    }

    public List<Reservas> buscar(String dni) {
        PreparedStatement ps;
        ResultSet rs;

        List<Reservas> lista = new ArrayList<>();

        try {

            ps = conexion.prepareStatement("SELECT id_reserva, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total FROM reservas WHERE dni_cliente = ?");
            ps.setString(1, dni);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id_reserva = rs.getInt("id_reserva");
                int dni_cliente = rs.getInt("dni_cliente");
                String patente = rs.getString("patente");
                String fecha_inicio = rs.getString("fecha_inicio");
                String fecha_fin = rs.getString("fecha_fin");                    //tener cuidado con pasar el dato tipo date
                Double precio_total = rs.getDouble("precio_total");          //tener cuidado con esto

                Reservas reserva = new Reservas(id_reserva, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total);

                lista.add(reserva);
            }

            return lista;

        } catch (SQLException e) {
            System.out.print(e.toString());
            return null;
        }
    }

    public int diferenciarDias(String fechaInicio, String fechaFin) throws ParseException {
        int dif = 1;

        java.util.Date fechaIncialdate = stringaFecha(fechaInicio);
        java.util.Date fechaFinaldate = stringaFecha(fechaFin);

        dif = (int) ((fechaFinaldate.getTime() - fechaIncialdate.getTime()) / (1000 * 60 * 60 * 24));

        if (dif == 0) {
            return 1;
        }
        return dif;
    }

    public java.util.Date stringaFecha(String fecha) throws ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        java.util.Date fecha1 = formato.parse(fecha);

        return fecha1;
    }
}
