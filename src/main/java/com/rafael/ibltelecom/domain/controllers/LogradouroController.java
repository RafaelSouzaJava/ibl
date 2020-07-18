package com.rafael.ibltelecom.domain.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.ibltelecom.domain.Logradouro;
import com.rafael.ibltelecom.dto.LogradouroDTO;
import com.rafael.ibltelecom.services.LogradouroService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/logradouros")
public class LogradouroController {

	@Autowired
	private LogradouroService logradouroService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<LogradouroDTO>> findAll(){
		List<Logradouro> list = logradouroService.findAll();
		List<LogradouroDTO> listDto = list.stream().map(obj -> new LogradouroDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		
	}
	
	/*@RequestMapping(value = "/{logadourosId}/bairros", method = RequestMethod.GET)
	public ResponseEntity<List<BairroDTO>> findLogradouro(@PathVariable Integer logadourosId){
		List<Bairro> list = service.findByEstado(logadourosId);
		List<BairroDTO> listDto = list.stream().map(obj -> new BairroDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}*/
	
	@RequestMapping(value = "/{bairrosId}/", method = RequestMethod.GET)
	public ResponseEntity<List<Logradouro>> findLogradouro(@RequestParam(value = "nome") String nome){
		List<Logradouro> list = logradouroService.findNomeByEstado(nome);
		return ResponseEntity.ok().body(list);
	}
}
