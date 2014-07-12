
package agenda.servlet;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class Conexion extends HttpServlet{
	
	public Connection c=null;
	public String Driver="org.hsqldb.jdbc.JDBCDriver";
	public String Url="jdbc:hsqldb:hsql://localhost";
	public String Usuario="sa";
	public String Password="";
	
	
	public void init()throws ServletException{
		try{
		Class.forName(Driver).newInstance();
		}catch(Exception e){
			throw new ServletException("No Es posible conectarse con el driver!!");
		}
		System.out.println("Driver Cargado....");
	}
	
	public Connection getConnection() throws ServletException{
		
		try{
			c=DriverManager.getConnection(Url,Usuario,Password);
		}catch(SQLException sqle){
			throw new ServletException("No es posible conectarse a al base de datos!!");
		}
		System.out.print("\n  -Conexion lista....-->");
		return c;
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		System.out.println("\n*Metodo doGet() Utilizado...");
		doPost(request,response);
	}

}
