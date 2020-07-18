package com.rafael.ibltelecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafael.ibltelecom.domain.Combo;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Integer>{

}
