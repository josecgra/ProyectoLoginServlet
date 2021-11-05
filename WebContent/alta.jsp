<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Formulario de Alta de un Alumno</h1>
	
	<form action="alta" method="post">
		<label for="dni">DNI: </label>
		<input type="text" name="dni" required><br>
		<label for="nombre">Nombre: </label>
		<input type="text" name="nombre" required><br>
		<label for="apellidos">Apellidos: </label>
		<input type="text" name="apellidos" required><br>
		<label for="edad">Edad: </label>
		<input type="number" name="edad" required><br>
		<label for="email">Email: </label>
		<input type="email" name="email" required><br>
		<label for="pass">Password: </label>
		<input type="password" name="pass" required><br>
		<label for="rol">Rol: </label>
		<input type="text" name="rol" required><br>
		<input type="submit" value="Registrar">
		<span style="color:red">
		
		<%
			if (request.getAttribute("msgerr")!=null) {
				if (request.getAttribute("msgerr").equals("")){
					out.write("");
				}else{
					out.write(request.getAttribute("msgerr").toString());
				}
			} else {
				out.write("");
			}
		
		%>
		
		</span>
	</form>
	
</body>
</html>