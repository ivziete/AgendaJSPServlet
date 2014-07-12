/*
 * Creado el 22/11/2004 a las 22/11/2004 por IvanC
*/
package agenda.beans;


public class Contacto {
	
	private int codContacto;      
	private String login;            
	private String nombres;
	private String apellidos;        
	private String sexo;             
	private String dni;
	private String telefono;
	private String email;
	private String direccion;
	private String fechaDeNacimiento;

	public Contacto(){
		System.out.print("\t*Bean Contacto utilizado...");
	}
	
	public String getApellidos() {
		return apellidos;
	}

	public int getCodContacto() {
		return codContacto;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getDni() {
		return dni;
	}

	public String getEmail() {
		return email;
	}

	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public String getLogin() {
		return login;
	}

	public String getNombres() {
		return nombres;
	}

	public String getSexo() {
		return sexo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setApellidos(String string) {
		apellidos = string;
	}

	public void setCodContacto(int i) {
		codContacto = i;
	}

	public void setDireccion(String string) {
		direccion = string;
	}

	public void setDni(String string) {
		dni = string;
	}

	public void setEmail(String string) {
		email = string;
	}

	public void setFechaDeNacimiento(String string) {
		fechaDeNacimiento = string;
	}

	public void setLogin(String string) {
		login = string;
	}

	public void setNombres(String string) {
		nombres = string;
	}

	public void setSexo(String string) {
		sexo = string;
	}

	public void setTelefono(String string) {
		telefono = string;
	}

}
