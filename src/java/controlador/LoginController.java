package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Reservas;
import modelo.ReservasABM;
import modelo.Usuarios;
import modelo.UsuariosABM;

/**
 *
 * @author Lucio
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuariosABM usuariosABM = new UsuariosABM();
        String accion, user, contr;
        RequestDispatcher dispatcher = null;

        accion = request.getParameter("accion");
        user = request.getParameter("usuario");
        contr = request.getParameter("contra");

        if (accion == null || accion.isEmpty()) {

            dispatcher = request.getRequestDispatcher("index.jsp");

        } else if ("iniciar".equals(accion)) {
            Usuarios usuario = usuariosABM.validarUsuario(contr, user);
            boolean existencia = usuariosABM.validar(contr, user);
            System.out.println("\nESTOY ACA");
            System.out.println(existencia);

            if (existencia == true) {
                if ("admin".equals(usuario.getTipo())) {

                    dispatcher = request.getRequestDispatcher("General/panelAdministrador.jsp");
                    request.setAttribute("user", usuario);

                } else if ("vendedor".equals(usuario.getTipo())) {

                    dispatcher = request.getRequestDispatcher("General/panelVendedor.jsp");
                    request.setAttribute("user", usuario);
                }
            } else if (existencia == false) {
                dispatcher = request.getRequestDispatcher("index.jsp");
            }

        } else {
            dispatcher = request.getRequestDispatcher("index.jsp");
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
