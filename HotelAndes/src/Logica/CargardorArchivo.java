package Logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;



public class CargardorArchivo {
	public HashMap<String,Cama> camas= new HashMap<String,Cama>();
	public HashMap<String, Habitacion> habitacionies= new  HashMap<String, Habitacion>();
	public HashMap<String, ArrayList<Habitacion>> habitacionesPorTipo= new HashMap <String, ArrayList<Habitacion>>();
	public HashMap<Date, ArrayList<Tarifa>> tarifas= new  HashMap<Date, ArrayList<Tarifa>>();
	public HashMap<Date, String> diasAño= new HashMap<Date, String>();
	public HashMap<Date, Float> tarifaEstandar= new HashMap<Date, Float>();
	public HashMap<Date, Float> tarifaSuite= new HashMap<Date, Float>();
	public HashMap<Date, Float> tarifaSuiteDoble= new HashMap<Date, Float>();
	public HashMap<String, Plato> platos= new  HashMap<String, Plato>();
	public ArrayList <HashMap<String, ArrayList<Plato>>> PlatoporNom= new ArrayList <HashMap<String, ArrayList<Plato>>>();
	public HashMap<String, Bebida> bebidas= new  HashMap<String, Bebida>();
	public ArrayList <HashMap<String, ArrayList<Bebida>>> BebidaporNom= new ArrayList <HashMap<String, ArrayList<Bebida>>>();
	public HashMap<String, Servicio> servicios= new  HashMap<String, Servicio>();
	
	
	
	
	/*
	 +habitaciones: ArrayList <HashMap<String(ID) , List<Habitaciones>>>
	+precioSpa: int
	+precioGuia: int
	+tarifas: ArrayList<HasMap<String(dia del año), float (tarifa)>>
	+menuRestaurante: ArrayList<HashMap>
 */
	
	
	
	
	
	public HashMap<String, Cama> getCamas() {
		return camas;
	}





	public HashMap<String, ArrayList<Habitacion>> getHabitacionesPorTipo() {
		return habitacionesPorTipo;
	}





	public HashMap<Date, Float> getTarifaEstandar() {
		return tarifaEstandar;
	}





	public HashMap<Date, Float> getTarifaSuite() {
		return tarifaSuite;
	}





	public HashMap<Date, Float> getTarifaSuiteDoble() {
		return tarifaSuiteDoble;
	}





	public HashMap<String, Plato> getPlatos() {
		return platos;
	}





	public HashMap<String, Bebida> getBebidas() {
		return bebidas;
	}





	public HashMap<String, Bebida> cargarBebida (File archivoBebidas) throws IOException
	{
		
		FileReader archivo= new FileReader(archivoBebidas);
		BufferedReader br = new BufferedReader(archivo);
		String linea = br.readLine();
		String[] titulos = linea.split(";");
		linea = br.readLine();
		
		
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			//producto, precio, tiempo, Shabitacion
			
			
			String[] partes = linea.split(";");
			
			String producto = partes[0];
			float precio = Float.parseFloat(partes[1]);
			String tiempo = partes[2];
			boolean llevable = toBool(partes[3]);
			Bebida laBebida = bebidas.get(producto);
			if (laBebida == null)
			{
				laBebida= new Bebida( producto, precio, tiempo,llevable);
				bebidas.put(producto,laBebida);
				//ArrayList <HashMap<String, ArrayList<Bebida>>> Mirar si organizar eso por esta estructura
				

			}
			linea = br.readLine(); // Leer la siguiente línea
		}

		br.close();
		return bebidas;
			
		}
	
	
	
	
	
	public HashMap<String, Plato> cargarPlato (File archivoPlatos) throws IOException
	{
		
		FileReader archivo= new FileReader(archivoPlatos);
		BufferedReader br = new BufferedReader(archivo);
		String linea = br.readLine();
		String[] titulos = linea.split(";");
		linea = br.readLine();
		
		
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			//producto, precio, tiempo, Shabitacion
			
			
			String[] partes = linea.split(";");
			
			String producto = partes[0];
			float precio = Float.parseFloat(partes[1]);
			String tiempo = partes[2];
			boolean llevable = toBool(partes[3]);

			Plato elPlato = platos.get(producto);
			if (elPlato == null)
			{
				elPlato= new Plato( producto, precio, tiempo, llevable);
				platos.put(producto, elPlato);
				//ArrayList <HashMap<String, ArrayList<Plato>>> Mirar si organizar eso por esta estructura
				

			}
			linea = br.readLine(); // Leer la siguiente línea
		}

		br.close();
		return platos;
			
		}
	
	public HashMap<Date, String> cargarDiasAño () throws IOException
	{
		File archivodiasAño= new File ("./data/diasAño.txt");
		FileReader archivo= new FileReader(archivodiasAño);
		BufferedReader br = new BufferedReader(archivo);
		String linea = br.readLine();
	
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			//fechaInical;fechaFinal;dia;tipo de habitacion;extra  01-01-23;07-01-23
			String[] partes = linea.split(";");
			Date fecha = formatearFecha(partes[0], "dd/MM/yy");
			String  dia=partes[1];
			
			diasAño.put(fecha, dia);
			
			linea = br.readLine(); // Leer la siguiente línea
		}

		br.close();
		
		return diasAño;
	}
	
	
	
	public HashMap<String, Habitacion> getHabitacionies() {
		return habitacionies;
	}



	public HashMap<String,Cama> cargarCamas (File archivoCamas) throws IOException
	{
		
		FileReader archivo= new FileReader(archivoCamas);
		BufferedReader br = new BufferedReader(archivo);
		String linea = br.readLine();
		String[] titulos = linea.split(";");
		linea = br.readLine();
		
		
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			
			String[] partes = linea.split(";");
			String tamaño = partes[0];
			String uso=partes[1];
			int numeroNiños=Integer.parseInt(partes[2]);
			int numeroAdultos=Integer.parseInt(partes[3]);
			Cama laCama= camas.get(tamaño);
			if (laCama == null)
			{
				laCama= new Cama (tamaño,uso, numeroNiños,numeroAdultos);
				camas.put(tamaño,laCama);
			}
			linea = br.readLine(); // Leer la siguiente línea
		}

		br.close();
		return camas;
	}
	public HashMap<String, Habitacion> cargarHabitacion (File archivoHabitaciones) throws IOException
	{
		ArrayList<Habitacion> estandar= new ArrayList<Habitacion>();
		ArrayList<Habitacion> suite= new ArrayList<Habitacion>();
		ArrayList<Habitacion> suiteDoble= new ArrayList<Habitacion>();
		
		FileReader archivo= new FileReader(archivoHabitaciones);
		BufferedReader br = new BufferedReader(archivo);
		String linea = br.readLine();
		String[] titulos = linea.split(";");
		linea = br.readLine();
		
		
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			//id;ubicacion;capacidad;camas;precioFijo;vista;balcon;cocina;tipo
			
			
			String[] partes = linea.split(";");
			
			String id = partes[0];
			String ubicacion=partes[1];
			int capacidad=Integer.parseInt(partes[2]);
			String[] lasCamas = partes[3].split(",");
			ArrayList<Cama> camasCuarto= new ArrayList<Cama>();
			for(String i: lasCamas)
			{
				Cama laCama= camas.get(i);
				if(laCama !=null) {
				camasCuarto.add(laCama);
				}
			}
					
			float precioFijo = Float.parseFloat(partes[4]);
			boolean vista = toBool(partes[5]);
			boolean balcon= toBool(partes[6]);
			boolean cocina= toBool(partes[7]);
			String tipo=partes[8];

			Habitacion laHabitacion= habitacionies.get(id);
			if (laHabitacion == null)
			{
				laHabitacion= new Habitacion( id,  capacidad, camasCuarto,  ubicacion,  tipo,  precioFijo, vista,  balcon,  cocina);
				habitacionies.put(id, laHabitacion);
	
				if(tipo=="estandar") {estandar.add(laHabitacion);}
				if(tipo=="suite") {suite.add(laHabitacion);}
				if(tipo=="suiteDoble") {suiteDoble.add(laHabitacion);}
				
				//ArrayList <HashMap<String, ArrayList<Habitacion>>> Mirar si organizar eso por esta estructura
				

			}
			linea = br.readLine(); // Leer la siguiente línea
		}
		habitacionesPorTipo.put("estandar", estandar);
		habitacionesPorTipo.put("suite", suite);
		habitacionesPorTipo.put("suiteDoble", suiteDoble);
		

		br.close();
		return habitacionies;
			
		}
	
	public HashMap<String, ArrayList<Habitacion>>getHabitacionesID(){
		return habitacionesPorTipo;
	}
	
	public ArrayList<Object> cargarTarifas () throws IOException
	{
		diasAño=cargarDiasAño();
		File archivoTarifas= new File ("./data/tarifas.txt");
		FileReader archivo= new FileReader(archivoTarifas);
		BufferedReader br = new BufferedReader(archivo);
		String linea = br.readLine();
		String[] titulos = linea.split(";");
		linea = br.readLine();
		
		
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			
			//HashMap<String, HashMap<Date,>>
			//fechaInicial;dia;tipo de habitacion;extra
			String[] partes = linea.split(";");
			Date fecha = formatearFecha(partes[0], "dd/MM/yy");
			String[] dias = partes[1].split(",");
			String tipoHabitación= partes[2];
			float extra= Integer.parseInt(partes[3]);
			String diasemanaTarifa= diasAño.get(fecha);
			
				if(tipoHabitación.equals("estandar"))
				{
					
					if(Arrays.asList(dias).contains(diasemanaTarifa)) {
						tarifaEstandar.put(fecha, extra);}
					else {tarifaEstandar.put(fecha, 0f);}
					
					
				}
				else if (tipoHabitación.equals("suite"))
				{
					
					if(Arrays.asList(dias).contains(diasemanaTarifa)) {
							tarifaSuite.put(fecha, extra);}
					else{tarifaSuite.put(fecha, 0f);}
					
				}
				else if (tipoHabitación.equals("suite doble"))
				{
					
					if(Arrays.asList(dias).contains(diasemanaTarifa)) {
							tarifaSuiteDoble.put(fecha, extra);}
					else{tarifaSuiteDoble.put(fecha, 0f);}
					
				}
			
			Tarifa laTarifa= new Tarifa (tipoHabitación,extra,fecha,dias);
		
			if ((tarifas.keySet()).contains(fecha))
			{
				ArrayList<Tarifa> listaTarifas = tarifas.get(fecha);
				listaTarifas.add(laTarifa);
				
			}
			else {
				ArrayList<Tarifa> listaTarifas= new ArrayList<Tarifa>();
				listaTarifas.add(laTarifa);
				tarifas.put(fecha, listaTarifas);
			}
			linea = br.readLine(); // Leer la siguiente línea
		}

		br.close();
		
		ArrayList<Object> listaFinal = new ArrayList<Object>();
		//0 estandar, 1 suite, 2 doble, 3 tarifapor fecha
		listaFinal.add(tarifaEstandar);
		listaFinal.add(tarifaSuite);
		listaFinal.add(tarifaSuiteDoble);
		listaFinal.add(tarifas);
		
		
		return listaFinal;
	}
	
	public HashMap<String, Servicio> cargarServicios (File archivoServicios) throws IOException
	{
		
		FileReader archivo= new FileReader(archivoServicios);
		BufferedReader br = new BufferedReader(archivo);
		String linea = br.readLine();
		String[] titulos = linea.split(";");
		linea = br.readLine();
		
		
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			//nombre;ubicacion;precio;diasDisponible;horarioDisponible
			
			
			String[] partes = linea.split(";");
			
			String nombre = partes[0];
			String ubicacion=partes[1];
			float precio=Integer.parseInt(partes[2]);
			String[] diasDisponible = partes[3].split(",");
			String horarioDisponible=partes[4];
			Servicio elServicio= servicios.get(nombre);
			if (elServicio == null)
			{
				elServicio= new ServicioRecreativo( nombre,  ubicacion, precio,  diasDisponible,  horarioDisponible);
				servicios.put(nombre, elServicio);
	
				

			}
			linea = br.readLine(); // Leer la siguiente línea
		}
		
		

		br.close();
		return servicios;
			
		}
	
	
	
	
	
	
	
	public boolean toBool ( String variable )
	{
		boolean bool=false;
		if (variable.equals("true"))
				{
			bool= true;
				}
		else if(variable.equals("false"))
				{
			bool= false;
				}
		return bool;
	}
	
	public Date formatearFecha(String date_time, String formato) {
		 
	        SimpleDateFormat dateParser = new SimpleDateFormat(formato);
	        Date date= null ;
           
	        {
	            try {
	                 date = dateParser.parse(date_time);
	                
	            } catch (ParseException e) {
	                e.printStackTrace();
	            }
	        }
	        return date;
	}
	public Date formatearHora(String date_time, String formato) {
		 
        SimpleDateFormat dateParser = new SimpleDateFormat(formato);
        Date date= null ;
       
        {
            try {
                 date = dateParser.parse(date_time);
                
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
}
	

}