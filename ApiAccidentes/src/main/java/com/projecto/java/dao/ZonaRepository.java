package com.projecto.java.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projecto.java.model.Zona;

@Repository
public interface ZonaRepository extends CrudRepository<Zona, Integer> {

}
