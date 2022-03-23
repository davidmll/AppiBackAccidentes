package com.projecto.java.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projecto.java.model.Accidente;
import com.projecto.java.model.Empresa;
import com.projecto.java.model.Zona;
import com.projecto.java.service.EmpresaService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class EmpresaController {

	@Autowired
	private EmpresaService service;

//	Methods Get

	@GetMapping("/empresas")
	public List<Empresa> index() {
		return service.findAllEmpresas();
	}

	@GetMapping("empresa/{id}")
	public ResponseEntity<?> findEmpresaById(@PathVariable int id) {

		Empresa empresa = null;

		Map<String, Object> response = new HashMap<>();

		try {

			empresa = service.findById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (empresa == null) {
			response.put("mensaje", "La empresa con ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Empresa>(empresa, HttpStatus.OK);

	}
	
	@GetMapping("/empresa/accidentes")
	public List<Accidente> listarAccidentes(){
		return service.findAllAccidentes();
	}

//	Method Post
	
	@PostMapping("/empresa/save")
	public ResponseEntity<?> saveEmpresa(@RequestBody Empresa empresa) {
		Empresa empresaNew = null;

		Map<String, Object> response = new HashMap<>();

		try {

			empresaNew = service.saveEmpresa(empresa);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", empresaNew);
		response.put("cliente", "La empresa ha sido creado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

//	Method Put

	@PutMapping("/empresa/update")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateEmpresa(@RequestBody Empresa empresa) {

		Map<String, Object> response = new HashMap<>();
		try {
			service.updateEmpresa(empresa);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("cliente", empresa);
		response.put("mensaje", "La empresa ha sido actualizada con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
//	Method Delete

	@DeleteMapping("/empresa/{id}")
	public ResponseEntity<?> deleteEmpresa(@PathVariable int id) {

		Empresa empresaDelete = service.findById(id);
		Map<String, Object> response = new HashMap<>();

		if (empresaDelete == null) {
			response.put("mensaje", "Error: no se pudo eliminar la empresa con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			service.deleteEmpresa(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar la información en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("cliente", empresaDelete);
		response.put("mensaje", "La empresa ha sido eliminada con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	

}
