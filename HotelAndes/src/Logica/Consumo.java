package Logica;

import java.util.Date;
import java.time.LocalDate;


public class Consumo {
	
	public LocalDate fecha;
	public String nombre;
	public HuespedReserva huésped;
	public float precio;
	public float impuestos;
	public boolean pagado;
	
	
	public Consumo(LocalDate fecha, String nombre, HuespedReserva huésped, float precio, float impuestos, boolean pagado) {
	
		this.fecha = fecha;
		this.nombre = nombre;
		this.huésped = huésped;
		this.precio = precio;
		this.impuestos = impuestos;
		this.pagado = pagado;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public String getNombre() {
		return nombre;
	}
	public HuespedReserva getHuésped() {
		return huésped;
	}
	public float getPrecio() {
		return precio;
	}
	public float getImpuestos() {
		return impuestos;
	}
	public boolean getPagado() {
		return pagado;
	}
	
	

	
	

}
