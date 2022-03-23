package com.projecto.java.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projecto.java.dao.EmpresaRepository;
import com.projecto.java.model.Accidente;
import com.projecto.java.model.Empresa;
import com.projecto.java.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaRepository repository;
	
	@Override
	public List<Empresa> findAllEmpresas() {
	
		return (List<Empresa>) repository.findAll();
	}

	@Override
	public Empresa findById(int id) {

		return repository.findById(id).get();
	}

	@Override
	public Empresa saveEmpresa(Empresa empresa) {

		return repository.save(empresa);
	}

	@Override
	public void deleteEmpresa(int id) {
		repository.deleteById(id);
	}

	@Override
	public List<Empresa> updateEmpresa(Empresa empresaUpdate) {
		int num = empresaUpdate.getIdempresa();
		
		if(repository.findById(num).isPresent()) {
			empresaUpdate.setIdempresa(empresaUpdate.getIdempresa());
			empresaUpdate.setNombre(empresaUpdate.getNombre());
			empresaUpdate.setAccidentes(empresaUpdate.getAccidentes());
			
			repository.save(empresaUpdate);
			return (List<Empresa>) repository.findAll();
		}
		return (List<Empresa>) empresaUpdate;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Accidente> findAllAccidentes() {
		// TODO Auto-generated method stub
		return repository.findAllAccidentes();
	} 
}
