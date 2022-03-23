package com.projecto.java.service;

import java.util.List;

import com.projecto.java.model.Empresa;
import com.projecto.java.model.Furgoneta;
import com.projecto.java.model.Zona;

public interface FurgonetaService {

	List<Furgoneta> findAllFurgonetas();

	Furgoneta findById(int id);

	Furgoneta saveFurgoneta(Furgoneta furgoneta);

	void deleteFurgoneta(int id);
	
	public List<Zona> findAllZonas();
	
	public List<Empresa> findAllEmpresa();
	
	List<Furgoneta> updateFurgoneta(Furgoneta furgoneta);

}