package Logica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Controlador {
	CargardorArchivo cargador= new CargardorArchivo();
	public HashMap<String,Cama> camas= new HashMap<String,Cama>();
	public HashMap<String, Habitacion> habitacionies= new  HashMap<String, Habitacion>();
	public HashMap<String, ArrayList<Habitacion>> habitacionesPorTipo= new HashMap <String, ArrayList<Habitacion>>();
	public HashMap<Date, ArrayList<Tarifa>> tarifas= new  HashMap<Date, ArrayList<Tarifa>>();
	public HashMap<Date, String> diasAño= new HashMap<Date, String>();
	public HashMap<Date, Float> tarifaEstandar= new HashMap<Date, Float>();
	public HashMap<Date, Float> tarifaSuite= new HashMap<Date, Float>();
	public HashMap<Date, Float> tarifaSuiteDoble= new HashMap<Date, Float>();
	public HashMap<String, Plato> platos= new  HashMap<String, Plato>();
	public HashMap<String, Bebida> bebidas= new  HashMap<String, Bebida>();
	public HashMap<String, Servicio> servicios= new  HashMap<String, Servicio>();
	public HashMap<String,Reserva> reservas=new HashMap<String, Reserva>();
	
	
	
	public ArrayList<Object> cargarTarifas(String nombre) throws IOException {
		
		File archivoT= new File ("./data/" + nombre);
        ArrayList<Object> tarifas= cargador.cargarTarifas(archivoT);
    	//0 estandar, 1 suite, 2 doble, 3 tarifapor fecha
        Object principal= tarifas.get(0);
         tarifaEstandar= (HashMap<Date, Float>) tarifas.get(1);
        tarifaSuite= (HashMap<Date, Float>) tarifas.get(2);
         tarifaSuiteDoble= (HashMap<Date, Float>) tarifas.get(3);
    
     
        return tarifas;
	}
	
	public HashMap<Date, String> cargarDiasAño() throws IOException{
		diasAño= cargador.cargarDiasAño();
		return diasAño;
	}
	
	public HashMap<String,Plato> cargarPlatos (String nombre) throws IOException {
		File archivoP= new File ("./data/" + nombre);
		platos= cargador.cargarPlato( archivoP);
		return platos;
	}
	
	
	
	public HashMap<String,Bebida> cargarBebidas (String nombre) throws IOException {
		File archivoP= new File ("./data/" + nombre);
		 bebidas= cargador.cargarBebida( archivoP);
		return bebidas;
	}
	
	
	
	public HashMap<String,Cama> cargarCamas (String nombre) throws IOException {
		File archivoP= new File ("./data/" + nombre);
		camas= cargador.cargarCamas( archivoP);
		return camas;
	}
	
	public HashMap<String,Servicio> cargarServicios (String nombre) throws IOException {
		File archivoP= new File ("./data/" + nombre);
		servicios= cargador.cargarServicios( archivoP);
		return servicios;
	}
	
	
	public HashMap<String,Habitacion> cargarHabitaciones (String nombre) throws IOException {
		File archivoP= new File ("./data/" + nombre);
		habitacionies= cargador.cargarHabitacion( archivoP);
		
		return habitacionies;
	}
	public HashMap<String, ArrayList<Habitacion>>getHabitacionesID(){
		habitacionesPorTipo=cargador.getHabitacionesID();
		return habitacionesPorTipo;
	}
	
	
}
