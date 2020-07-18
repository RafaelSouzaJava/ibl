package com.rafael.ibltelecom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.ibltelecom.domain.Bairro;
import com.rafael.ibltelecom.repositories.BairroRepository;
import com.rafael.ibltelecom.services.exceptions.ObjectNotFoundException;


@Service
public class BairroService {

	@Autowired
	private BairroRepository repository;
	
	public List<Bairro> findAll(){
		return repository.findAllByOrderByBairro();
	}
	
	/*public List<Bairro> findByEstado(Integer logradourosId){
		return repository.findBairros(logradourosId);
	}
	
	public List<Bairro> findNomeByEstado(String nome){
		return repository.findNomeBairros(nome);
	}*/
	
	public Bairro buscar(Integer id) {
		Optional<Bairro> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Bairro não encontrado! Id: " + id +", tipo: " + Bairro.class.getName()));
	}
}
