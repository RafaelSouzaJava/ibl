package com.rafael.ibltelecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.ibltelecom.domain.ClienteCombo;

@Repository
public interface ClienteComboRepository extends JpaRepository<ClienteCombo, Integer>{

	@Transactional(readOnly = true)
	ClienteCombo findByEmail(String email);
	
	@Transactional(readOnly = true)
	ClienteCombo findByCpfOuCnpj(String cpfOuCnpj);
}
