package com.rafael.ibltelecom.domain.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafael.ibltelecom.domain.Post;
import com.rafael.ibltelecom.dto.PostDTO;
import com.rafael.ibltelecom.services.PostService;


@RestController
@RequestMapping(value = "/posts")
public class PostController {

	@Autowired
	PostService postService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> find(@PathVariable Integer id) {
		Post obj = postService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PostDTO objDto) {
		Post obj = postService.fromDTO(objDto);
		obj = postService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PostDTO objDto, @PathVariable Integer id){
		Post obj = postService.fromDTO(objDto);
		obj.setId(id);
		obj = postService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		postService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PostDTO>> findAll() {
		List<Post> list = postService.findAll();
		List<PostDTO> listDto = list.stream().map(obj -> new PostDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<PostDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Post> list = postService.findPage(page, linesPerPage, orderBy, direction);
		Page<PostDTO> listDto = list.map(obj -> new PostDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
