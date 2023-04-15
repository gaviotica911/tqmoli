package Consola;

import Logica.CargardorArchivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import Logica.Bebida;
import Logica.Cama;
import Logica.Habitacion;
import Logica.Plato;
import Logica.Servicio;
import Logica.Tarifa;

public class MenuAdmin {
	static CargardorArchivo Admin= new CargardorArchivo();
	

	
	
	public void ejecutarAplicacion() throws IOException {
		System.out.println("Bienvenido Administrador de Hotel de los Andes. \n");
		
		boolean continuar = true;
		while (continuar) 
		{
			try 
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					{ejecutarCargarCamas();}
				else if (opcion_seleccionada == 2)
					{ejecutarCargarHabitaciones();}
				else if (opcion_seleccionada == 3)
					{ejecutarCargarBebidas();}
				else if (opcion_seleccionada == 4)
					{ejecutarCargarPlatos();}
				else if (opcion_seleccionada == 5)
					{ejecutarCargarServicios();}
				else if (opcion_seleccionada == 6)
					{ejecutarCargarTarifas();}
				
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			
			
			
			
	            
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		
		
		
				
		
			
			
		}
		




		
	}


	public void ejecutarCargarTarifas() throws IOException {
		
		
		String archivo = input ("Ingrese el nombre del archivo en formato .txt: ");
		
		
		File archivoT= new File ("./data/" + archivo);
        ArrayList<Object> tarifas= Admin.cargarTarifas();
    	//0 estandar, 1 suite, 2 doble, 3 tarifapor fecha
        
        HashMap<Date, String> diasAño= Admin.cargarDiasAño();
       
         
         Object principal= tarifas.get(0);
         Object tarifaEstandar= tarifas.get(1);
         Object tarifaSuite= tarifas.get(2);
         Object tarifaSuiteDoble= tarifas.get(3);
         //System.out.println(tarifaEstandar);
         System.out.println("--------TARIFA ESTANDAR--------");
         for(Date j : ((HashMap<Date, Float>) tarifaEstandar).keySet()) {
 			String fecha=fechaString(j);
 			System.out.println(fecha + ": "+ (( (HashMap<String, Cama>) tarifaEstandar).get(j))); 
 			
 		}
         System.out.println("--------TARIFA SUITE--------");
         for(Date j : ((HashMap<Date, Float>) tarifaSuite).keySet()) {
  			String fecha=fechaString(j);
  			System.out.println(fecha + ": "+ (( (HashMap<String, Cama>) tarifaSuite).get(j))); 
  			
  		}
         System.out.println("--------TARIFA SUITE DOBLE--------");
         for(Date j : ((HashMap<Date, Float>) tarifaSuiteDoble).keySet()) {
  			String fecha=fechaString(j);
  			System.out.println(fecha + ": "+ (( (HashMap<String, Cama>) tarifaSuiteDoble).get(j))); 
  			
  		}
		
	}




	public static String fechaString(Date date) {
		SimpleDateFormat dateFormatter=null;
        
        dateFormatter = new SimpleDateFormat("dd/MM");
       
        return dateFormatter.format(date);
	}


	public void ejecutarCargarServicios() throws IOException {
		
		String archivo = input ("Ingrese el nombre del archivo en formato .txt: ");
		
		
		File archivoS= new File ("./data/" + archivo);
		HashMap<String,Servicio> servicios= Admin.cargarServicios( archivoS);
		for(String i: servicios.keySet() ) {
			Servicio c = servicios.get(i);
		//nombre;ubicacion;precio;diasDisponible;horarioDisponible

        	System.out.println("Producto: " + c.getNombre() + ", Ubicacion: " + c.getUbicacion() + ", Precio: "+c.getPrecioTotal()+", Tiempo: "+ c.getHorariosDeDisponibilidad()+" ,Dias de Disponibilidad: ");
        	
            for(String d:c.getDias() ) {
            	System.out.println(d +  ",");
            }
            System.out.println("\n");
		}

		
	}




	public void ejecutarCargarPlatos() throws IOException {
		String archivo = input ("Ingrese el nombre del archivo en formato .txt: ");
		
		
		File archivoP= new File ("./data/" + archivo);
		HashMap<String,Plato> platos= Admin.cargarPlato( archivoP);
		for(String i: platos.keySet() ) {
			Plato c = platos.get(i);
		//producto;precio;tiempo;habitación

        	System.out.println("Producto: " + c.getNombre() +  ", Precio: "+c.getPrecioTotal()+", Tiempo: "+ c.getHorariosDeDisponibilidad() + ", Habitación: "+ c.isLlevable());
        }
		
	}








	public void ejecutarCargarBebidas() throws IOException {
		
		String archivo = input ("Ingrese el nombre del archivo en formato .txt: ");
		
		
		File archivoB= new File ("./data/" + archivo);
		HashMap<String,Bebida> bebidas= Admin.cargarBebida( archivoB);
		for(String i: bebidas.keySet() ) {
			Bebida c = bebidas.get(i);
		//producto;precio;tiempo;habitación

        	System.out.println("Producto: " + c.getNombre() +  ", Precio: "+c.getPrecioTotal()+", Tiempo: "+ c.getHorariosDeDisponibilidad() + ", Habitación: "+ c.isLlevable());
        }
		
	}




	public void ejecutarCargarHabitaciones() throws IOException {
		
		String archivo = input ("Ingrese el nombre del archivo en formato .txt: ");
		
		
		File archivoHabitaciones= new File ("./data/" + archivo);
		
		HashMap<String, Habitacion> cuartos= Admin.cargarHabitacion ( archivoHabitaciones);
		System.out.println("Cuartos: \n");
		for(String i : cuartos.keySet()) 
		{
			
			Habitacion hab = cuartos.get(i);
            System.out.println("iD: " + i+ ", Capacidad: "+hab.getCapacidad() + ", Ubicacion : " + hab.getUbicacion() + ", Precio: "+hab.getPrecioFijo()+ "Camas: ");
            ArrayList<Cama> camasHab= hab.getCama();
            for(Cama c:camasHab ) {
            	System.out.println(c.tamaño +  ".");
            }
            System.out.println("\n");
            
        
 		
 		}
		
	}




	public void ejecutarCargarCamas() throws IOException {
		String archivo = input ("Ingrese el nombre del archivo en formato .txt: ");
		
		
		File archivoCamas= new File ("./data/" + archivo);
		
		HashMap<String,Cama> camas= Admin.cargarCamas ( archivoCamas);
		for(String i: camas.keySet() ) {
			Cama c = camas.get(i);
		
        	System.out.println("Tamaño: " + c.tamaño +  ", Uso: "+c.getUso()+", #Niños: "+ c.getNumeroNiños());
        }
        System.out.println("\n");
		
		
	}




	public String input(String mensaje) {
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
		
		
	}




	public void mostrarMenu() {
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Cargar Camas ");
		System.out.println("2. Cargar Habitaciones");
		System.out.println("3. Cargar Bebidas");
		System.out.println("4. Cargar Platos ");
		System.out.println("5. Cargar Servicios");
		System.out.println("6. Cargar Tarifas");
		
		
		
	}
	
}
