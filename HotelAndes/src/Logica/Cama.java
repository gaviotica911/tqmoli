package Logica;

import java.util.HashMap;

public class Cama {
	CargardorArchivo cargador= new CargardorArchivo();
	//Atributos
	public String tamaño;
	public String uso;
	public int numeroNiños;
	public int numeroAdultos;
	public HashMap<String,Cama> camas= cargador.getCamas();
	//Constructor
	public Cama(String tamaño, String uso, int numeroNiños, int numeroAdultos) {
		this.tamaño = tamaño;
		this.uso = uso;
		this.numeroNiños = numeroNiños;
		this.numeroAdultos = numeroAdultos;
	}

	public String getTamaño() {
		return tamaño;
	}

	public String getUso() {
		return uso;
	}

	public int getNumeroNiños() {
		return numeroNiños;
	}

	public int getNumeroAdultos() {
		return numeroAdultos;
	}
	
	

}
