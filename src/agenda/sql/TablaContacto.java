/*
 * Creado el 22/11/2004 a las 22/11/2004 por IvanC
*/
package agenda.sql;
import agenda.beans.*;
import java.sql.*;

import javax.servlet.*;

public class TablaContacto {
	
	Connection c=null;
	
	public TablaContacto(Connection c){
		System.out.print("Clase T.C.:\n");
		if(c==null)throw new IllegalArgumentException("No hay conexion el la tabla contacto");
		this.c=c;
	}
	
	public Contacto crearContacto(Contacto nuevo,String login) throws ServletException{
		System.out.print("    +T.C.:crearContacto(nuevo,"+login+"): actualizando la base de datos... ");
		
		Statement stmt=null;
		ResultSet rset=null;
		String Query=null;
		int maximo=0;
		
		try {

			stmt=c.createStatement();
			rset=stmt.executeQuery("SELECT max(codContacto) as maximo FROM CONTACTO");
			if(rset.next())maximo=rset.getInt("maximo");
			
			nuevo.setLogin(login);
			nuevo.setCodContacto(1+maximo);
			
			Query="INSERT INTO contacto values('"+nuevo.getCodContacto()+"','" +
				   nuevo.getLogin()+"','"+nuevo.getNombres()+"','"+nuevo.getApellidos()+"','"+        
				   nuevo.getSexo()+"','"+nuevo.getDni()+"','"+nuevo.getTelefono()+"','"+
				   nuevo.getEmail()+"','"+nuevo.getDireccion()+"','"+nuevo.getFechaDeNacimiento()+"')";

			
			stmt.executeUpdate(Query);System.out.println("listo!!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("error al crear contacto"+e);
		}
		return nuevo;
		
	}
	
	public void eliminarContacto(int codContacto){
		System.out.print("    +T.C.:eliminarContacto("+codContacto+"): actualizando la base de datos... ");
		String upd="DELETE FROM CONTACTO WHERE codContacto="+codContacto+"";
		try {
			Statement stmt=c.createStatement();
			stmt.executeUpdate(upd);System.out.println("listo!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Contacto[] traerContactos(String login) throws ServletException{
		System.out.println("    +T.C.:traerContactos("+login+"): recuperando de la base de datos... ");
		Contacto[] contactos=null;
	
		try{
			String Query="SELECT * FROM contacto " +
						 " WHERE login='"+login+"'";
			Statement stmt=c.createStatement();
			ResultSet rset=stmt.executeQuery(Query);
			int tamaño=0;
								
			while(rset.next()){
				tamaño=rset.getRow();
			}
			
			rset=stmt.executeQuery(Query);
			contactos=guardarArrayContacto(rset,tamaño);
			stmt.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new ServletException("nada");
		}
		return contactos;
	}	
	
	public Contacto traerContacto(int codContacto) throws ServletException{
		System.out.println("    +T.C.:traerContacto("+codContacto+"): recuperando de la base de datos... ");
		Contacto[] contactos=null;
	
	try{
		String Query="SELECT * FROM contacto " +
					 " WHERE codContacto="+codContacto+" ";
		Statement stmt=c.createStatement();
		ResultSet rset=stmt.executeQuery(Query);
		int tamaño=0;
		
		while(rset.next()){
			tamaño=rset.getRow();
		}
		rset=stmt.executeQuery(Query);
			
		contactos=guardarArrayContacto(rset,tamaño);
		stmt.close();
	}catch(SQLException sqle){
		sqle.printStackTrace();
		throw new ServletException("nada");
	}
	return contactos[0];
}
	
	public Contacto[] traerContactos(String login,String columna) throws ServletException{
		System.out.println("    +T.C.:traerContactos("+login+","+columna+"): recuperando de la base de datos... ");
		Contacto[] contactos=null;
				try{
					String Query="SELECT * FROM contacto " +
								 " WHERE login='"+login+"' " +
								 " ORDER BY '"+columna+"'";
					Statement stmt=c.createStatement();
					ResultSet rset=stmt.executeQuery(Query);
					int tamaño=0;
					while(rset.next()){
						tamaño=rset.getRow();
					}
					rset=stmt.executeQuery(Query);
					contactos=guardarArrayContacto(rset,tamaño);
					stmt.close();
				}catch(SQLException sqle){
					sqle.printStackTrace();
					throw new ServletException("nada");
				}
				return contactos;
	}

	public Contacto[] traerContactos(String login,String columna,String AscDesc) throws ServletException{
		System.out.println("    +T.C.:traerContactos("+login+","+columna+","+AscDesc+"): recuperando de la base de datos... ");
		Contacto[] contactos=null;
				try{
					String Query="SELECT * FROM contacto " +
								 " WHERE login='"+login+"' " +
								 " ORDER BY '"+columna+"' "+AscDesc+" ";
					Statement stmt=c.createStatement();
					ResultSet rset=stmt.executeQuery(Query);
					int tamaño=0;
						while(rset.next()){
							tamaño=rset.getRow();
						}
					rset=stmt.executeQuery(Query);
					contactos=guardarArrayContacto(rset,tamaño);
					stmt.close();
				}catch(SQLException sqle){
					sqle.printStackTrace();
					throw new ServletException("nada");
				}
				return contactos;
	}

	public Contacto[] guardarArrayContacto(ResultSet rset,int tamaño) throws ServletException{
		System.out.println("       +T.C.:guardarArrayContacto(rset,"+tamaño+"): creando un array["+tamaño+"]... ");
		Contacto[] contactos=null;
		try{
			
			contactos=new Contacto[tamaño];
			Contacto contacto;
			
			for(int i=0;rset.next()&&i<tamaño;i++){
				contacto=new Contacto();
				
				contacto.setCodContacto(Integer.parseInt(rset.getString("codContacto")));
				contacto.setLogin(rset.getString("login"));
				contacto.setNombres(rset.getString("nombres"));
				contacto.setApellidos(rset.getString("apellidos"));
				contacto.setSexo(rset.getString("sexo"));
				contacto.setDni(rset.getString("dni"));
				contacto.setTelefono(rset.getString("telefono"));
				contacto.setEmail(rset.getString("email"));
				contacto.setDireccion(rset.getString("direccion"));
				contacto.setFechaDeNacimiento(rset.getString("fechaDeNacimiento"));
	
				contactos[i]=contacto;System.out.println("-->["+i+"]");
			}
			
		}catch(Exception sqle){
			contactos=null;
			throw new ServletException("error...."+sqle);						
		}
		return contactos;
	}
	
	public Contacto[] buscarContactoPorPalabra(String login,String columna,String palabra) throws ServletException{
		System.out.println("    +T.C.:buscarContactoPorPalabra("+login+","+columna+","+palabra+"): recuperando de la base de datos... ");
		Contacto[] contacto=null;
		
		try {
			Statement stmt=c.createStatement();
			
			String Query="SELECT * FROM contacto " +
						 "WHERE "+columna+" LIKE '%"+palabra+"%' and login='"+login+"' ";
			
			ResultSet rset=stmt.executeQuery(Query);
			int tamaño=0;
				while(rset.next()){
					tamaño=rset.getRow();
				}
			rset=stmt.executeQuery(Query);
			contacto=guardarArrayContacto(rset,tamaño);
		} catch (SQLException e) {
			e.printStackTrace();
			contacto=null;
		}


		return contacto;
	}

	public Contacto[] buscarContactoPorPalabra(String login,String palabra) throws ServletException{
		System.out.println("    +T.C.:buscarContactoPorPalabra("+login+","+palabra+"): recuperando de la base de datos... ");
		Contacto[] contactos=null;
		
		try {
			Statement stmt=c.createStatement();
			String Query="SELECT * FROM contacto " +
						"WHERE " +
						"(nombres LIKE '%"+palabra+"%' or " +
						" apellidos LIKE '%"+palabra+"%' or " +
						" sexo LIKE '%"+palabra+"%' or " +
						" dni LIKE '%"+palabra+"%' or " +
						" telefono LIKE '%"+palabra+"%' or " +
						" email LIKE '%"+palabra+"%' or " +
						" direccion LIKE '%"+palabra+"%' or " +
						" fechaDeNacimiento LIKE '%"+palabra+"%')  and login='"+login+"' ";
						
			ResultSet rset=stmt.executeQuery(Query);
			int tamaño=0;
				while(rset.next()){
					tamaño=rset.getRow();
				}
			rset=stmt.executeQuery(Query);
			contactos=guardarArrayContacto(rset,tamaño);
			stmt.close();						
		} catch (SQLException e) {
			e.printStackTrace();
			contactos=null;
		}
		return contactos;
	}
	
	public Contacto[] buscarContactoPorLetra(String login,String columna,String letra) throws ServletException{
		System.out.println("    +T.C.:buscarContactoPorLetra("+login+","+columna+","+letra+"): recuperando de la base de datos... ");
		Contacto[] contactos=null;
		
		try {
			Statement stmt=c.createStatement();
			String Query="SELECT * FROM contacto " +
						 "WHERE "+columna+" LIKE '"+letra+"%'  and login='"+login+"' ";
			
			ResultSet rset=stmt.executeQuery(Query);
			int tamaño=0;
				while(rset.next()){
					tamaño=rset.getRow();
				}
			rset=stmt.executeQuery(Query);
			contactos=guardarArrayContacto(rset,tamaño);
			stmt.close();						
		} catch (SQLException e) {
			e.printStackTrace();
			contactos=null;
		}


		return contactos;
	}

	public Contacto[] buscarContactoPorLetra(String login,String letra) throws ServletException{
		System.out.println("    +T.C.:buscarContactoPorLetra("+login+","+letra+"): recuperando de la base de datos... ");
		Contacto[] contactos=null;
		
		try {
			Statement stmt=c.createStatement();
			String Query="SELECT * FROM contacto " +
						"WHERE " +
						" (nombres LIKE '"+letra+"%' or " +
						" apellidos LIKE '"+letra+"%' or " +
						" sexo LIKE '"+letra+"%' or " +
						" dni LIKE '"+letra+"%' or " +
						" telefono LIKE '"+letra+"%' or " +
						" email LIKE '"+letra+"%' or " +
						" direccion LIKE '"+letra+"%' or " +
						" fechaDeNacimiento LIKE '"+letra+"%') and login='"+login+"' ";
						
			ResultSet rset=stmt.executeQuery(Query);
			int tamaño=0;
				while(rset.next()){
					tamaño=rset.getRow();
				}
			rset=stmt.executeQuery(Query);
			contactos=guardarArrayContacto(rset,tamaño);
						
		} catch (SQLException e) {
			e.printStackTrace();
			contactos=null;
		}
		return contactos;
	}
	
	
	public void modificarContacto(Contacto modificado,int codContacto){
		System.out.print("    +T.C.:modificarContacto(modificado,"+codContacto+"): actualizando la base de datos... ");
			
			String upd="UPDATE CONTACTO " +						"SET nombres='"+modificado.getNombres()+"', "+
						"apellidos='"+modificado.getApellidos()+"', "+
						"sexo='"+modificado.getSexo()+"', "+
						"dni='"+modificado.getDni()+"', "+
						"telefono='"+modificado.getTelefono()+"', "+
						"email='"+modificado.getEmail()+"', "+
						"direccion='"+modificado.getDireccion()+"', "+
						"fechaDeNacimiento='"+modificado.getFechaDeNacimiento()+"' "+
						"WHERE codContacto="+codContacto+" ";
			try {
				Statement stmt=c.createStatement();
				stmt.executeUpdate(upd);System.out.println("listo!!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}

