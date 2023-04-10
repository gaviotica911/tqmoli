package Consola;

import Logica.CargardorArchivo;
import java.io.File;
import java.io.IOException;
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
import Logica.Servicio;
import Logica.Tarifa;

public class MenuAdmin {
	static CargardorArchivo Admin= new CargardorArchivo();
	

	
	
	public void ejecutarAplicacion() {
		System.out.println("Bienvenido Administrador de Hotel de los Andes. \n");
		
		boolean continuar = true;
		while (continuar) 
		{
			try 
			{
			mostrarMenu();
			int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
			if (opcion_seleccionada == 1)
				
				File archivoCama = new File ("./data/camas.txt");
				HashMap<String,Cama> camas= Admin.cargarCamas ( archivoCamas);
				for(String i: camas.keySet() ) {
				Cama c = camas.get(i);
			
	        		System.out.println("Tamaño: " + c.tamaño +  ", uso: "+c.getUso()+", numNiños: "+ c.getNumeroNiños());
				}
				System.out.println("\n");
			
	            
	        
	 		
	 		
			
			
			}
		}
		
		
		
				
		
		}




	private String input(String string) {
		// TODO Auto-generated method stub
		return null;
	}




	private void mostrarMenu() {
		// TODO Auto-generated method stub
		
	}
	
}
