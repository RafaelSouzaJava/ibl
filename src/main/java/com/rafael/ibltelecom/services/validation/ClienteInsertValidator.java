package com.rafael.ibltelecom.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.rafael.ibltelecom.domain.ClientePlano;
import com.rafael.ibltelecom.domain.controllers.exception.FieldMessage;
import com.rafael.ibltelecom.domain.enums.TipoCliente;
import com.rafael.ibltelecom.dto.ClienteNewDTO;
import com.rafael.ibltelecom.repositories.ClientePlanoRepository;
import com.rafael.ibltelecom.services.validation.utils.BR;



public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	ClientePlanoRepository clienteRepository;
	
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		ClientePlano aux = clienteRepository.findByEmail(objDto.getEmail());
		
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		ClientePlano cpfaux = clienteRepository.findByCpfOuCnpj(objDto.getCpfOuCnpj());
		
		if (cpfaux != null) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF ou CNPJ já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
