package com.rafael.ibltelecom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.ibltelecom.domain.Logradouro;

@Repository
@Transactional
public interface LogradouroRepository extends JpaRepository<Logradouro, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Logradouro obj WHERE obj.bairros.id = :bairrosId ORDER BY obj.logradouro")
	public List<Logradouro> findLogradouros(@Param("bairrosId") Integer bairros_id);
	
	@Transactional(readOnly=true)	
	@Query("SELECT p FROM Logradouro p WHERE p.bairros.bairro = :logradouro ORDER BY p.logradouro")
	public List<Logradouro> findNomeLogradouros(@Param("logradouro") String logradouro);
	
	@Transactional(readOnly=true)
	public List<Logradouro> findAllByOrderByLogradouro();
}
