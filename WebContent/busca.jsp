
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
	String numContactos="No Se Encontro Contactos";
%>
<body>
Bienvenido: <%=usuario.getNombre()%>
<%
	if(contactos!=null){
		if(contactos.length!=0){
			imprimir=true;
			numContactos="Tienes "+contactos.length+" Contactos encontrados";
		}
	}
%>
<table align="right" bgcolor="#FF00FF" cellspacing="1" cellpadding="1" border="1" frame="vsides" rules="cols">
<tr>
    <td rowspan="3"></td>
    <td colspan="2"></td>
    <td rowspan="3"></td>
</tr>
<tr>
<form action="P.C." method="post">
    <td><input type="submit" name="Busca" value="Busca"></td>
    <td><input type="text" name="palabra"></td>
	<input type="hidden" name="comando" value="5">
	<input type="hidden" name="url" value="busca.jsp">
</form>
</tr>
<tr>
    <td colspan="2"></td>
</tr>
</table>

<form action="fC.html">
<table cellspacing="2" cellpadding="2" border="0">
<tr>
    <td colspan="11"><%=numContactos%> y <%if(contactos==null)out.println("contactos nulo!");%></td>
	<td><input type="submit" name="boton" value="Nuevo Contacto"></td>
</tr>
</form>
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
	<td>modificar</td>
<form action="P.C." method="post">
	<td><input type="submit" value="eliminar"></td>
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
<input type="hidden" name="comando" value="3">
<input type="hidden" name="url" value="busca.jsp">
</form>
</table>
<table bgcolor="#00FF00" cellspacing="1" cellpadding="1" border="1" frame="above" rules="groups">
<tr>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=a">A</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=b">B</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=c">C</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=d">D</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=e">E</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=f">F</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=g">G</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=h">H</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=i">I</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=j">J</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=k">K</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=l">L</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=m">M</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=ñ">Ñ</a></td>
</tr>
<tr>
    <td colspan="14"></td>
</tr>
<tr>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=n">N</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=o">O</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=p">P</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=q">Q</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=r">R</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=s">S</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=t">T</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=u">U</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=v">V</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=w">W</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=x">X</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=y">Y</a></td>
	<td><a href="P.C.?comando=4&url=busca.jsp&letra=z">Z</a></td>
</tr>
</table>
</body>
</html>
