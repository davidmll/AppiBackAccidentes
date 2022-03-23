package com.projecto.java.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projecto.java.dao.AccidenteRepository;
import com.projecto.java.model.Accidente;
import com.projecto.java.model.Zona;
import com.projecto.java.service.AccidenteService;

@Service
public class AccidenteServiceImpl implements AccidenteService {

	@Autowired
	private AccidenteRepository repository;

	@Override
	public List<Accidente> findAllAccidentes() {
	
		return (List<Accidente>) repository.findAll();
	}

	@Override
	public Accidente findById(int id) {

		return repository.findById(id).get();
	}

	@Override
	public Accidente saveAccidente(Accidente accidente) {

		return repository.save(accidente);
	}

	@Override
	public void deleteAccidente(int id) {
		
		this.repository.deleteById(id);
	} 
	
	@Override
	@Transactional(readOnly = true)
	public List<Zona> findAllZonas() {
		
		return this.repository.findAllZonas();
	} 
	
	@Override
	public List<Accidente> updateAccidente(Accidente accidenteUpdate){
		int num = accidenteUpdate.getIdAccidente();
		
		if(repository.findById(num).isPresent()) {
			
			accidenteUpdate.setIdAccidente(accidenteUpdate.getIdAccidente());
			accidenteUpdate.setAsunto(accidenteUpdate.getAsunto());
			accidenteUpdate.setLocalizacion(accidenteUpdate.getLocalizacion());
			accidenteUpdate.setTipo(accidenteUpdate.getTipo());
			accidenteUpdate.setTitulo(accidenteUpdate.getTitulo());
			accidenteUpdate.setFecha(accidenteUpdate.getFecha());
			
			repository.save(accidenteUpdate);
			return (List<Accidente>) repository.findAll();
		}
		return (List<Accidente>) accidenteUpdate;
	}
}
