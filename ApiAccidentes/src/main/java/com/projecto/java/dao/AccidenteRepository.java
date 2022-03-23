package com.projecto.java.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projecto.java.model.Accidente;
import com.projecto.java.model.Zona;

@Repository
public interface AccidenteRepository extends CrudRepository<Accidente, Integer> {

	@Query("from Zona")
	public List<Zona> findAllZonas();
}
