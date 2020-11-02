<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ferreteria.*" %>




<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF‐8">
		<title>Catalogo</title>
		
	
		<%!
		/* Crearemos nuestro objeto Catalogo que realizara las funciones explicadas en 
		Catologo.java
		*/
		Catalogo ct = new Catalogo();
		String mensaje = ct.getMensaje();
		%>

	</head>

	<body>
		
		<!-- Recogeremos el mensaje de nuestro objeto ct, y crearemos una tabla para mostrar los productos -->
		<h1> <%= ct.getMensaje() %></h1>
		<table>
			<tr>
				<th>CODIGO</th>
				<th id="descripcion">DESCRIPCION</th>
				<th>PRECIO</th>
				<th>STOCK</th>
			</tr>
			<!-- En esta parte recorreremos todos los articulos por medio de un buble for de Articulos de nuestra arrayList y acceremos al 
			los diferentes get de la misma para mostrarlos, los realizxaremos por columnas para que sea mejor visible -->
			
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