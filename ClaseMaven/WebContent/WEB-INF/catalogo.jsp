<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ page import="ferreteria.*" %>




<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF‐8">
		<title>Catalogo</title>
		<link rel="stylesheet" type="text/css" href="css/estilo.css">
	
		<%!
		/* Esto se ejecuta cuando un usuario realiza
		la primera solicitud a está página jsp
		y no vuelve a ejecutarse a no ser que se
		detenga el servidor por labores de
		mantenimiento.
		Las variables aquí declaradas se mantienen
		durante toda la sesión del servidor, es decir,
		durante el tiempo que está iniciado.
		*/
		Catalogo ct = new Catalogo();
		String mensaje = ct.getMensaje();
		%>

	</head>

	<body>

		<h1> <%= ct.getMensaje() %></h1>
		<table>
			<tr>
				<th>CODIGO</th>
				<th id="descripcion">DESCRIPCION</th>
				<th>PRECIO</th>
				<th>STOCK</th>
			</tr>
			
		<% for (Articulo ar : ct.getArticulos()) { %>
			<tr>
				<td> <%= ar.getCodigo() %></td>
				<td> <%= ar.getDescripcion() %></td>
				<td class="derecha"> <%= ar.getPrecio() %></td>
				<td class="derecha"> <%= ar.getStock() %></td>
			</tr>
		<% } %>
		</table>
		
	</body>
</html>