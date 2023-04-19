package Logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import java.util.List;

import java.util.Set;

public class FuncionesEmpleado {
	Inventario inventarioInstancia = new Inventario();
	HashMap<String, ArrayList<Date>> inventario = inventarioInstancia.getInventario();
	public CargardorArchivo cargador = new CargardorArchivo();
	public HashMap<String, Bebida> bebidas = cargador.getBebidas();
	public HashMap<String, Plato> platos = cargador.getPlatos();
	public HashMap<String, Servicio> servicios = cargador.getServicios();
	List<Servicio> listaPlatos = new ArrayList<Servicio>(platos.values());
	List<Servicio> listaBebidas = new ArrayList<Servicio>(bebidas.values());
	List<Servicio> listaServicio = new ArrayList<Servicio>(servicios.values());

	public ArrayList<String> reserva(HuespedReserva huesped1, Date Fecha_llegada, Date Fecha_salida,
			int cantidadDeAcompañantes, HashMap<String, ArrayList<Habitacion>> catalogo) {

		HashMap<String, Integer> habitacionestipo = new HashMap<>();
		habitacionestipo.put("estandar", 0);
		habitacionestipo.put("suite", 0);
		habitacionestipo.put("suiteDoble", 0);
		while (cantidadDeAcompañantes > 0) {
			if (cantidadDeAcompañantes >= 4) { // suiteDoble
				habitacionestipo.put("suiteDoble", habitacionestipo.get("suiteDoble").intValue() + 1);
				cantidadDeAcompañantes -= 4;
			} else if (cantidadDeAcompañantes >= 3) { // suite
				habitacionestipo.put("suite", habitacionestipo.get("suite").intValue() + 1);
				cantidadDeAcompañantes -= 3;
			} else {// estandar
				habitacionestipo.put("estandar", habitacionestipo.get("estandar").intValue() + 1);
				cantidadDeAcompañantes -= 2;
			}
		}

		ArrayList<Date> fechasEntreEntradaYSalida = fechas(Fecha_llegada, Fecha_salida);

		// ArrayList<Date> diasOcupados= inventario.get(IDcuarto); hola mundo

		ArrayList<String> idsReservados = new ArrayList<String>();

		for (int i = 0; i < fechasEntreEntradaYSalida.size(); i++) {
			Date fecha = fechasEntreEntradaYSalida.get(i);

			if (habitacionestipo.get("estandar") != 0) {
				int cantidad = habitacionestipo.get("estandar");

				ArrayList<Habitacion> habitaciones = catalogo.get("estandar");
				ArrayList<String> idsDisponibles = new ArrayList<String>();

				for (Habitacion habitacion : habitaciones) {
					if (!inventario.containsKey(habitacion.getId())) {
						inventario.put(habitacion.getId(), new ArrayList<Date>());
					}
					ArrayList<Date> fechasOcupadas = inventario.get(habitacion.getId());
					if (!fechasOcupadas.contains(fecha)) {
						idsDisponibles.add(habitacion.getId());
					}
				}

				if (idsDisponibles.size() < cantidad) {
					// No hay suficientes habitaciones disponibles para esta fecha y tipo
					return new ArrayList<String>();
				}

				// Reservar habitaciones disponibles
				for (int k = 0; k < cantidad; k++) {

					String id = idsDisponibles.get(k);
					idsReservados.add(id);
					inventario.get(id).add(fecha);
				}
			}

			if (habitacionestipo.get("suite") != 0) {
				int cantidad = habitacionestipo.get("suite");

				ArrayList<Habitacion> habitaciones = catalogo.get("suite");
				ArrayList<String> idsDisponibles = new ArrayList<String>();

				for (Habitacion habitacion : habitaciones) {
					if (!inventario.containsKey(habitacion.getId())) {
						inventario.put(habitacion.getId(), new ArrayList<Date>());
					}
					ArrayList<Date> fechasOcupadas = inventario.get(habitacion.getId());
					if (!fechasOcupadas.contains(fecha)) {
						idsDisponibles.add(habitacion.getId());
					}
				}

				if (idsDisponibles.size() < cantidad) {
					// No hay suficientes habitaciones disponibles para esta fecha y tipo
					return new ArrayList<String>();
				}

				// Reservar habitaciones disponibles
				for (int k = 0; k < cantidad; k++) {

					String id = idsDisponibles.get(k);
					idsReservados.add(id);
					inventario.get(id).add(fecha);
				}
			}

			if (habitacionestipo.get("suiteDoble") != 0) {
				int cantidad = habitacionestipo.get("suiteDoble");

				ArrayList<Habitacion> habitaciones = catalogo.get("suiteDoble");
				ArrayList<String> idsDisponibles = new ArrayList<String>();

				for (Habitacion habitacion : habitaciones) {
					if (!inventario.containsKey(habitacion.getId())) {
						inventario.put(habitacion.getId(), new ArrayList<Date>());
					}
					ArrayList<Date> fechasOcupadas = inventario.get(habitacion.getId());
					if (!fechasOcupadas.contains(fecha)) {
						idsDisponibles.add(habitacion.getId());
					}
				}

				if (idsDisponibles.size() < cantidad) {
					// No hay suficientes habitaciones disponibles para esta fecha y tipo
					return new ArrayList<String>();
				}

				// Reservar habitaciones disponibles
				for (int k = 0; k < cantidad; k++) {

					String id = idsDisponibles.get(k);
					idsReservados.add(id);
					inventario.get(id).add(fecha);
				}
			}
		}

		Set<String> set = new HashSet<>();
		ArrayList<String> listaSinRepetidos = new ArrayList<>();

		for (String elemento : idsReservados) {
			if (!set.contains(elemento)) {
				listaSinRepetidos.add(elemento);
				set.add(elemento);
			}
		}

		return listaSinRepetidos;

	}

	public ArrayList<Date> fechas(Date Fecha_llegada, Date Fecha_salida) {
		ArrayList<Date> fechasEntreEntradaYSalida = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(Fecha_llegada);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		while (calendar.getTime().before(Fecha_salida) || calendar.getTime().equals(Fecha_salida)) {
			Date fechaActual = calendar.getTime();
			String x = sdf.format(fechaActual);
			fechasEntreEntradaYSalida.add(formatearHora(x, "dd/MM/yy"));
			calendar.add(Calendar.DATE, 1);
		}

		return fechasEntreEntradaYSalida;
	}

	public void cancelarReservaFecha(ArrayList<String> habitacionesReserva, Date Fecha,
			HashMap<String, ArrayList<Date>> inventario) {

		for (String idHabitacion : habitacionesReserva) {
			ArrayList<Date> listaFechas = inventario.get(idHabitacion);
			listaFechas.remove(Fecha);
		}

	}

	public String cancelarReserva(ArrayList<String> habitacionesReserva, Date FechaLlegada,

			Date FechaSalida, HashMap<String, ArrayList<Date>> inventario) {

		Date fechaActual = new Date();

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(FechaLlegada);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(fechaActual);

		long tiempo1 = cal1.getTimeInMillis();
		long tiempo2 = cal2.getTimeInMillis();

		long diferencia = tiempo2 - tiempo1;
		int dias = (int) (diferencia / (1000 * 60 * 60 * 24));

		ArrayList<Date> FechasEntreEntradaYSalida = fechas(FechaLlegada, FechaSalida);

		if (dias <= 2) {
			for (Date Fecha : FechasEntreEntradaYSalida) {
				cancelarReservaFecha(habitacionesReserva, Fecha, inventario);
			}

			System.out.println(inventario);

			return ("Se ha cancelado su reserva");
		} else {
			return ("No es posible cancelar la reserva debido a que debe ser 2 días antes de la fecha de inicio de la reserva");
		}

	}

	public HashMap<String, Float> calcularValoresTotales(ArrayList<String> habitacionesReservadas,
			HashMap<String, Habitacion> habitaciones, HashMap<Date, Float> tarifaEstandar,
			HashMap<Date, Float> tarifaSuite, HashMap<Date, Float> tarifaSuiteDoble,
			ArrayList<Date> fechasEntreEntradaYSalida) {
		HashMap<String, Float> valoresTotales = new HashMap<String, Float>();

		for (String idHabitacion : habitacionesReservadas) {
			Habitacion habitacion = habitaciones.get(idHabitacion);
			float valorTotal = 0;

			for (Date fecha : fechasEntreEntradaYSalida) {
				float valorBase = habitacion.getPrecioFijo();
				String tipo = habitacion.getTipo();
				Float valorAdicional = null;
				if (tipo == "estandar") {
					valorAdicional = tarifaEstandar.get(fecha);
				} else if (tipo == "suite") {
					valorAdicional = tarifaSuite.get(fecha);
				} else {
					valorAdicional = tarifaSuiteDoble.get(fecha);
				}

				if (valorAdicional != null) {
					valorBase += valorAdicional;
				}

				valorTotal += valorBase;
			}

			valoresTotales.put(idHabitacion, valorTotal);
		}

		return valoresTotales;
	}

	public void cargarConsumo(HashMap<String, Object> opcion) {
		Servicio elServicio = null;

		if (opcion.get("bebidas") != null) {
			Object op = opcion.get("bebidas");
			elServicio = listaBebidas.get(Integer.parseInt((String) op) - 1);

		}
		if (opcion.get("palto") != null) {
			Object op = opcion.get("plato");
			elServicio = listaPlatos.get(Integer.parseInt((String) op) - 1);

		}
		if (opcion.get("servicio") != null) {
			Object op = opcion.get("servicio");
			elServicio = listaServicio.get(Integer.parseInt((String) op) - 1);

		}
		if (elServicio == null) {
			System.out.println("Algo Salio Mal");
		} else {
			Consumo objconsumo = new Consumo(LocalDate.now(), (String) elServicio.getNombre(),
					elServicio.getPrecioTotal(), (float) (elServicio.getPrecioTotal() * 0.19), false);
			HuespedReserva a = (HuespedReserva) opcion.get("reserva");
			HashMap<String, Consumo> losconsumos = a.getConsumos();
			losconsumos.put(objconsumo.getNombre(), objconsumo);

		}

	}

	public static String fechaString(Date date) {

		SimpleDateFormat dateFormatter = null;

		dateFormatter = new SimpleDateFormat("dd/MM");

		return dateFormatter.format(date);

	}

	public void generarFactura(String nombre) {
		// Factura factura= new Factura(LocalDate.now(), )
		// return

		// public Factura(Date fecha, String nombre, HuespedReserva huésped, float
		// valotTotal, float impuestos, int numeroFactura, ArrayList<Consumo> consumos)
		// {
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
