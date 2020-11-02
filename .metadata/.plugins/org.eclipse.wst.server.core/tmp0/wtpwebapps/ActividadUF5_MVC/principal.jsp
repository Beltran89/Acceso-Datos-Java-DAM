<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <link rel="stylesheet" href="css/estilos.css">
<title>Insert title here</title>
</head>
<body>
	<!-- Un JSP o Java Server Page, es una pagina que nos sirva para hacer contenido dinamico
		Es decir, mezclamos etiquetas HTML con algunas expresiones en JSP (JSP Expresion Language)
		Las expresiones JSP nos permiten dar dinamismo a nuestras paginas
	 -->
	 <h1>Bienvenido a nuestra pagina de listado de Productos </h1>
	 <a href="index.html">Volver a inicio</a>
	 <br/>
	 
	 <!-- Utilizando JSP EL podemos acceder a los atributos de la request en cualquier momento  -->
	 <h3 style="color:red">${mensajeError}</h3>
	 <h3 style="color:blue">${mensaje}</h3>
	 
	 <h2>Lista de productos</h2>
	 <!-- podriamos hacer esto, pero la lista aparecia sin formato -->
	 
	 <!-- para darle formato, tenemos que apoyarnos en unas librerias
	 	que se llaman jstl
	  -->
	  <table>
	  	<tr>
	  		<th>Codigo</th>
	  		<th>Descripcion</th>
	  		<th>Precio</th>
	  		<th>Stock</th>
	  	
	  	</tr>
	  	<!-- PAra utilizar estas etiquetas tienen que estar dadas de alta en la parte de arriba
	  	de la pagina y tener las librerias importadas en el proyecto (maven) -->
	  	<c:forEach items="${listaProductos}" var="producto">
	  		<tr>
		    <td>${producto.codigo}</td>
		    <td>${producto.descripcion}</td>
		    <td>${producto.precio}</td>
		    <td>${producto.stock}</td>
		    
		    </tr>
		</c:forEach>
	  </table>
	  
	  <!-- Tambien podriamos hacerlo con scriptlets (apuntes) -->
</body>
</html>