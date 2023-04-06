package Logica;

import java.util.HashMap;

public class Restaurante {
	CargardorArchivo cargador= new CargardorArchivo();
	public HashMap<String, Plato> platos= cargador.getPlatos();
	public HashMap<String, Bebida> bebidas= cargador.getBebidas();
	public HashMap<String, Plato> getPlatos() {
		return platos;
	}
	public HashMap<String, Bebida> getBebidas() {
		return bebidas;
	}
	
	

}
