package com.rafael.ibltelecom.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rafael.ibltelecom.domain.Categoria;
import com.rafael.ibltelecom.domain.Plano;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Integer>{

	@Query("SELECT DISTINCT obj FROM Plano obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Plano> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
	
	//@Transactional(readOnly=true)
	//Page<Planos> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
}
