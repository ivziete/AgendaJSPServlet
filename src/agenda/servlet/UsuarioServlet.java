/*
 * Creado el 14/11/2004 a las 14/11/2004 por IvanC
*/
package agenda.servlet;

import javax.servlet.http.*;
import javax.servlet.*;

import java.io.IOException;

import agenda.beans.*;
import agenda.sql.*;

public class UsuarioServlet extends Conexion{
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws IOException,ServletException{
		
		int comando=0;
		try{
			comando=Integer.parseInt(request.getParameter("comando"));
		}catch(Exception e){
			throw new ServletException("Crea un comando");
		}
		
		switch(comando){
			case 1:
				validar(request,response);
				break;
			case 2:
				nuevo(request,response);
				break;
			default:
				throw new ServletException("la opcion no esta diponible");
		}
		
	}
	private void validar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n <->U.S.: validar(request,reponse): validando...");
		
		String url="index.html";
		String login=request.getParameter("login");
		String password=request.getParameter("password");

		if(login==null || login.trim().equals("") )throw new ServletException("Login esta Vacio");
		if(password==null || password.trim().equals("") )throw new ServletException("password esta Vacio");
		
		TablaUsuario tUsuario=new TablaUsuario(this.getConnection());
		Usuario usuarioActual=tUsuario.validarUsuario(login,password);
		
		if(usuarioActual!=null){
			url="menu.jsp";
			HttpSession miSession=request.getSession(true);
			
			miSession.setAttribute("usuario",usuarioActual);
			miSession.setAttribute("login",usuarioActual.getLogin());
			System.out.println("\n\t[Ya estamos adentro!!]");
		
			TablaContacto tContacto=new TablaContacto(getConnection());
		  	
		  	Contacto[] contactos=tContacto.traerContactos(login);
		
			miSession.setAttribute("contactos",contactos);
			miSession.setAttribute("cantidad",new Integer(contactos.length));
			
			System.out.println("  -Retorno: usuario, login="+usuarioActual.getLogin()+", cantidad="+contactos.length+", contactos");
		}else{System.out.println("\n\t[No pudimos ingresar!!]");}
		
		System.out.println(" <->Redireccionando a: "+url);
		response.sendRedirect(url);
		
	}
	
	private void nuevo(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		System.out.println("\n <->U.S.: nuevo(request,response): Creando nuevo usuario...");
		
		
		String url="index.html";
		
		String login=request.getParameter("login");
		String password=request.getParameter("password");
		String password1=request.getParameter("password1");
		String nombre=request.getParameter("nombre");
		
		if(login==null || login.trim().equals(""))throw new ServletException("El login esta vacio");
		if(password==null || password.trim().equals(""))throw new ServletException("El password esta vacio");
		if(nombre==null || nombre.trim().equals(""))throw new ServletException("El nombre esta vacio");
		if(!password.equals(password1))throw new ServletException("Los passwords son diferentes!!");
		
		Usuario nuevo=new Usuario();
		nuevo.setLogin(login);
		nuevo.setNombre(nombre);
		nuevo.setPassword(password);
		
		
		TablaUsuario tUsuario=new TablaUsuario(getConnection());
		tUsuario.crearUsuario(nuevo);
		
		System.out.println(" <->Redireccionando a: "+url);
		response.sendRedirect(url);
		
	}

	

}
