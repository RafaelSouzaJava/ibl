package com.rafael.ibltelecom.repositories;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafael.ibltelecom.domain.Categoria;

@EnableCaching
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
