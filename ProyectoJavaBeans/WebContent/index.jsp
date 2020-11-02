<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<jsp:useBean id="yo" class="escuela.Estudiante" />
<jsp:setProperty name="yo" property="nombre" value="Perico de los Palotes" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Página con un JavaBean</title>
</head>
<body>
<%
yo.setCurso("Acceso a datos");
%>
<h1>Estudiante: <%=yo.getNombre() %></h1>
<h2>Fecha/hora matricula: <%=yo.getFechaHoraMatricula() %></h2>
<h3>Curso: <%=yo.getCurso() %></h3>
</body>
</html>