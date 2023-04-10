package Logica;

import java.util.Date;

public class Reserva {
	private HuespedReserva huesped ;
	private Date fecha_llegada;
	private Date fecha_salida;
	private float precio;
	private int cantidadDeAcompañantesNinos;
	private int cantidadDeAcompañantesAdultos;
	private int numeroDeHabitaciones;
	private float montoTotal;
	private boolean estado;
	//holaaa
	
	
	
	
	
	public int getCantidadDeAcompañantesNinos() {
		return cantidadDeAcompañantesNinos;
	}
	public int getCantidadDeAcompañantesAdultos() {
		return cantidadDeAcompañantesAdultos;
	}
	public Date getFecha_llegada() {
		return fecha_llegada;
	}
	public Date getFecha_salida() {
		return fecha_salida;
	}
	public float getPrecio() {
		return precio;
	}
	
	public Reserva(HuespedReserva huesped, Date fecha_llegada, Date fecha_salida, float precio,
			int cantidadDeAcompañantesNinos, int cantidadDeAcompañantesAdultos, int numeroDeHabitaciones,
			float montoTotal, boolean estado) {
	
		this.huesped = huesped;
		this.fecha_llegada = fecha_llegada;
		this.fecha_salida = fecha_salida;
		this.precio = precio;
		this.cantidadDeAcompañantesNinos = cantidadDeAcompañantesNinos;
		this.cantidadDeAcompañantesAdultos = cantidadDeAcompañantesAdultos;
		this.numeroDeHabitaciones = numeroDeHabitaciones;
		this.montoTotal = montoTotal;
		this.estado = estado;
	}
	public int getNumeroDeHabitaciones() {
		return numeroDeHabitaciones;
	}
	public float getMontoTotal() {
		return montoTotal;
	}
	public boolean isEstado() {
		return estado;
	}
	
	
	
	
	
	
	
	
	
}
