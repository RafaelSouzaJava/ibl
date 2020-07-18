package com.rafael.ibltelecom.domain.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.ibltelecom.domain.Bairro;
import com.rafael.ibltelecom.domain.Logradouro;
import com.rafael.ibltelecom.dto.BairroDTO;
import com.rafael.ibltelecom.dto.LogradouroDTO;
import com.rafael.ibltelecom.services.BairroService;
import com.rafael.ibltelecom.services.LogradouroService;



@RestController
@RequestMapping(value = "/bairros")
public class BairroController {

	@Autowired
	private BairroService service;
	
	@Autowired
	private LogradouroService logradouroService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<BairroDTO>> findAll(){
		List<Bairro> list = service.findAll();
		List<BairroDTO> listDto = list.stream().map(obj -> new BairroDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		
	}
	
	@RequestMapping(value = "/{bairrosId}/logradouros", method = RequestMethod.GET)
	public ResponseEntity<List<LogradouroDTO>> findLogradouro(@PathVariable Integer bairrosId){
		List<Logradouro> list = logradouroService.findByEstado(bairrosId);
		List<LogradouroDTO> listDto = list.stream().map(obj -> new LogradouroDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/{bairrosId}/", method = RequestMethod.GET)
	public ResponseEntity<List<Logradouro>> findLogradouro(@RequestParam(value = "nome") String nome){
		List<Logradouro> list = logradouroService.findNomeByEstado(nome);
		return ResponseEntity.ok().body(list);
	}
}
