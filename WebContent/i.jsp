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
%>
<%
	String msgNumContactos="No tienes Contactos";
	boolean impSistema=false;
	boolean impContactos=false;
	if(usuario!=null){
		impSistema=true;
		if(contactos!=null){
			if(contactos.length!=0){
				impContactos=true;
				msgNumContactos="Tienes "+contactos.length+" Contactos";
			}
		}
	}
%>
<body>
<%if(impSistema){%>
	Bienvenido:<%=usuario.getNombre()%>
<%}else{%>
	Por favor ingresa adecuadamente!!
<%}%>
</body>
</html>
