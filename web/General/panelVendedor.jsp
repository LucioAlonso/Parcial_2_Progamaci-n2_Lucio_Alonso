<%-- 
    Document   : panelVendedor
    Created on : 15 nov. 2021, 1:10:02
    Author     : Lucio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link rel="shortcut icon" href="/AVIS/css/favicon.png">
        <link rel="stylesheet" type="text/css" href="/AVIS/css/paginaPrincipal.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panel de Contro</title>
    </head>

    <body>
  
            <form method="post" id="cerrarSesion" action="index.jsp" class="inicioBoton">
                <button type="submit">Cerrar Sesion</button>
            </form>
            <form method="post" id="nuevaReserva" class="inicioBoton" action="ReservasController?accion=vehiculosDisponibles&disponibilidad=si">
                <button type="submit">Nueva Reserva</button>
            </form> 
                
 
    <h1 class="titulos">PANEL DE CONTROL</h1>
    <h2 class="titulos">Vendedor</h2>



    <form method="post" action="ReservasController?accion=buscarVendedor&dni=dni_cliente" class="consultar">
        <h3 id="consultarReservas">Consultar Reservas</h3>
        <p> 
            DNI:
            <input id="dni_cliente" name="dni_cliente" type="text" /> <button type="submit">Buscar</button> 
        </p>
    </form>    


    <div style = "float: left">
        <h3 class="nombreTabla">Reservas Finalizadas</h3>   
        <table border="1" class="tabla">
            <thead>
                <tr>
                    <th>ID Reserva</th>
                    <th>DNI Cliente</th>
                    <th>Patente</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Fin</th>
                    <th>Monto Total</th>
                </tr>
            </thead>
            <tbody> 
                <c:forEach var="reservas" items="${lista}">
                    <tr>
                        <td><c:out value="${reservas.id_reserva}"/></td>
                        <td><c:out value="${reservas.dni_cliente}"/></td>
                        <td><c:out value="${reservas.patente}"/></td>
                        <td><c:out value="${reservas.fecha_inicio}"/></td>
                        <td><c:out value="${reservas.fecha_fin}"/></td>
                        <td><c:out value="${reservas.precio_total}"/></td>                    
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div style = "float: left">
        <h3 class="nombreTabla">Reservas Activas</h3>
        <table border="1" class="tabla">
            <thead>
                <tr>
                    <th>ID Reserva</th>
                    <th>DNI Cliente</th>
                    <th>Patente</th>
                    <th>Fecha Inicio</th>
                    <th>Precio Total</th>
                </tr>
            </thead>
            <tbody> 
                <c:forEach var="reservasDes" items="${listaDes}">
                    <tr>
                        <td><c:out value="${reservasDes.id_reserva}"/></td>
                        <td><c:out value="${reservasDes.dni_cliente}"/></td>
                        <td><c:out value="${reservasDes.patente}"/></td>
                        <td><c:out value="${reservasDes.fecha_inicio}"/></td>
                        <td><c:out value="${reservasDes.precio_total}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>




</body>
</html>
