package Logica;

import java.util.Date;

public class HuespedReserva {
	private String nombre; 
	private int documento;
	private String correo;
	private String celular;
	FuncionesEmpleado empleado= new FuncionesEmpleado();
	public String getNombre() {
		return nombre;
	}


	public int getDocumento() {
		return documento;
	}


	public String getCorreo() {
		return correo;
	}


	public String getCelular() {
		return celular;
	}


	public  HuespedReserva(String nombre, int documento, String correo, String celular) {
		this.nombre = nombre;
		this.documento = documento;
		this.correo = correo;
		this.celular = celular;
		
	}


	


	
	
	
	

}
