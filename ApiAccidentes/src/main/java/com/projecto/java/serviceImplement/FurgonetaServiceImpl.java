package com.projecto.java.serviceImplement;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projecto.java.dao.FurgonetaRepository;
import com.projecto.java.model.Empresa;
import com.projecto.java.model.Furgoneta;
import com.projecto.java.model.Zona;
import com.projecto.java.service.FurgonetaService;

@Service
public class FurgonetaServiceImpl implements FurgonetaService {

	@Autowired
	private FurgonetaRepository repository;
	
	@Override
	public List<Furgoneta> findAllFurgonetas() {
	
		return  (List<Furgoneta>) repository.findAll();
	}

	@Override
	public Furgoneta findById(int id) {

		return repository.findById(id).get();
	}

	@Override
	public Furgoneta saveFurgoneta(Furgoneta furgoneta) {

		return repository.save(furgoneta);
	}

	@Override
	public void deleteFurgoneta(int id) {
		
		this.repository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Zona> findAllZonas() {
		
		return this.repository.findAllZonas();
	}

	@Override
	public List<Furgoneta> updateFurgoneta(Furgoneta furgonetaUpdate) {
		int num = furgonetaUpdate.getIdFurgoneta();
		
		if(repository.findById(num).isPresent()) {
			furgonetaUpdate.setIdFurgoneta(furgonetaUpdate.getIdFurgoneta());
			furgonetaUpdate.setIdentificacion(furgonetaUpdate.getIdentificacion());
			furgonetaUpdate.setZonas(furgonetaUpdate.getZonas());
			
			repository.save(furgonetaUpdate);
			return (List<Furgoneta>) repository.findAll();
		}
		return (List<Furgoneta>) furgonetaUpdate;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Empresa> findAllEmpresa() {
		
		return this.repository.findAllEmpresas();
	} 
}
