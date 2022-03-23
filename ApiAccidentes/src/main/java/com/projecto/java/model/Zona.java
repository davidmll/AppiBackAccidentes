package com.projecto.java.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "zonas")
public class Zona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idZona;
	
	private String nombre;
		

//	Getters and Setters
	
	public int getIdZona() {
		return idZona;
	}

	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
