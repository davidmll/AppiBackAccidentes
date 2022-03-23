package com.projecto.java.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "furgonetas")
public class Furgoneta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFurgoneta;

	private String identificacion;

	private Zona zonas;

	@ManyToOne
	@JoinColumn(name = "id_empresa", nullable = false)
	private Empresa empresas;

//	Getters and Setters

	public int getIdFurgoneta() {
		return idFurgoneta;
	}

	public void setIdFurgoneta(int idFurgoneta) {
		this.idFurgoneta = idFurgoneta;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public Zona getZonas() {
		return zonas;
	}

	public void setZonas(Zona zonas) {
		this.zonas = zonas;
	}

	public Empresa getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Empresa empresas) {
		this.empresas = empresas;
	}

}
