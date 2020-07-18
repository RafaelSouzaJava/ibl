package com.rafael.ibltelecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafael.ibltelecom.domain.Viabilidade;

@Repository
public interface ViabilidadeRepository extends JpaRepository<Viabilidade, Integer>{

}
