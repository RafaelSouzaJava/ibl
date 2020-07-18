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
import com.rafael.ibltelecom.domain.Plano;
import com.rafael.ibltelecom.repositories.CategoriaRepository;
import com.rafael.ibltelecom.repositories.PlanoRepository;
import com.rafael.ibltelecom.services.exceptions.DataIntegrityException;
import com.rafael.ibltelecom.services.exceptions.ObjectNotFoundException;


@Service
public class PlanoService {

	@Autowired
	private PlanoRepository planosRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Plano find(Integer id) {
		Optional<Plano> pedido = planosRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException("Planos não encontrado id: " + ", Tipo: " + Plano.class.getName()));
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			planosRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Planos que foi enviado ao cliente.");
		}
	}
	
	public List<Plano> findAll() {
		return planosRepository.findAll();
	}
	
	public Page<Plano> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return planosRepository.search(nome, categorias, pageRequest);
	}
}
