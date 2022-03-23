package com.projecto.java.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projecto.java.model.Empresa;
import com.projecto.java.model.Furgoneta;
import com.projecto.java.model.Zona;

@Repository
public interface FurgonetaRepository extends CrudRepository<Furgoneta, Integer> {

	@Query("from Zona")
	public List<Zona> findAllZonas();
	
	@Query("from Empresa")
	public List<Empresa> findAllEmpresas();
}
