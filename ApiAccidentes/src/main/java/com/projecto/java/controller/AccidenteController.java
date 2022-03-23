package com.projecto.java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projecto.java.model.Accidente;
import com.projecto.java.model.Zona;
import com.projecto.java.service.AccidenteService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AccidenteController {

	@Autowired
	private AccidenteService service;
	
//	Methods Get

	@GetMapping("/accidentes")
	public List<Accidente> index() {
		return service.findAllAccidentes();
	}

	@GetMapping("accidente/{id}")
	public ResponseEntity<?> findAccidenteById(@PathVariable int id) {

		Accidente accidente = null;

		Map<String, Object> response = new HashMap<>();

		try {

			accidente = service.findById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (accidente == null) {
			response.put("mensaje", "El accidente con ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Accidente>(accidente, HttpStatus.OK);

	}
	
	@GetMapping("/accidente/zonas")
	public List<Zona> listarZonas(){
		return service.findAllZonas();
	}

//	Method Post
	
	@PostMapping("/accidente/save")
	public ResponseEntity<?> saveFurgoneta(@RequestBody Accidente accidente) {
		Accidente accidenteNew = null;

		Map<String, Object> response = new HashMap<>();

		try {

			accidenteNew = service.saveAccidente(accidente);
			
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", accidenteNew);
		response.put("cliente", "El accidente ha sido registrado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

//	Method Put

	@PutMapping("/accidente/update")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateAccidente(@RequestBody Accidente accidente) {

		Map<String, Object> response = new HashMap<>();

		try {
			
			service.updateAccidente(accidente);
			
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("cliente", accidente);
		response.put("mensaje", "El accidente ha sido actualizado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

//	Method Delete

	@DeleteMapping("/accidente/{id}")
	public ResponseEntity<?> deleteAccidente(@PathVariable int id) {

		Accidente accidenteDelete = service.findById(id);
		Map<String, Object> response = new HashMap<>();

		if (accidenteDelete == null) {
			response.put("mensaje", "Error: no se pudo eliminar el accidente con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			service.deleteAccidente(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar la información en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("cliente", accidenteDelete);
		response.put("mensaje", "El accidente ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
