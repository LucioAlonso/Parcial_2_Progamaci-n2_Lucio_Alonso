package controlador;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Reservas;
import modelo.ReservasABM;
import modelo.Vehiculos;
import modelo.VehiculosABM;
import modelo.Clientes;
import modelo.ClientesABM;

/**
 *
 * @author Lucio
 */
public class ReservasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha = dtf.format(LocalDateTime.now());
        ReservasABM reservasABM = new ReservasABM();
        ClientesABM clientesABM = new ClientesABM();
        VehiculosABM vehiculosABM = new VehiculosABM();

        String accion;
        String dni;
        String disponibilidad;
        String tipo;

        RequestDispatcher dispatcher = null;

        accion = request.getParameter("accion");
        dni = request.getParameter("dni_cliente");
        disponibilidad = request.getParameter("disponibilidad");
        tipo = request.getParameter("tipo");



        if (accion == null || accion.isEmpty()) {

            dispatcher = request.getRequestDispatcher("index.jsp");
            List<Reservas> listaReservas = reservasABM.listarReservas(true);
            List<Reservas> listaReservasDes = reservasABM.listarReservas(false);
            request.setAttribute("lista", listaReservas);
            request.setAttribute("listaDes", listaReservasDes);
        } else if ("nuevo".equals(accion)) {

            dispatcher = request.getRequestDispatcher("Reservas/nuevo.jsp");

        } else if ("insertar".equals(accion)) {

            int dni_cliente = Integer.parseInt(request.getParameter("dni"));
            String patente = request.getParameter("patente");
            String nombre = request.getParameter("nombre");
            int telefono = Integer.parseInt(request.getParameter("telefono"));
            String direccion = request.getParameter("direccion");
            String fecha_inicio = dtf.format(LocalDateTime.now());                     //cuidado con esto

            Clientes cliente = new Clientes(nombre, dni_cliente, telefono, direccion);
            Reservas reserva = new Reservas(0, dni_cliente, patente, fecha_inicio, null, null);

            reservasABM.insertar(reserva, (vehiculosABM.buscarVehiculo(patente)).getPrecio());
            clientesABM.insertar(cliente);
            vehiculosABM.actualizarEstado(vehiculosABM.buscarVehiculo(patente), patente, true);

            dispatcher = request.getRequestDispatcher("/ReservasController?accion=buscar");
            List<Reservas> listaReservas = reservasABM.listarReservas(true);
            List<Reservas> listaReservasDes = reservasABM.listarReservas(false);
            request.setAttribute("lista", listaReservas);
            request.setAttribute("listaDes", listaReservasDes);

        } else if ("actualizar".equals(accion)) {

            int id_reserva = Integer.parseInt(request.getParameter("id_reserva"));
            int dni_cliente = Integer.parseInt(request.getParameter("dni_cliente"));
            String patente = request.getParameter("patente");
            String fecha_inicio = request.getParameter("fecha_inicio");                     //cuidado con esto
            String fecha_fin = request.getParameter("fecha_fin");                        //cuidado con esto
            Double precio_total = Double.parseDouble(request.getParameter("precio_total"));
            
            vehiculosABM.actualizarEstado(vehiculosABM.buscarVehiculo(patente), fecha_fin, false);
            Reservas reserva = new Reservas(id_reserva, dni_cliente, patente, fecha_inicio, fecha_fin, precio_total);
                        
            reservasABM.actualizar(reserva);
            dispatcher = request.getRequestDispatcher("index.jsp");
            List<Reservas> listaReservas = reservasABM.listarReservas(true);
            List<Reservas> listaReservasDes = reservasABM.listarReservas(false);
            request.setAttribute("lista", listaReservas);
            request.setAttribute("listaDes", listaReservasDes);

        } else if ("finalizarReserva".equals(accion)) {

            int id = Integer.parseInt(request.getParameter("id"));
            String dnis = request.getParameter("dni");
            String patentes = request.getParameter("patente");
            
            reservasABM.actualizar(reservasABM.motrarReserva(id));
            vehiculosABM.actualizarEstado(vehiculosABM.buscarVehiculo(patentes), patentes, false);
            
            dispatcher = request.getRequestDispatcher("General/panelAdministrador.jsp");
            List<Reservas> listaReservas = null;
            try {
                listaReservas = reservasABM.listarReservas(dnis, true);
                
            } catch (ParseException ex) {
                Logger.getLogger(ReservasController.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<Reservas> listaReservasDes = reservasABM.listarReservas(false);
            request.setAttribute("lista", listaReservas);
            request.setAttribute("listaDes", listaReservasDes);

        } else if ("buscarVendedor".equals(accion)) {
            dispatcher = request.getRequestDispatcher("General/panelVendedor.jsp");
            List<Reservas> listaReservas = null;
            List<Reservas> listaReservasDes = null;
            try {
                listaReservas = reservasABM.listarReservas(dni, true);
                listaReservasDes = reservasABM.listarReservas(dni, false);
            } catch (ParseException ex) {
                Logger.getLogger(ReservasController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("lista", listaReservas);
            request.setAttribute("listaDes", listaReservasDes);

        } else if ("buscarAdministrador".equals(accion)) {
            dispatcher = request.getRequestDispatcher("General/panelAdministrador.jsp");
            List<Reservas> listaReservas = null;
            List<Reservas> listaReservasDes = null;
            try {
                listaReservas = reservasABM.listarReservas(dni, true);
                listaReservasDes = reservasABM.listarReservas(dni, false);
            } catch (ParseException ex) {
                Logger.getLogger(ReservasController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("lista", listaReservas);
            request.setAttribute("listaDes", listaReservasDes);

        } else if ("buscar".equals(accion)) {
            dispatcher = request.getRequestDispatcher("index.jsp");
            List<Reservas> listaReservas = null;
            List<Reservas> listaReservasDes = null;
            try {
                listaReservas = reservasABM.listarReservas(dni, true);
                listaReservasDes = reservasABM.listarReservas(dni, false);
            } catch (ParseException ex) {
                Logger.getLogger(ReservasController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("lista", listaReservas);
            request.setAttribute("listaDes", listaReservasDes);

        } else if ("vehiculosDisponibles".equals(accion)) {

            dispatcher = request.getRequestDispatcher("Reservas/reservaNueva.jsp");
            List<Vehiculos> listaVehiculos = vehiculosABM.listarVehiculos(disponibilidad);
            request.setAttribute("lista", listaVehiculos);


        } else {
            dispatcher = request.getRequestDispatcher("index.jsp");
            List<Reservas> listaReservas = reservasABM.listarReservas(true);
            List<Reservas> listaReservasDes = reservasABM.listarReservas(false);
            request.setAttribute("lista", listaReservas);
            request.setAttribute("listaDes", listaReservasDes);
        }

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
