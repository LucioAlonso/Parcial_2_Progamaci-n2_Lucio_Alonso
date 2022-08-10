<%-- 
    Document   : modificar
    Created on : 12 nov. 2021, 16:13:22
    Author     : Lucio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="/AVIS/css/favicon.png">
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <title>AVIS</title>
    </head>
    <body>
        <h2>Modificar registro</h2>
        
        
        <form action="VehiculosController?accion=actualizar" method="POST" autocomplete="off">
            
            <input patente="patente" name="patente" type="hidden" value="<c:out value="${vehiculo.patente}" />" />
            
            <p>
                Marca:
                <input id="marca" name="marca" type="text" value="<c:out value="${vehiculo.marca}" />" />
                
                Modelo:
                <input id="modelo" name="modelo" type="text" value="<c:out value="${vehiculo.modelo}" />" />
                
                Color:
                <input id="color" name="color" type="text" value="<c:out value="${vehiculo.color}" />" />
                
                Tanque:
                <input id="tanque" name="tanque" type="text" value="<c:out value="${vehiculo.tanque}" />" />
                
                Precio:
                <input id="precio" name="precio" type="text" value="<c:out value="${vehiculo.precio}" />" />
                
                <button id="guardar" name="guardar" type="submit">Guardar</button>
            </p>
            
        </form>
    </body>
</html>
