package com.rafael.ibltelecom.domain.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafael.ibltelecom.domain.CategoriaCombo;
import com.rafael.ibltelecom.dto.CategoriaComboDTO;
import com.rafael.ibltelecom.services.CategoriaComboService;


@RestController
@RequestMapping(value = "/categoriacombos")
public class CategoriaCombosComtroller {

	@Autowired
	private CategoriaComboService categoriacomboService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoriaCombo> find(@PathVariable Integer id) {
		CategoriaCombo obj = categoriacomboService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaComboDTO objDto) {
		CategoriaCombo obj = categoriacomboService.fromDTO(objDto);
		obj = categoriacomboService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CategoriaCombo> update(@Valid @RequestBody CategoriaComboDTO objDto, @PathVariable Integer id){
		CategoriaCombo obj = categoriacomboService.fromDTO(objDto);
		obj.setId(id);
		obj = categoriacomboService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriacomboService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaComboDTO>> findAll() {
		List<CategoriaCombo> list = categoriacomboService.findAll();
		List<CategoriaComboDTO> listDto = list.stream().map(obj -> new CategoriaComboDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaComboDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<CategoriaCombo> list = categoriacomboService.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaComboDTO> listDto = list.map(obj -> new CategoriaComboDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}

