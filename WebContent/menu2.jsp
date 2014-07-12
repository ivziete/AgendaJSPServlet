<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>Untitled</title>
</head>
<%@page import="agenda.beans.*"%>
<%
	HttpSession miSession=request.getSession(true);
	Usuario usuario=(Usuario)miSession.getValue("usuario");
	Contacto[] contactos=(Contacto[])miSession.getValue("contactos");
	boolean imprimir=false;
	String numContactos="No tienes Contactos";
%>
Bienvenido: <%=usuario.getNombre()%>
tienes <%=contactos.length%>
<%
	if(contactos!=null){
		if(contactos.length!=0){
			imprimir=true;
			numContactos="Tienes "+contactos.length+" Contactos";
		}
	}
%>
<body>


<form action="fc.html">
<table cellspacing="2" cellpadding="2" border="0">
<tr>
    <td colspan="11"><%=numContactos%> y <%if(contactos==null)out.println("contactos nulo!");%></td>
	<td><input type="submit" name="boton" value="Nuevo Contacto"></td>
</tr>
<%if(imprimir){%>
<tr>
    <td>nombres</td>
    <td>apellidos</td>
    <td>sexo</td>
    <td>dni</td>
    <td>telefono</td>
    <td>email</td>
    <td>direccion</td>
    <td>fechaDeNacimiento</td>
</tr>
<%  for(int i=contactos.length-1;i>=0;i--){%>
<tr>
    <td><%=contactos[i].getNombres()%></td>
    <td><%=contactos[i].getApellidos()%></td>
    <td><%=contactos[i].getSexo()%></td>
    <td><%=contactos[i].getDni()%></td>
    <td><%=contactos[i].getTelefono()%></td>
    <td><%=contactos[i].getEmail()%></td>
    <td><%=contactos[i].getDireccion()%></td>
    <td><%=contactos[i].getFechaDeNacimiento()%></td>
	<td><a href="P.C.?comando=2&m1=1&codContacto=<%=contactos[i].getCodContacto()%>">Modificar<a/></td>
	<td><input type="checkbox" name="eliminar" value="<%=contactos[i].getCodContacto()%>"></td>
</tr>
<%	}%>
<%}%>
</table>
<input type="hidden" name="url" value="menu.jsp">
</form>
</body>
</html>