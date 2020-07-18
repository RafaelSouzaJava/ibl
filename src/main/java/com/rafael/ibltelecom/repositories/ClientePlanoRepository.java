package com.rafael.ibltelecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.ibltelecom.domain.ClientePlano;

@Repository
public interface ClientePlanoRepository extends JpaRepository<ClientePlano, Integer>{

	@Transactional(readOnly = true)
	ClientePlano findByEmail(String email);
	
	@Transactional(readOnly = true)
	ClientePlano findByCpfOuCnpj(String cpfOuCnpj);
}
