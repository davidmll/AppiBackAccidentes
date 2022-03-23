package com.projecto.java.service;

import java.util.List;

import com.projecto.java.model.Accidente;
import com.projecto.java.model.Empresa;

public interface EmpresaService {

	List<Empresa> findAllEmpresas();

	Empresa findById(int id);

	Empresa saveEmpresa(Empresa empresa);

	void deleteEmpresa(int id);
	
	List<Accidente> findAllAccidentes();
	
	List<Empresa> updateEmpresa(Empresa empresaUpdate);

}