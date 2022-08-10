package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Vehiculos;
import modelo.VehiculosABM;

/**
 *
 * @author Lucio
 */
public class VehiculosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        VehiculosABM vehiculosABM = new VehiculosABM();
        String accion;
        RequestDispatcher dispatcher = null;

        accion = request.getParameter("accion");

        if (accion == null || accion.isEmpty()) {

            dispatcher = request.getRequestDispatcher("Vehiculos/index.jsp");
            List<Vehiculos> listaVehiculos = vehiculosABM.listarVehiculos();
            request.setAttribute("lista", listaVehiculos);

        } else if ("insertarVehiculo".equals(accion)) {

            String patente = request.getParameter("patente");
            String marca = request.getParameter("marca");
            int modelo = Integer.parseInt(request.getParameter("modelo"));
            String color = request.getParameter("color");
            int tanque = Integer.parseInt(request.getParameter("tanque"));
            Double precio = Double.parseDouble(request.getParameter("precio"));
            boolean estado = false;

            Vehiculos vehiculo = new Vehiculos(patente, marca, modelo, color, tanque, precio, estado);

            vehiculosABM.insertar(vehiculo);

            dispatcher = request.getRequestDispatcher("/VehiculosController?accion=buscarAdministrador");
            List<Vehiculos> listaVehiculos = vehiculosABM.listarVehiculos();
           // request.setAttribute("lista", listaVehiculos);
            
        } else if ("cargar".equals(accion)) {
            dispatcher = request.getRequestDispatcher("Vehiculos/vehiculoNuevo.jsp");

        }else if ("buscarAdministrador".equals(accion)) {
            dispatcher = request.getRequestDispatcher("General/panelAdministrador.jsp");
         //   List<Vehiculos> listaVehiculos = null;
         //   listaVehiculos = vehiculosABM.listarVehiculos();
          //  request.setAttribute("lista", listaVehiculos);

        } else {
            dispatcher = request.getRequestDispatcher("index.jsp");
            List<Vehiculos> listaVehiculos = vehiculosABM.listarVehiculos();
            request.setAttribute("lista", listaVehiculos);
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
