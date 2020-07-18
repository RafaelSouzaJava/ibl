package com.rafael.ibltelecom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.ibltelecom.domain.Logradouro;
import com.rafael.ibltelecom.domain.enums.FormaPagamento;
import com.rafael.ibltelecom.repositories.LogradouroRepository;
import com.rafael.ibltelecom.services.exceptions.ObjectNotFoundException;


@Service
public class LogradouroService {

	@Autowired
	private LogradouroRepository repository;
	
	public List<Logradouro> findAll(){
		return repository.findAllByOrderByLogradouro();
	}
	
	public List<Logradouro> findByEstado(Integer bairrosId){
		return repository.findLogradouros(bairrosId);
	}
	
	public List<Logradouro> findNomeByEstado(String nome){
		return repository.findNomeLogradouros(nome);
	}
	
	public Logradouro buscar(Integer id) {
		Optional<Logradouro> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Logradouro não encontrado! Id: " + id +", tipo: " + FormaPagamento.class.getName()));
	}
}
