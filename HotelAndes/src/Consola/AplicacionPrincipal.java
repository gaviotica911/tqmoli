package Consola;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class  AplicacionPrincipal {
	Hotel hotel= new Hotel();
	
	
	 
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
					{ejecutarIniciarSesion();}
				else if (opcion_seleccionada == 2)
					{ejecutarCrearUsuario();}
				else if (opcion_seleccionada == 3 )
				{//TODO
					}
				
				else if (opcion_seleccionada == 4 )
				{//TODO
					
				}
				else if (opcion_seleccionada == 5 )
				{//TODO
					
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
		System.out.println("1. Iniciar Sesión");
		System.out.println("2. Crear Usuario");
		
		
	}
	
	
	
	public void ejecutarIniciarSesion() throws IOException {
		String usuario= input("Ingrese su usuario: ");
		String contraseña= input("Ingrese su contraseña: ");
		
		System.out.println(hotel.iniciarSesion(usuario,contraseña));
		
		
	}
	
	public void ejecutarCrearUsuario() {
		String usuario= input("Ingrese un nombre de usuario: ");
		String contraseña= input("Ingrese una contraseña: ");
		ArrayList<String> us= new ArrayList<String>();
		hotel.crearUsuario(usuario, contraseña);
		
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
	
	public static void main(String[] args) throws IOException
	{
		AplicacionPrincipal app= new AplicacionPrincipal();
		Hotel hotel= new Hotel();
		hotel.cargarUsuarios();
		app.ejecutarAplicacion();
	}
	
	
}
