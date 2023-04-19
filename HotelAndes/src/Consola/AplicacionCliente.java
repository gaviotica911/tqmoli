package Consola;

import Logica.Bebida;
import Logica.CargardorArchivo;
import Logica.Consumo;
import Logica.Factura;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import Logica.Reserva;
import Logica.Servicio;
import Logica.FuncionesEmpleado;
import Logica.Habitacion;
import Logica.HuespedReserva;
import Logica.Inventario;
import Logica.Plato;

public class AplicacionCliente {
	public HashMap<String, HuespedReserva> huespedes = new HashMap<String, HuespedReserva>();
	public HashMap<String, Reserva> reservas = new HashMap<String, Reserva>();
	public CargardorArchivo catalogo = new CargardorArchivo();
	public CargardorArchivo cargador = new CargardorArchivo();

	public ArrayList<String> usuariosCheckIn = new ArrayList<String>();
	FuncionesEmpleado empleado = new FuncionesEmpleado();
	Inventario inventarioInstancia = new Inventario();
	HashMap<String, ArrayList<Date>> inventario = inventarioInstancia.getInventario();

	// public HashMap<HuespedReserva, Reserva> reservas = new
	// HashMap<HuespedReserva, Reserva>();

	public void ejecutarAplicacion() throws IOException {

		System.out.println("Bienvenido querido Usuario. \n");

		boolean continuar = true;
		while (continuar) {
			try {
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));

				if (opcion_seleccionada == 1) {
					ingresarDatos();
				} else if (opcion_seleccionada == 2) {
					reservar();
				} else if (opcion_seleccionada == 3) {
					checkIn();
				} 
				else if (opcion_seleccionada == 4) {
					mostrarCatalogo();
				}

				else if (opcion_seleccionada == 5) {
					mostrarMenuRestaurante();

				} else if (opcion_seleccionada == 6) {
					{
						ejecutarSolicitarServicio();
					}
				} else if (opcion_seleccionada == 7) {
					checkOut();
				} else if (opcion_seleccionada == 8) {
					{
						ejecutarPagarUnProducto();
					}
				} else if (opcion_seleccionada == 9) {
					cancelarReserva();
				}

				else if (opcion_seleccionada == 10) {

					System.out.println("Saliendo de la aplicación ...");
					AplicacionPrincipal appp = new AplicacionPrincipal();
					appp.ejecutarAplicacion();
				}

				else {
					System.out.println("Por favor seleccione una opción válida.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}

	public void mostrarMenu() {
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("\nPara reservar necesitas primero ingresar tus datos\n");
		System.out.println("1. Ingresar Datos");
		System.out.println("2. Realizar una reserva");
		System.out.println("3. Realizar una check-in");
		System.out.println("4. Solicitar catalogo de servicios (guia turistico y spa)");
		System.out.println("5. Mostrar el menu del Restaurante");
		System.out.println("6. Solicitar servicio (Plato, bebida o servicio)");
		System.out.println("7. Realizar una check-out");
		System.out.println("8. Pagar Servicio");
		System.out.println("9. Cancelar reserva");
		System.out.println("10. Cerrar Sesion");

	}

	public void ejecutarPagarUnProducto() {
		String nombreH = input("Ingrese su nombre");

		if (huespedes.get(nombreH) == null) {
			System.out.println("No ingreso el nombre de un huesped");
		} else {
			HashMap<String, Consumo> consumosH = (huespedes.get(nombreH)).getConsumos();

			int cont = 1;
			for (String i : consumosH.keySet()) {
				Consumo c = consumosH.get(i);

				System.out.println(cont + ". " + c.getNombre() + ", precio: " + c.getPrecio() + ", impuesto: "
						+ c.getImpuestos() + ", pagado: " + c.getPagado());
			}
			int producto = Integer.parseInt(input("Ingrese el numero del producto que desea pagar"));
			List<Consumo> listaConsumo = new ArrayList<Consumo>(consumosH.values());
			(listaConsumo.get(producto)).setPagado(true);
			System.out.println("Se realizo el pago con exito");
		}

	}

	public void checkOut() {
		// TODO hacer todo el checjout

		// aca cuando hace el check out le doy la factura
		String nombre = input("Ingrese su nombre");

		if (huespedes.get(nombre) == null) {
			System.out.println("No ingreso el nombre de un huesped");
		} else {
			ArrayList<Consumo> consumosLiii = new ArrayList<Consumo>(((huespedes.get(nombre)).getConsumos()).values());

			Factura f = new Factura(LocalDate.now(), nombre, (huespedes.get(nombre)), consumosLiii);
		}

	}

	public void checkIn() {

		String nombre = input("Ingrese su nombre");
		if (huespedes.get(nombre) == null) {
			System.out.println("No ingreso el nombre de un huesped");
		} else {

			Reserva reserva = reservas.get(nombre);
			int cantidad = reserva.getCantidadDeAcompañantes();
			HashMap<String, String> huespedes = new HashMap<String, String>();

			for (int i = 0; i < cantidad; i++) {
				String nombre1 = input("Por favor ingrese el nombre del huesped" + i);
				String id = input("Ingrese su cédula por favor:" + i);
				huespedes.put(nombre1, id);
			}
			reserva.setEstado(true);

			System.out.println("Registro exisoto");
		}
	}

	public void ejecutarSolicitarServicio() {
		HashMap<String, Object> rta = new HashMap<String, Object>();
		String nombre = input("Ingrese su nombre: ");
		if (usuariosCheckIn.contains(nombre) == false) {
			System.out.println("Tienes que hacer check in antes de solicitar un servicio");
		} else {
			int opcion = Integer.parseInt(
					input("Ingrese 1 si desea ordenar servicios de spa o guia o 2 si desea ordenar del restaurante."));
			switch (opcion) {
				case 2:
					int opcionR = Integer
							.parseInt(input("Ingrese 1 si desea ordenar bebidas o 2 si desea ordenar platos"));
					if (opcionR == 1) {
						int bebida = Integer.parseInt(input("Ingrese el numero de bebida que desea ordenar: "));
						rta.put("bebida", bebida);
					}
					if (opcionR == 2) {
						int plato = Integer.parseInt(input("Ingrese el numero de plato que desea ordenar: "));
						rta.put("plato", plato);
					}
					break;
				case 1:
					int servicio = Integer.parseInt(input("Ingrese el numero de bebida que desea ordenar: "));
					rta.put("servicio", servicio);
					break;
			}
			if (rta != null) {
				rta.put("nombre", nombre);
				rta.put("reserva", reservas.get(nombre));

				empleado.cargarConsumo(rta);

			}
		}

	}

	public void ingresarDatos() {
		{

			System.out.println("Ingresa sus datos: ");
			String nombre = input("Ingrese su nombre completo: ");
			int documento = Integer
					.parseInt(input("Ingrese su numero de documento, sin punto ni comas ni guiones ni espacios: "));
			String correo = input("Ingrese su correo electronico: ");
			String celular = input("Ingrese su numero celular: ");
			HuespedReserva huesped1 = huespedes.get(nombre);
			if (huesped1 == null) {
				HashMap<String, Consumo> consumoss = new HashMap<String, Consumo>();
				huesped1 = new HuespedReserva(nombre, documento, correo, celular, consumoss);
				huespedes.put(nombre, huesped1);
			} else {
				System.out.println("Ya estasregistrado :)");
			}

		}
	}

	public void mostrarCatalogo() {
		System.out.println("Los servicios dispoibles son: ");
		;
		HashMap<String, Servicio> servicios = cargador.getServicios();
		int cont = 1;
		for (String i : servicios.keySet()) {
			Servicio c = servicios.get(i);
			// nombre;ubicacion;precio;diasDisponible;horarioDisponible

			System.out.println(cont + ". " + c.getNombre() + ", precio: " + c.getPrecioTotal() + ", tiempo: "
					+ c.getHorariosDeDisponibilidad() + ", ubicacion: " + c.getUbicacion());
		}
	}

	public void mostrarMenuRestaurante() {
		System.out.println("BEBIDAS");

		HashMap<String, Bebida> bebidas = cargador.getBebidas();
		int cont = 1;
		for (String i : bebidas.keySet()) {

			Bebida c = bebidas.get(i);

			System.out.println(cont + ". " + c.getNombre() + ", precio: " + c.getPrecioTotal() + ", tiempo: "
					+ c.getHorariosDeDisponibilidad() + ", habitación: " + c.isLlevable());
		}
		System.out.println("\n");

		System.out.println("PLATOS");
		HashMap<String, Plato> platos = cargador.getPlatos();
		cont = 1;
		for (String i : platos.keySet()) {
			Plato p = platos.get(i);
			// producto;precio;tiempo;habitación

			System.out.println(cont + ". " + p.getNombre() + ", precio: " + p.getPrecioTotal() + ", tiempo: "
					+ p.getHorariosDeDisponibilidad() + ", habitación: " + p.isLlevable());
		}
		System.out.println("\n");

	}

	public void reservar() throws IOException {

		String nombre = input("Ingrese su nombre completo: ");
		HashMap<String, Habitacion> habitaciones = catalogo.getHabitacionies();
		HashMap<String, ArrayList<Habitacion>> habitacionies = catalogo.getHabitacionesID();
		HashMap<Date, Float> tarifaEstandar = catalogo.getTarifaEstandar();
		HashMap<Date, Float> tarifaSuite = catalogo.getTarifaSuite();
		HashMap<Date, Float> tarifaSuiteDoble = catalogo.getTarifaSuiteDoble();

		int cantidadDeAcompañantes = Integer
				.parseInt(input("Ingrese la cantidad de personas que van al viaje (incluyendolo a usted)"));

		Date Fecha_llegada = formatearHora(input("Ingrese la fecha de llegada en el formato dd/MM/yy: "), "dd/MM/yy");
		Date Fecha_salida = formatearHora(input("Ingrese la fecha de Salida en el formato dd/MM/yy: "), "dd/MM/yy");
		HuespedReserva huesped1 = huespedes.get(nombre);
		if (huesped1 == null) {
			System.out.println("Necesitas ingresar tus datos primero.");

		} else {
			FuncionesEmpleado empleado = new FuncionesEmpleado();
			File archivoHabitaciones = new File("./data/habitacion.txt");
			catalogo.cargarHabitacion(archivoHabitaciones);
			ArrayList<Date> fechas = empleado.fechas(Fecha_llegada, Fecha_salida);
			ArrayList<String> xd = empleado.reserva(huesped1, Fecha_llegada, Fecha_salida, cantidadDeAcompañantes,
					habitacionies);
			HashMap<String, Float> valores = empleado.calcularValoresTotales(xd, habitaciones, tarifaEstandar,
					tarifaSuite, tarifaSuiteDoble, fechas);
			System.out.println(xd);
			System.out.println(valores);
			HuespedReserva huesped = huespedes.get(nombre);
			int numHabitaciones = xd.size();
			float precio_habitaciones = 0;
			for (float precio : valores.values()) {
				precio_habitaciones += precio;
			}

			Reserva reserva = new Reserva(huesped, Fecha_llegada, Fecha_salida, precio_habitaciones,
					cantidadDeAcompañantes, numHabitaciones, 0, false, xd);
			reservas.put(nombre, reserva);

		}

	}

	public void cancelarReserva() throws IOException {
		String nombre = input("Ingresa tu nombre: ");
		FuncionesEmpleado empleado = new FuncionesEmpleado();
		Reserva reserva = reservas.get(nombre);
		ArrayList<String> habitacionesReserva = reserva.getHabitaciones();
		Date Fecha_llegada = reserva.getFecha_llegada();
		Date Fecha_salida = reserva.getFecha_salida();
		String xd = empleado.cancelarReserva(habitacionesReserva, Fecha_llegada, Fecha_salida, inventario);

	}

	
	

	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (IOException e) {
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

	public Date formatearHora(String date_time, String formato) {

		SimpleDateFormat dateParser = new SimpleDateFormat(formato);
		Date date = null;

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
// dios mio por favor que sirva , desde visual