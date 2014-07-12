<html><head><title>Untitled</title></head><body>
<%@page import="agenda.beans.*"%>
<%@page import="agenda.servlet.*"%>
<%@page import="agenda.sql.*"%>

<%@page import="java.sql.*"%>

<%
	Conexion C=new Conexion();
	C.init();
	
	TablaUsuario tUsuario=new TablaUsuario(C.getConnection());
	Usuario usuario=tUsuario.validarUsuario("jod","gino");
	if(usuario!=null)out.println("bienvenido "+usuario.getNombre());
	else out.println("nada");
	Usuario nuevo=new Usuario();
	nuevo.setNombre("ivan");
	nuevo.setPassword("telesup");
	nuevo.setLogin("yanos");
	tUsuario.crearUsuario(nuevo);
%>
L
</body>
</html>
