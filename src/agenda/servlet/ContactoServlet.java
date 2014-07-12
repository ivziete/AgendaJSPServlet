/*
 * Creado el 14/11/2004 a las 14/11/2004 por IvanC
*/
package agenda.servlet;

import agenda.sql.*;
import agenda.beans.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;

public class ContactoServlet extends Conexion{
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws IOException,ServletException{
		
		int comando=0;
		
		try {
			comando=Integer.parseInt(request.getParameter("comando"));
		} catch (Exception e) {
			throw new ServletException("Crea el comando!");
		}
		
		switch(comando){
			case 1:
				nuevo(request,response);
				break;
			case 2:
				modificar(request,response);
				break;
			case 3:
				eliminar(request,response);
				break;
			case 4:
				porLetra(request,response);
				break;
			case 5:
				porPalabra(request,response);
				break;
			case 6:
				Todo(request,response);
				break;
			default:
				throw new ServletException("La opcion no existe!");
		}
	}

	private void Todo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url=request.getParameter("url");
		HttpSession miSession=request.getSession(true);
		String login=(String) miSession.getAttribute("login");
		System.out.println("\n <->C.S.: Todo(request,response): Retorna todos los contactos de "+login+"...");
		TablaContacto tContacto=new TablaContacto(getConnection());
		Contacto[] contactos=tContacto.traerContactos(login);
		
		System.out.println("  -Retorno: contactos ");
		System.out.println(" <->Redireccionando a: "+url);
		miSession.setAttribute("contactos",contactos);
		response.sendRedirect(url);
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("\n <->C.S.: nuevo(request,response): Crea un nuevo contacto...");
		String url="menu.jsp";

		HttpSession miSession=request.getSession(true);
		String login=(String)miSession.getAttribute("login");
		
				
		String nombres=request.getParameter("nombres");
		String apellidos=request.getParameter("apellidos");
		String sexo=request.getParameter("sexo");
		String dni=request.getParameter("dni");
		String telefono=request.getParameter("telefono");
		String email=request.getParameter("email");
		String direccion=request.getParameter("direccion");
		String fechaDeNacimiento=request.getParameter("fechaDeNacimiento");
		
		
		if(nombres==null||nombres.trim().equals(""))throw new ServletException("Pon el nombre solo eso te pido!!");

		Contacto nuevo=new Contacto();
		if(nombres!=null && !nombres.trim().equals(""))nuevo.setNombres(nombres);
		if(apellidos!=null && !apellidos.trim().equals(""))nuevo.setApellidos(apellidos);
		if(sexo!=null && !sexo.trim().equals(""))nuevo.setSexo(sexo);
		if(dni!=null && !dni.trim().equals(""))nuevo.setDni(dni);
		if(telefono!=null && !telefono.trim().equals(""))nuevo.setTelefono(telefono);
		if(email!=null && !email.trim().equals(""))nuevo.setEmail(email);
		if(direccion!=null && !direccion.trim().equals(""))nuevo.setDireccion(direccion);
		if(fechaDeNacimiento!=null && !fechaDeNacimiento.trim().equals(""))nuevo.setFechaDeNacimiento(fechaDeNacimiento);
		
		
		TablaContacto tContacto=new TablaContacto(getConnection());
		tContacto.crearContacto(nuevo,login);
		Contacto[] contactos=tContacto.traerContactos(login);
		
		System.out.println("  -Retorno: cantidad="+contactos.length+", contactos");
		miSession.setAttribute("contactos",contactos);
		miSession.setAttribute("cantidad",new Integer(contactos.length));
		
		System.out.println(" <->Redireccionando a: "+url);
		response.sendRedirect(url);
		
	}

	private void modificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n <->C.S.: modificar(request,response): Modifica un contacto...");
		
		String m1=request.getParameter("m1");
		String m2=request.getParameter("m2");
		HttpSession miSession=request.getSession(true);
		String url="fM.jsp";
		
		if(m1!=null){
			System.out.println("  ->Metodo1:");
			int codContacto=Integer.parseInt(request.getParameter("codContacto"));
			TablaContacto tContacto=new TablaContacto(getConnection());
			Contacto contactoM=tContacto.traerContacto(codContacto);
			System.out.println("   -Retorno1: codContacto="+codContacto+", contactosM");
			miSession.setAttribute("contactoM",contactoM);
			miSession.setAttribute("codContacto",new Integer(codContacto));
		
			System.out.println("  ->Redireccionando a: "+url);
			response.sendRedirect(url);
			
		}
		if(m2!=null){
			System.out.println("   <-Metodo2:");
			url="menu.jsp";
			int codContacto=((Integer)miSession.getAttribute("codContacto")).intValue();
			String login=(String) miSession.getAttribute("login");
			String nombres=request.getParameter("nombres");
			String apellidos=request.getParameter("apellidos");
			String sexo=request.getParameter("sexo");
			String dni=request.getParameter("dni");
			String telefono=request.getParameter("telefono");
			String email=request.getParameter("email");
			String direccion=request.getParameter("direccion");
			String fechaDeNacimiento=request.getParameter("fechaDeNacimiento");
		
		
    		if(nombres==null||nombres.trim().equals(""))throw new ServletException("Pon el nombre solo eso te pido!!");

			Contacto nuevo=new Contacto();
			if(nombres!=null && !nombres.trim().equals(""))nuevo.setNombres(nombres);
			if(apellidos!=null && !apellidos.trim().equals(""))nuevo.setApellidos(apellidos);
			if(sexo!=null && !sexo.trim().equals(""))nuevo.setSexo(sexo);
			if(dni!=null && !dni.trim().equals(""))nuevo.setDni(dni);
			if(telefono!=null && !telefono.trim().equals(""))nuevo.setTelefono(telefono);
			if(email!=null && !email.trim().equals(""))nuevo.setEmail(email);
			if(direccion!=null && !direccion.trim().equals(""))nuevo.setDireccion(direccion);
			if(fechaDeNacimiento!=null && !fechaDeNacimiento.trim().equals(""))nuevo.setFechaDeNacimiento(fechaDeNacimiento);
		
		
			TablaContacto tContacto=new TablaContacto(getConnection());
			tContacto.modificarContacto(nuevo,codContacto);
			Contacto[] contactos=tContacto.traerContactos(login);
		
			System.out.println("   -Retorno2: contactos");
			miSession.setAttribute("contactos",contactos);
		
			System.out.println("  <-Redireccionando a: "+url);
			response.sendRedirect(url);
				
		}
		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("\n <->C.S.: eliminar(request,response): Elimina contactos marcados...");
		
		String url=request.getParameter("url");
		String[] eliminar=request.getParameterValues("eliminar");
		if(eliminar==null)response.sendRedirect(url);
		
		int cod=0;
		TablaContacto tContacto=new TablaContacto(getConnection());
		for(int i=0;i<eliminar.length;i++){
			cod=Integer.parseInt(eliminar[i]);
			tContacto.eliminarContacto(cod);
		}
		HttpSession miSession=request.getSession(true);
		String login=(String)miSession.getAttribute("login");
		Contacto[] contactos=tContacto.traerContactos(login);
		
		System.out.println("  -Retorno: cantidad="+contactos.length+", contactos");
		miSession.setAttribute("cantidad",new Integer(contactos.length));
		miSession.setAttribute("contactos",contactos);
		
		System.out.println(" <->Redireccionando a: "+url);
		response.sendRedirect(url);
				
	}


	private void porPalabra(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("\n <->C.S.: porPalabra(request,response): Buscando contactos por palabra...");
		
		String url=request.getParameter("url");
		
		String palabra=request.getParameter("palabra");
		if(palabra==null||palabra.trim().equals(""))response.sendRedirect(url);
		
		HttpSession miSession=request.getSession(true);
		String login=(String)miSession.getAttribute("login");
		TablaContacto tContacto=new TablaContacto(getConnection());
		Contacto[] encontrados=tContacto.buscarContactoPorPalabra(login,palabra);
		System.out.println("  -Retorno: contactos");
		System.out.println(" -Redireccionando a: "+url);
		miSession.setAttribute("contactos",encontrados);
		
		System.out.println(" <->Redireccionando a: "+url);
		response.sendRedirect(url);
	}
	

	private void porLetra(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("\n <->C.S.: porLetra(request,response): Buscando contactos por letra...");
		
		String url=request.getParameter("url");
		
		String letra=request.getParameter("letra");
		if(letra==null||letra.trim().equals(""))response.sendRedirect(url);
		
		HttpSession miSession=request.getSession(true);
		String login=(String)miSession.getAttribute("login");
		TablaContacto tContacto=new TablaContacto(getConnection());
		Contacto[] encontrados=tContacto.buscarContactoPorLetra(login,letra);
		
		System.out.println("  -Retorno: contactos");
		System.out.println(" -Redireccionando a: "+url);
		
		miSession.setAttribute("contactos",encontrados);
		
		System.out.println(" <->Redireccionando a: "+url);
		response.sendRedirect(url);
		
	}
}
