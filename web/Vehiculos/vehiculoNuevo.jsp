<%-- 
    Document   : reservaNueva
    Created on : 14 nov. 2021, 14:34:46
    Author     : Lucio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="/AVIS/css/favicon.png">
        <link rel="stylesheet" type="text/css" href="/AVIS/css/paginaPrincipal.css">
        <title>Nuevo Vehiculo</title>
    </head>
    <body>
        <h1 id="paginaNuevoVehiculo">Nuevo Vehiculo</h1>

        <form class="planillaDeDatos" action="VehiculosController?accion=insertarVehiculo" method="POST" autocomplete="off">
            <p>
                Patente:        
                <input id="patente" name="patente" type="text" />
                <br><br>
                Marca:           
                <input id="marca" name="marca" type="text" />
                <br><br>            
                Modelo:       
                <input id="modelo" name="modelo" type="number" />
                <br><br>
                Color:       
                <input id="color" name="color" type="text" />
                <br><br>
                Tanque:       
                <input id="tanque" name="tanque" type="number" />     
                <br><br>
                Precio:       
                <input id="precio" name="precio" type="number" />     
                <br><br>
                
                <button id="guardar" name="guardar" type="submit">Cargar Vehiculo</button>
            </p>
        </form>


        <form class="planillaDeDatos" method="post" action="/AVIS/VehiculosController?accion=buscarAdministrador">
            <button type="submit">Cancelar</button>
        </form> 
    </body>
</html>
