package Logica;

import java.util.HashMap;

public  class Bebida implements Servicio {
	CargardorArchivo cargador= new CargardorArchivo();
	    private float precio;
	    private String horariosDeDisponibilidad;
	    private boolean llevable;
		private String nombre;
		public HashMap<String, Bebida> bebidas= cargador.getBebidas();
	    
		
		
	    // Constructor
	    public Bebida(String producto, float precio, String tiempo, boolean llevable) {
	        this.nombre = producto;
	        this.precio = (float) precio;
	        this.horariosDeDisponibilidad = tiempo;
	        this.llevable = llevable;
	    
}


		public boolean isLlevable() {
			return llevable;
		}


		@Override
		public String getNombre() {
			
			return nombre;
		}

		@Override
		public String getUbicacion() {
						return "Restaurante";
		}

		@Override
		public float getPrecioTotal() {
			
			return precio;
		}

		@Override
		public String[] getDias() {
			String[] dias= {"lunes","martes","miercoles","jueves", "viernes","sabado","domingo"};
			return dias;
		}

		@Override
		public String getHorariosDeDisponibilidad() {
			
			return horariosDeDisponibilidad;
		}}