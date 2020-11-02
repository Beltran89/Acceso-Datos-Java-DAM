<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

		<H1>Bienvenido a nuestra pagina de listado de Productos</H1>
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
</body>
</html>