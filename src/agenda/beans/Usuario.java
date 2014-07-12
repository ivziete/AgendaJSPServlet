/*
 * Creado el 22/11/2004 a las 22/11/2004 por IvanC
*/
package agenda.beans;


public class Usuario {
	
	private String login;
	private String nombre;
	private String password; 
	
	public Usuario(){
		System.out.println("\t*Bean Usuario utilizado...");
	}

	public String getLogin() {
		return login;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setLogin(String string) {
		login = string;
	}

	public void setNombre(String string) {
		nombre = string;
	}

	public void setPassword(String string) {
		password = string;
	}

}
