package com.projecto.java.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "accidentes")
public class Accidente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAccidente;

	private String titulo;
	private Zona localizacion;
	private String tipo;
	private String asunto;
	private String fecha;

//	Getters and Setters

	public int getIdAccidente() {
		return idAccidente;
	}

	public void setIdAccidente(int idAccidente) {
		this.idAccidente = idAccidente;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Zona getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Zona localizacion) {
		this.localizacion = localizacion;
	}

}