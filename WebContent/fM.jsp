<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>Untitled</title>
</head>

<body>
<%@page import="agenda.beans.*"%>
<%
	HttpSession miSession=request.getSession(true);
	Contacto contacto=(Contacto)miSession.getValue("contactoM");
%>
<form action="P.C." method="post">
<table align="center" bgcolor="#FFFF00" cellspacing="0" cellpadding="0" border="1">
<tr>
    <td colspan="2">Formulario de Modificacion de Contacto</td>
</tr>
<tr>
    <td>Nombres</td>
    <td><input type="text" name="nombres" value="<%=contacto.getNombres()%>"></td>
</tr>
<tr>
    <td>Apellidos</td>
    <td><input type="text" name="apellidos" value="<%=contacto.getApellidos()%>"></td>
</tr>
<tr>
    <td>Sexo</td>
    <td><select name="sexo">
	<option value="M" <%if(contacto.getSexo().equals("M"))out.print("Selected");%>>Masculino</option>
	<option value="F" <%if(contacto.getSexo().equals("F"))out.print("Selected");%>>Femenino</option>
</select></td>
</tr>
<tr>
    <td>Dni</td>
    <td><input type="text" name="dni" size="8" maxlength="8" value="<%=contacto.getDni()%>"></td>
</tr>
<tr>
    <td>Telefono</td>
    <td><input type="text" name="telefono" size="12" maxlength="12" value="<%=contacto.getTelefono()%>"></td>
</tr>
<tr>
    <td>Email</td>
    <td><input type="text" name="email" value="<%=contacto.getEmail()%>"></td>
</tr>
<tr>
    <td>Direccion</td>
    <td><input type="text" name="direccion" value="<%=contacto.getDireccion()%>"></td>
</tr>
<tr>
    <td>FechaDeNacimiento</td>
    <td><input type="text" name="fechaDeNacimiento" value="<%=contacto.getFechaDeNacimiento()%>"></td>
</tr>
<tr>
    <td><input type="submit" name="Aceptar" value="Aceptar"></td>
    <td><input type="reset" name="Borrar" value="Restaurar"></td>
	<input type="hidden" name="comando" value="2">
	<input type="hidden" name="m2" value="1">
</tr>
<tr><img src="MODIFICA.gif" width="150" height="150" alt="" border="0"></tr>
</table>


</form>


</body>
</html>
