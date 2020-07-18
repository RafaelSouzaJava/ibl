package com.rafael.ibltelecom.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rafael.ibltelecom.domain.Categoria;
import com.rafael.ibltelecom.domain.Post;
import com.rafael.ibltelecom.dto.PostDTO;
import com.rafael.ibltelecom.repositories.PostRepository;
import com.rafael.ibltelecom.services.exceptions.DataIntegrityException;
import com.rafael.ibltelecom.services.exceptions.ObjectNotFoundException;


@Service
public class PostService {

	@Autowired
	PostRepository postRepository;

	public Post find(Integer id) {
		Optional<Post> obj = postRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Post não encontrado! Id: " + id +", tipo: " + Categoria.class.getName()));
	}
	
	public Post insert(Post obj) {
		obj.setId(null);
		return postRepository.save(obj);
	}
	
	public Post update(Post obj) {
		Post newObj = find(obj.getId());
		updatePost(newObj, obj);
		return postRepository.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			postRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Post que possui planos");
		}
	}
	
	public List<Post> findAll() {
		return postRepository.findAll();
	}
	
	public Page<Post> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return postRepository.findAll(pageRequest);
	}
	
	public Post fromDTO(PostDTO objDto) {
		return new Post(objDto.getId(), objDto.getTitulo(), objDto.getAutor(), objDto.setData(LocalDate.now()), objDto.getTexto());
	}

	private void updatePost(Post newObj, Post obj) {
		if (obj.getTitulo() != null) {
			newObj.setTitulo(obj.getTitulo());
		}
		if (obj.getAutor() != null) {
			newObj.setAutor(obj.getAutor());
		}
		if (obj.getTexto() != null) {
			newObj.setTexto(obj.getTexto());
		}
	}
}
