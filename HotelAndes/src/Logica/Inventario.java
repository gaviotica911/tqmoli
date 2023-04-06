package Logica;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;



public class Inventario {
	
	Habitacion cuarto;
	boolean disponible;
	
	
	CargardorArchivo cargador= new CargardorArchivo();
	HashMap<String, ArrayList<Date>> inventario= new HashMap<String, ArrayList<Date>>();
	
	
	HashMap<String, Habitacion> cuartos=cargador.getHabitacionies();
	
	
	
	public HashMap<String, ArrayList<Date>> getInventario() {
		for (String i: cuartos.keySet()) {
			Habitacion hab=cuartos.get(i);
			ArrayList<Date> diasOcupados=new ArrayList<Date>();
			inventario.put(hab.getId(), diasOcupados);
		}
		return inventario;
	}
	
	
	public ArrayList<Date> fechasOcupado(String id) {
		
		return inventario.get(id);
	}
	public void setDisponibilidad(String IDcuarto, Date ocupada) {
		ArrayList<Date> diasOcupados= inventario.get(IDcuarto);
		diasOcupados.add(ocupada);
		
	}

}