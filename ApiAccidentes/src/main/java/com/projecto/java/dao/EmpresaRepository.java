package com.projecto.java.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projecto.java.model.Accidente;
import com.projecto.java.model.Empresa;

@Repository
public interface EmpresaRepository extends CrudRepository<Empresa, Integer> {

	@Query("from Accidente")
	public List<Accidente> findAllAccidentes();
}
