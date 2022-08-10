<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <title>AVIS Pagina Principal</title>
        <link rel="stylesheet" type="text/css" href="css/paginaPrincipal.css">
        <link rel="shortcut icon" href="/AVIS/css/favicon.png">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <p><form method="get" action="inicioSesion.jsp" class="inicioBoton">
            <button type="submit">Iniciar Sesion</button>
        </form></p>

    <h1 class="titulos">AVIS</h1>
    <h2 class="titulos">Alquiler de Vehiculos</h2>



    <form method="post" action="ReservasController?accion=buscar&dni=dni_cliente" class="consultar">
        <h3 id="consultarReservas">Consultar Reservas</h3>
        <p> 
            DNI:
            <input id="dni_cliente" name="dni_cliente" type="text" /> <button type="submit">Buscar</button> 
        </p>     
    </form>    




    <div style = "float: left">
        <h3 class="nombreTabla">Reservas Finalizadas</h3>  
        <table border="1" class="tabla"">
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