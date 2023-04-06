package Logica;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class FuncionesEmpleado {
	Inventario inventarioInstancia =new Inventario();
	HashMap<String, ArrayList<Date>> inventario= inventarioInstancia.getInventario();
		
	public ArrayList<String> reserva(HuespedReserva huesped1, Date Fecha_llegada,Date  Fecha_salida,int cantidadDeAcompañantes, HashMap<String, ArrayList<Habitacion>> catalogo)
	{
		 int[] habitacionestipo = new int[3]; 
	     while (cantidadDeAcompañantes > 0) 
	     {
	           if (cantidadDeAcompañantes >= 4) 
	           { //  suiteDoble
	                habitacionestipo[2]++;
	                cantidadDeAcompañantes -= 4;
	            }
	           else if (cantidadDeAcompañantes >= 3) 
	           { // suite
	                habitacionestipo[1]++;
	                cantidadDeAcompañantes -= 3;
	            } else 
	            {//  	estandar
	                habitacionestipo[0]++;
	                cantidadDeAcompañantes -= 2;
	            }}
	      
		 
		 ArrayList<Date> fechasEntreEntradaYSalida = new ArrayList<Date>();
	     Calendar calendar = Calendar.getInstance();
	     calendar.setTime(Fecha_llegada);

	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	     while (calendar.getTime().before(Fecha_salida) || calendar.getTime().equals(Fecha_salida)) {
	         Date fechaActual = calendar.getTime();
	         String x =sdf.format(fechaActual);
	         fechasEntreEntradaYSalida.add(formatearHora(x, "dd/MM/yy"));
	         calendar.add(Calendar.DATE, 1);}
	     
	     //ArrayList<Date> diasOcupados= inventario.get(IDcuarto);
	     


	     ArrayList<String> idsReservados = new ArrayList<String>();

	     for (int i = 0; i < fechasEntreEntradaYSalida.size(); i++) {
	         Date fecha = fechasEntreEntradaYSalida.get(i);

	         for (int j = 0; j < habitacionestipo.length; j++) {
	             int cantidad = habitacionestipo[j];
	             String tipoHabitacion = "";
	             switch (j) {
	                 case 0:
	                     tipoHabitacion = "estandar";
	                     break;
	                 case 1:
	                     tipoHabitacion = "suite";
	                     break;
	                 case 2:
	                     tipoHabitacion = "suiteDoble";
	                     break;
	             }

	             ArrayList<Habitacion> habitaciones = catalogo.get(tipoHabitacion);
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

	     return idsReservados;
	 }



public void  cancelarReservaFecha(ArrayList<String>  habitacionesReserva, Date Fecha, HashMap<String, ArrayList<Date>> inventario)
	{
			
			for (String idHabitacion : habitacionesReserva)
					{
						ArrayList<Date> listaFechas=inventario.get(idHabitacion);
						listaFechas.remove(Fecha);
					}
			
		}
	public String cancelarReserva(ArrayList<String>  habitacionesReserva,Date FechaLlegada ,ArrayList<Date> FechasEntreEntradaYSalida, HashMap<String, ArrayList<Date>> inventario)
	{
		Date fechaActual = new Date();

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(FechaLlegada);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(fechaActual);

		long tiempo1 = cal1.getTimeInMillis();
		long tiempo2 = cal2.getTimeInMillis();

		long diferencia = tiempo2 - tiempo1;
		int dias = (int) (diferencia / (1000 * 60 * 60 * 24));
		
		if (dias<=2) {
			for (Date Fecha:FechasEntreEntradaYSalida)
			{cancelarReservaFecha(habitacionesReserva,Fecha,inventario);}
			return("Se ha cancelado su reserva");
		}
		else {
			return("No es posible cancelar la reserva debido a que debe ser 2 días antes de la fecha de inicio de la reserva");
		}}
		
public static HashMap<String, Float> calcularValoresTotales(ArrayList<String> habitacionesReservadas, HashMap<String, Habitacion> habitaciones, HashMap<Date, Float> tarifas, ArrayList<Date> fechasEntreEntradaYSalida) {
        HashMap<String, Float> valoresTotales = new HashMap<String, Float>();

        for (String idHabitacion : habitacionesReservadas) {
            Habitacion habitacion = habitaciones.get(idHabitacion);
            float valorTotal = 0;

            for (Date fecha : fechasEntreEntradaYSalida) {
                float valorBase = habitacion.getPrecioFijo();

                Float valorAdicional = tarifas.get(fecha);
                if (valorAdicional != null) {
                    valorBase += valorAdicional;
                }

                valorTotal += valorBase;
            }

            valoresTotales.put(idHabitacion, valorTotal);
        }

        return valoresTotales;
    }

	
	
	
	public static String fechaString(Date date)
	{
		 
	        
            SimpleDateFormat dateFormatter=null;
	             
	        dateFormatter = new SimpleDateFormat("dd/MM");
	       
	        return dateFormatter.format(date);
	        
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
        return date;}
	
	
	
	
	public void cargarConsumo() {}


}
