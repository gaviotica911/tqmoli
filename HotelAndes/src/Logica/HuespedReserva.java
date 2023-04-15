package Logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class HuespedReserva {
	private String nombre; 
	private int documento;
	private String correo;
	private String celular;
	private HashMap<String,Consumo> consumos=new  HashMap<String,Consumo>();
	
	
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
	
	public HashMap<String,Consumo> getConsumos() {
		return consumos;
	}


	public  HuespedReserva(String nombre, int documento, String correo, String celular,  HashMap<String,Consumo> consumos) {
		this.consumos=consumos;
		this.nombre = nombre;
		this.documento = documento;
		this.correo = correo;
		this.celular = celular;
		
	}


	


	
	
	
	

}
