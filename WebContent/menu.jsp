<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>Menu</title>
</head>
<%@page import="agenda.beans.*"%>
<%
	HttpSession miSession=request.getSession(true);
	Usuario usuario=(Usuario)miSession.getValue("usuario");
	Contacto[] contactos=(Contacto[])miSession.getValue("contactos");
%>
<%
	boolean impSistema=false;
	boolean impContactos=false;
	int numContactos=0;
	int actual=0;
	String msgNumContactos="No tienes Contactos";
%>
<%
	if(usuario!=null){
		impSistema=true;
		if(contactos!=null){
			numContactos=((Integer)miSession.getValue("cantidad")).intValue();
			if(numContactos!=0){
				msgNumContactos="Tienes "+numContactos+" Contactos";
				if(contactos.length!=0){
					actual=contactos.length;
					impContactos=true;
				}
			}
		}else{out.println("El Array de Contactos es nulo");}
	}else{out.println("El usuario es nulo");}
%>

<%if(impSistema){%>

<body>

<table>
<tr>
	<td>Bienvenido: <%=usuario.getNombre()%></td>
</tr>
<tr>
	<td><a href="index.html">Salir</a></td>
</tr>
<tr>
	<td><a href="fC.html"><img src="NEW1.gif" alt="NUEVO CONTACTO!!" width="49" height="15" border="1"></a></td>
</tr>
<tr>
	<td><%=msgNumContactos%></td>
</tr>
</table>
<table cellspacing="2" cellpadding="2" border="0">
<tr>
	<form action="P.C." method="post">
	<input type="hidden" name="comando" value="5">
	<input type="hidden" name="url" value="menu.jsp">
    <td><input type="submit" name="Busca" value="Busca"><img src="BUSCA.gif" width="32" height="32" alt="" border="0"></td>
    <td><input type="text" name="palabra"></td>
	</form>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=a">A</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=b">B</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=c">C</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=d">D</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=e">E</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=f">F</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=g">G</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=h">H</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=i">I</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=j">J</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=k">K</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=l">L</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=m">M</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=n">N</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=ñ">Ñ</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=o">O</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=p">P</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=q">Q</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=r">R</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=s">S</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=t">T</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=u">U</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=v">V</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=w">W</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=x">X</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=y">Y</a></td>
	<td><a href="P.C.?comando=4&url=menu.jsp&letra=z">Z</a></td>
</tr>
<tr>
	<td>Aqui hay <%=actual%> de <%=numContactos%></td>
</tr>

</table>

<%	if(impContactos){%>

  <form action="P.C."><!--Envio al "ContactoServlet"-->
   	<input type="hidden" name="comando" value="3"><!--Envio al Metodo "Elimnar"-->
	<input type="hidden" name="url" value="menu.jsp"><!--Requerido por el metodo "Eliminar"-->

<table cellspacing="2" cellpadding="2" border="1">
<tr>
    <td>nombres</td>
    <td>apellidos</td>
    <td>sexo</td>
    <td>dni</td>
    <td>telefono</td>
    <td>email</td>
    <td>direccion</td>
    <td>fechaDeNacimiento</td>
   	<TD>MODIFICAR</TD>
	<td><input type="submit" name="boton" value="Eliminar"></td><!--Eliminar!!-->
</tr>

<!--Imprimiendo el Array de Contactos-->
<%  	for(int i=contactos.length-1;i>=0;i--){%>
<tr>
    <td><%=contactos[i].getNombres()%></td>
    <td><%=contactos[i].getApellidos()%></td>
    <td><%=contactos[i].getSexo()%></td>
    <td><%=contactos[i].getDni()%></td>
    <td><%=contactos[i].getTelefono()%></td>
    <td><%=contactos[i].getEmail()%></td>
    <td><%=contactos[i].getDireccion()%></td>
    <td><%=contactos[i].getFechaDeNacimiento()%></td>
	
	<!--Modificar envia "comando=2" "m1=1" "CodContacto=<!%codigo de contacto actual%>"-->
	<td><a href="P.C.?comando=2&m1=1&codContacto=<%=contactos[i].getCodContacto()%>">Modificar<a/></td>
	<!--
	    Todos los checkbox se llamaran "eliminar" 
		y sus values son el codigo de contacto de cada contacto impreso
		En el servlet se recuperara por medio de un Array
	 !-->
	 <td><input type="checkbox" name="eliminar" value="<%=contactos[i].getCodContacto()%>"></td>
	
</tr>
<%		}%>
<!--Terminando el Bucle de impresion-->

	</form>
</table>
<%	}%>

</body>
<%}else{out.println("|| Ingresa Adecuadamente!! por el index... ||");}%>

</html>
