package com.rafael.ibltelecom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rafael.ibltelecom.domain.Categoria;
import com.rafael.ibltelecom.domain.Viabilidade;
import com.rafael.ibltelecom.dto.ViabilidadeDTO;
import com.rafael.ibltelecom.repositories.ViabilidadeRepository;
import com.rafael.ibltelecom.services.exceptions.DataIntegrityException;
import com.rafael.ibltelecom.services.exceptions.ObjectNotFoundException;


@Service
public class ViabilidadeService {

	@Autowired
	ViabilidadeRepository viabilidadeRepository;

	public Viabilidade find(Integer id) {
		Optional<Viabilidade> obj = viabilidadeRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Viabilidade não encontrado! Id: " + id +", tipo: " + Categoria.class.getName()));
	}
	
	public Viabilidade insert(Viabilidade obj) {
		obj.setId(null);
		return viabilidadeRepository.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			viabilidadeRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Viabilidade que possui planos");
		}
	}
	
	public List<Viabilidade> findAll() {
		return viabilidadeRepository.findAll();
	}
	
	public Page<Viabilidade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return viabilidadeRepository.findAll(pageRequest);
	}	
	
	public Viabilidade fromDTO(ViabilidadeDTO objDto) {
		return new Viabilidade(null, objDto.getNome(), objDto.getEmail(), objDto.getTelefone(), objDto.getTelefone2(), objDto.getEndereco(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro());
	}
}
