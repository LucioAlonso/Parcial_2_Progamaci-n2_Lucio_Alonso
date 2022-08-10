<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="/AVIS/css/favicon.png">
        <title>Inicio Sesion</title>
    </head>
    <body>
        <h1>Bienvenido!</h1>
        <form method="post" action="LoginController?accion=iniciar&user=usuario&contr=contra">
            <p> 
                Usuario:
                <input id="usuario" name="usuario" type="text" required=""/> 
                <br><br>
                Contraseña:
                <input id="contra" name="contra" type="password" required=""/>  
            </p>
            <br>
            <button type="submit">Iniciar Sesion</button>
        </form> 
    </body>
</html>
