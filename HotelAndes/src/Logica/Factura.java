package Logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Factura {
	
	public LocalDate  fecha;
	public String nombre;
	public HuespedReserva huésped;
	public float valotTotal;
	public float impuestos;
	public int numeroFactura;
	public ArrayList<Consumo> consumos= new ArrayList<Consumo>();
	
	
	public Factura(LocalDate fecha, String nombre, HuespedReserva huésped, float valotTotal, float impuestos,
			int numeroFactura, ArrayList<Consumo> consumos) {
		
		this.fecha = fecha;
		this.nombre = nombre;
		this.huésped = huésped;
		this.valotTotal = valotTotal;
		this.impuestos = impuestos;
		this.numeroFactura = numeroFactura;
		this.consumos = consumos;
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
	public float getValotTotal() {
		return valotTotal;
	}
	public float getImpuestos() {
		return impuestos;
	}
	public int getNumeroFactura() {
		return numeroFactura;
	}
	public ArrayList<Consumo> getConsumos() {
		return consumos;
	}
	
	
	
	


}
