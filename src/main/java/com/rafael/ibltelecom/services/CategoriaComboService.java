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
import com.rafael.ibltelecom.domain.CategoriaCombo;
import com.rafael.ibltelecom.dto.CategoriaComboDTO;
import com.rafael.ibltelecom.repositories.CategoriaComboRepository;
import com.rafael.ibltelecom.services.exceptions.DataIntegrityException;
import com.rafael.ibltelecom.services.exceptions.ObjectNotFoundException;


@Service
public class CategoriaComboService {

	@Autowired
	private CategoriaComboRepository categoriaComboRepository;
	
	public CategoriaCombo find(Integer id) {
		Optional<CategoriaCombo> obj = categoriaComboRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrado! Id: " + id +", tipo: " + Categoria.class.getName()));
	}
	
	public CategoriaCombo insert(CategoriaCombo obj) {
		obj.setId(null);
		return categoriaComboRepository.save(obj);
	}
	
	public CategoriaCombo update(CategoriaCombo obj) {
		CategoriaCombo newObj = find(obj.getId());
		updateData(newObj, obj);
		return categoriaComboRepository.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		categoriaComboRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui planos");
		}
	}
	
	public List<CategoriaCombo> findAll() {
		return categoriaComboRepository.findAll();
	}
	
	public Page<CategoriaCombo> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return categoriaComboRepository.findAll(pageRequest);
	}
	
	public CategoriaCombo fromDTO(CategoriaComboDTO objDto) {
		return new CategoriaCombo(objDto.getId(), objDto.getNome());
	}
	
	private void updateData(CategoriaCombo newObj, CategoriaCombo obj) {
		if (obj.getNome() != null) {
			newObj.setNome(obj.getNome());
		}
	}

}
