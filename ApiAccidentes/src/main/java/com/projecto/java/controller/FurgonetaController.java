package com.projecto.java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projecto.java.model.Accidente;
import com.projecto.java.model.Empresa;
import com.projecto.java.model.Furgoneta;
import com.projecto.java.model.Zona;
import com.projecto.java.service.FurgonetaService;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class FurgonetaController {

	@Autowired
	private FurgonetaService service;
	
//	Methods Get

	@GetMapping("/furgonetas")
	public List<Furgoneta> index() {
		return service.findAllFurgonetas();
	}

	@GetMapping("furgoneta/{id}")
	public ResponseEntity<?> findFurgonetaById(@PathVariable int id) {

		Furgoneta furgoneta = null;

		Map<String, Object> response = new HashMap<>();

		try {

			furgoneta = service.findById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (furgoneta == null) {
			response.put("mensaje", "La furgoneta con ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Furgoneta>(furgoneta, HttpStatus.OK);

	}
	
	@GetMapping("/furgoneta/zonas")
	public List<Zona> listarZonas(){
		return service.findAllZonas();
	}
	
	@GetMapping("/furgoneta/empresas")
	public List<Empresa> listarEmpresas(){
		return service.findAllEmpresa();
	}

//	Method Post
	
	@PostMapping("/furgoneta/save")
	public ResponseEntity<?> saveFurgoneta(@RequestBody Furgoneta furgoneta) {
		Furgoneta furgonetaNew = null;

		Map<String, Object> response = new HashMap<>();

		try {

			furgonetaNew = service.saveFurgoneta(furgoneta);
			
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", furgonetaNew);
		response.put("cliente", "La furgoneta ha sido creado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

//	Method Put

	@PutMapping("/furgoneta/update")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateFurgoneta(@RequestBody Furgoneta furgoneta) {

		Map<String, Object> response = new HashMap<>();

		try {
			service.updateFurgoneta(furgoneta);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("cliente", furgoneta);
		response.put("mensaje", "La furgoneta ha sido actualizada con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

//	Method Delete

	@DeleteMapping("/furgoneta/{id}")
	public ResponseEntity<?> deleteFurgoneta(@PathVariable int id) {

		Furgoneta furgonetaDelete = service.findById(id);
		Map<String, Object> response = new HashMap<>();

		if (furgonetaDelete == null) {
			response.put("mensaje", "Error: no se pudo eliminar la furgoneta con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			service.deleteFurgoneta(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar la información en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("cliente", furgonetaDelete);
		response.put("mensaje", "La furgoneta ha sido eliminada con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}
