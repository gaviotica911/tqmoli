package Consola;
import Logica.Bebida;
import Logica.CargardorArchivo;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import Logica.Reserva;
import Logica.Servicio;
import Logica.FuncionesEmpleado;
import Logica.Habitacion;
import Logica.HuespedReserva;
import Logica.Plato;


public class AplicacionCliente {
	 public HashMap<String, HuespedReserva> huespedes = new  HashMap<String, HuespedReserva>();
	 public CargardorArchivo catalogo= new CargardorArchivo ();
	 public CargardorArchivo cargador= new CargardorArchivo(); 
	 
	 
	// public HashMap<HuespedReserva, Reserva> reservas = new  HashMap<HuespedReserva, Reserva>();
	
	public void ejecutarAplicacion() throws IOException
	{
		
		
		
		System.out.println("Bienvenido querido Usuario. \n");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					{ingresarDatos();}
				else if (opcion_seleccionada == 2)
					{reservar();}
				else if (opcion_seleccionada == 3 )
				{mostrarCatalogo();}
				
				else if (opcion_seleccionada == 4 )
				{mostrarMenuRestaurante();
					
				}
				else if (opcion_seleccionada == 5 )
				{
					
				}
				
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Saliendo de la aplicación ...");
					 AplicacionPrincipal appp= new AplicacionPrincipal();
					appp.ejecutarAplicacion();
				}
				
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
	
	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("\nPara reservar necesitas primero ingresar tus datos\n");
		System.out.println("1. Ingresar Datos");
		System.out.println("2. Realizar una reserva");
		System.out.println("3. Solicitar catalogo de servicios");
		System.out.println("4. Mostrar el menu del Restaurante");
		System.out.println("5. Solicitar servicio");
		System.out.println("6. CerrarSesion");
		
	}
	public void ingresarDatos(){
		{
			 System.out.println("Ingresa sus datos: ");
			  String nombre=input("Ingrese su nombre completo: "); 
			  int documento= Integer.parseInt(input("Ingrese su numero de documento, sin punto ni comas ni guiones ni espacios: "));
			  String correo= input("Ingrese su correo electronico: ");
			  String celular= input("Ingrese su numero celular: ");
			  HuespedReserva huesped1= huespedes.get(nombre);
				if (huesped1 == null)
				{
					 huesped1 = new HuespedReserva(nombre, documento, correo, celular);
					huespedes.put(nombre,huesped1);
				}
				else {
					System.out.println("Ya estasregistrado :)");
				}
			  
			  
		}
	}
	
	
	public void solicitarServicio()
	{
		
	}
	public void mostrarCatalogo() {
		System.out.println("Los servicios dispoibles son: ");
		;
		HashMap<String,Servicio> servicios=cargador.getServicios();
		int cont=1;
		for(String i: servicios.keySet() ) {
			Servicio c = servicios.get(i);
		//nombre;ubicacion;precio;diasDisponible;horarioDisponible

        	System.out.println(cont+ ". " + c.getNombre() +  ", precio: "+c.getPrecioTotal()+", tiempo: "+ c.getHorariosDeDisponibilidad() + ", ubicacion: " + c.getUbicacion());
		}
	}
	public void mostrarMenuRestaurante(){
		System.out.println("BEBIDAS");
		HashMap<String,Bebida> bebidas= cargador.getBebidas();
		int cont=1;
		for(String i: bebidas.keySet() ) {
			Bebida c = bebidas.get(i);
			
        	System.out.println(cont+". " + c.getNombre() +  ", precio: "+c.getPrecioTotal()+", tiempo: "+ c.getHorariosDeDisponibilidad() + ", habitación: "+ c.isLlevable());
        }
        System.out.println("\n");
        
        System.out.println("PLATOS");
		HashMap<String,Plato> platos= cargador.getPlatos();
		cont=1;
		for(String i: platos.keySet() ) {
			Plato p = platos.get(i);
		//producto;precio;tiempo;habitación

        	System.out.println(cont+". " +  p.getNombre() +  ", precio: "+p.getPrecioTotal()+", tiempo: "+ p.getHorariosDeDisponibilidad() + ", habitación: "+ p.isLlevable());
        }
        System.out.println("\n");
        
        
        
		
		
		
	}
	public void reservar() throws IOException
	{
	
	  String nombre=input("Ingrese su nombre completo: "); 
	  HashMap<String, ArrayList<Habitacion>> habitacionies= catalogo.getHabitacionesID();
	  int cantidadDeAcompañantes= Integer.parseInt(input("Ingrese la cantidad de personas que van al viaje (incluyendolo a usted)"));
	  Date Fecha_llegada= formatearHora(input("Ingrese la fecha de llegada en el formato dd/MM/yy: "), "dd/MM/yy");
	  Date Fecha_salida= formatearHora(input("Ingrese la fecha de Salida en el formato dd/MM/yy: "), "dd/MM/yy");
	  HuespedReserva huesped1= huespedes.get(nombre);
		if (huesped1 == null)
		{
			System.out.println("Necesitas ingresar tus datos primero.");
			 
		}
		else {
			FuncionesEmpleado empleado= new FuncionesEmpleado();
			File archivoHabitaciones= new File ("./data/habitacion.txt");
			catalogo.cargarHabitacion(archivoHabitaciones);
			ArrayList<String> xd=empleado.reserva(huesped1, Fecha_llegada,  Fecha_salida, cantidadDeAcompañantes, habitacionies);
			System.out.println(xd);
			
			
		}
		
	
			
	  
	
	}

	// TODO EL RESTO DEL MENUUUU!!!!!!!!!!!!!!!
	
	
	public static void main(String[] args) throws IOException
	{
		AplicacionCliente consola = new AplicacionCliente();
		consola.ejecutarAplicacion();
	}
	
	public String input(String mensaje)
	{
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
//dios mio por favor que sirva , desde visual 