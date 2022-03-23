package com.projecto.java.service;

import java.util.List;

import com.projecto.java.model.Accidente;
import com.projecto.java.model.Zona;

public interface AccidenteService {

	public List<Accidente> findAllAccidentes();

	public Accidente findById(int id);

	public Accidente saveAccidente(Accidente accidente);
	
	public void deleteAccidente(int id);
	
	public List<Zona> findAllZonas();
	
	List<Accidente> updateAccidente(Accidente accidenteUpdate);
	
}