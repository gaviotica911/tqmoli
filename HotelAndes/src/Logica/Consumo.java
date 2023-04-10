package Logica;

import java.util.Date;

public class Consumo {
	
	public Date fecha;
	public String nombre;
	public HuespedReserva huésped;
	public float precio;
	public float impuestos;
	public Boolean pagado;
	
	
	public Consumo(Date fecha, String nombre, HuespedReserva huésped, float precio, float impuestos, Boolean pagado) {
	
		this.fecha = fecha;
		this.nombre = nombre;
		this.huésped = huésped;
		this.precio = precio;
		this.impuestos = impuestos;
		this.pagado = pagado;
	}
	public Date getFecha() {
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
	public Boolean getPagado() {
		return pagado;
	}
	
	

	
	

}
