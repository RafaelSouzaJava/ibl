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

import com.rafael.ibltelecom.domain.ClienteCombo;
import com.rafael.ibltelecom.dto.ClienteComboDTO;
import com.rafael.ibltelecom.dto.ClienteNewComboDTO;
import com.rafael.ibltelecom.services.ClienteComboService;


@RestController
@RequestMapping(value = "/clientecombos")
public class ClienteComboComtroller {

	@Autowired
	private ClienteComboService clienteService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ClienteCombo> find(@PathVariable Integer id) {
		ClienteCombo obj = clienteService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewComboDTO objDto) {
		ClienteCombo obj = clienteService.fromDTO(objDto);
		obj = clienteService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteComboDTO objDto, @PathVariable Integer id){
		ClienteCombo obj = clienteService.fromDTO(objDto);
		obj.setId(id);
		obj = clienteService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteComboDTO>> findAll() {
		List<ClienteCombo> list = clienteService.findAll();
		List<ClienteComboDTO> listDto = list.stream().map(obj -> new ClienteComboDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteComboDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<ClienteCombo> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteComboDTO> listDto = list.map(obj -> new ClienteComboDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
