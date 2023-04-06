package Logica;

public class ServicioRecreativo implements Servicio {
	public String nombre ;
	public String ubicacion ;
	public float precioTotal ;
	public String[] dias ;
	public String horariosDeDisponibilidad;
	
	
	
	public ServicioRecreativo(String nombre, String ubicacion, float precioTotal, String[] dias,
			String horariosDeDisponibilidad) {
	
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.precioTotal = precioTotal;
		this.dias = dias;
		this.horariosDeDisponibilidad = horariosDeDisponibilidad;
	}
	@Override
	public String getNombre() {
		
		return nombre;
	}
	@Override
	public String getUbicacion() {
		
		return ubicacion;
	}
	@Override
	public float getPrecioTotal() {
		
		return precioTotal;
	}
	@Override
	public String[] getDias() {
		
		return dias;
	}
	@Override
	public String getHorariosDeDisponibilidad() {
		
		return horariosDeDisponibilidad;
	}
	
	
	
}
