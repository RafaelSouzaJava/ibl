package com.rafael.ibltelecom.domain.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.ibltelecom.domain.Combo;
import com.rafael.ibltelecom.services.ComboService;


@RestController
@RequestMapping(value = "/combos")
public class ComboController {

	@Autowired
	private ComboService comboService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Combo> find(@PathVariable Integer id) {
		Combo obj = comboService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		comboService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Combo>> findAll() {
		List<Combo> list = comboService.findAll();		;
		return ResponseEntity.ok().body(list);
	}	
}
