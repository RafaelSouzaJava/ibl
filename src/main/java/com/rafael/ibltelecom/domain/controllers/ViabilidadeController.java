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

import com.rafael.ibltelecom.domain.Viabilidade;
import com.rafael.ibltelecom.dto.ViabilidadeDTO;
import com.rafael.ibltelecom.services.ViabilidadeService;


@RestController
@RequestMapping(value = "/viabilidades")
public class ViabilidadeController {

	@Autowired
	ViabilidadeService viabilidadeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Viabilidade> find(@PathVariable Integer id) {
		Viabilidade obj = viabilidadeService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ViabilidadeDTO objDto) {
		Viabilidade obj = viabilidadeService.fromDTO(objDto);
		obj = viabilidadeService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		viabilidadeService.delete(id);
		return ResponseEntity.noContent().build();
		
	}	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ViabilidadeDTO>> findAll() {
		List<Viabilidade> list = viabilidadeService.findAll();
		List<ViabilidadeDTO> listDto = list.stream().map(obj -> new ViabilidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}	
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ViabilidadeDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Viabilidade> list = viabilidadeService.findPage(page, linesPerPage, orderBy, direction);
		Page<ViabilidadeDTO> listDto = list.map(obj -> new ViabilidadeDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
