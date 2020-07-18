package com.rafael.ibltelecom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.rafael.ibltelecom.domain.Combo;
import com.rafael.ibltelecom.repositories.ComboRepository;
import com.rafael.ibltelecom.services.exceptions.DataIntegrityException;
import com.rafael.ibltelecom.services.exceptions.ObjectNotFoundException;

@Service
public class ComboService {

	@Autowired
	private ComboRepository comboRepository;	
	
	
	
	
	public Combo find(Integer id) {
		Optional<Combo> pedido = comboRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException("Combo não encontrado id: " + ", Tipo: " + Combo.class.getName()));
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			comboRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Combo que foi enviado ao cliente.");
		}
	}
	
	public List<Combo> findAll() {
		return comboRepository.findAll();
	}
	

	/*(public Page<Combo> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		List<CategoriaCombo> categorias = categoriaRepository.findAllById(ids);
		return comboRepository.findDistinctByNomeContainingAndCategoriaComboIn(nome, categorias, pageRequest);
	}*/
}
