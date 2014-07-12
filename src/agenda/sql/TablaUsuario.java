/*
 * Creado el 22/11/2004 a las 22/11/2004 por IvanC
*/
package agenda.sql;
import java.sql.*;

import javax.servlet.*;

import agenda.beans.Usuario;

public class TablaUsuario {
	
	Connection c=null;
	
	public TablaUsuario(Connection c){
		System.out.print("Clase T.U.:\n");
		if(c==null)
			throw new IllegalArgumentException("No hay conexion en la tabla usuario");
		this.c=c;
	}
	public Usuario validarUsuario(String login,String password) throws ServletException{
		System.out.println("    +T.U.:validarUsuario("+login+","+password+"): Validando usuario...");
		Usuario usuario=null;
		Statement stmt=null;
		ResultSet rset=null;
		
		if(login==null)throw new ServletException("el login es nulo en tabla usuario");
		if(password==null)throw new ServletException("el password es nulo en la tabla usuario");
		if(login.trim().equals(""))throw new ServletException("el login es vecio en tabla usuario");
		if(password.trim().equals(""))throw new ServletException("el password es vacio en tabla usuario");

		
		try{
			String Query="select * from usuario where login='"+login+"' and password='"+password+"'";
			stmt=c.createStatement();
			rset=stmt.executeQuery(Query);
			
			if(rset.next()){
				usuario=new Usuario();
				usuario.setLogin(login);
				usuario.setNombre(rset.getString("nombre"));
			}
			stmt.close();
		} catch (SQLException sqle) {
			String error = "<";
			while(sqle!=null){
				error+=sqle;
				sqle=sqle.getNextException();
			}
			throw new ServletException("el error SQL "+error+" >");
		}catch(Exception error){
			throw new ServletException("el error java"+error);
			
		}
		return usuario;
	}
	
	public void crearUsuario(Usuario nuevo) throws ServletException{
		System.out.println("    +T.U.:crearUsuario(nuevo):Usuario creado Correctamente...");
		
		String Query="INSERT INTO usuario values('"+nuevo.getLogin()+"','"+nuevo.getNombre()+"','"+nuevo.getPassword()+"')";
		Statement stmt=null;
		
		try {
			stmt = c.createStatement();
			stmt.executeUpdate(Query);
			stmt.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new ServletException("Error al crear usuario "+e1);
		}
		
	}

}
