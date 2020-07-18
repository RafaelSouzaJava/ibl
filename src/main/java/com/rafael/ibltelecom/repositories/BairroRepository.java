package com.rafael.ibltelecom.repositories;

import java.util.List;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.ibltelecom.domain.Bairro;

@EnableCaching
@Repository
public interface BairroRepository extends JpaRepository<Bairro, Integer>{

	@Transactional(readOnly=true)
	public List<Bairro> findAllByOrderByBairro();
}
