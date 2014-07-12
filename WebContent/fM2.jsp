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
aqui <%=contacto.getNombres()%>
soy  <%=contacto.getApellidos()%>

<%if(contacto.getSexo().equals("M"))out.print("SelectedM");%>
<%if(contacto.getSexo().equals("F"))out.print("SelectedF");%>

</body>
</html>
