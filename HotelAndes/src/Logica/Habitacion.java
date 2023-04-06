package Logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Habitacion {
	CargardorArchivo cargador= new CargardorArchivo();
	//id;ubicacion;capacidad;camas;precioFijo;vista;balcon;cocina;tipo
	public String id;
	public int capacidad;
	public ArrayList<Cama> cama;
	public String ubicacion;
	public String tipo;
	public float precioFijo;
	public boolean vista;
	public boolean balcon;
	public boolean cocina;
	public HashMap<String, ArrayList<Habitacion>> habitacionesPorTipo= cargador.getHabitacionesPorTipo();
	
	
	
	public Habitacion(String id, int capacidad, ArrayList<Cama> cama, String ubicacion, String tipo, float precioFijo,
			boolean vista, boolean balcon, boolean cocina) {
		
		this.id = id;
		this.capacidad = capacidad;
		this.cama = cama;
		this.ubicacion = ubicacion;
		this.tipo = tipo;
		this.precioFijo = precioFijo;
		this.vista = vista;
		this.balcon = balcon;
		this.cocina = cocina;
	}



	public String getId() {
		return id;
	}



	public int getCapacidad() {
		return capacidad;
	}



	public ArrayList<Cama> getCama() {
		return cama;
	}



	public String getUbicacion() {
		return ubicacion;
	}



	public String getTipo() {
		return tipo;
	}



	public float getPrecioFijo() {
		return precioFijo;
	}



	public boolean isVista() {
		return vista;
	}



	public boolean isBalcon() {
		return balcon;
	}



	public boolean isCocina() {
		return cocina;
	}

	
	

}
