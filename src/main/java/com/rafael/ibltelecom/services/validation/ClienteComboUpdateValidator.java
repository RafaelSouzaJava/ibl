package com.rafael.ibltelecom.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.rafael.ibltelecom.domain.ClienteCombo;
import com.rafael.ibltelecom.domain.controllers.exception.FieldMessage;
import com.rafael.ibltelecom.dto.ClienteComboDTO;
import com.rafael.ibltelecom.repositories.ClienteComboRepository;



public class ClienteComboUpdateValidator implements ConstraintValidator<ClienteComboUpdate, ClienteComboDTO> {
	
	@Autowired
	ClienteComboRepository clienteRepository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClienteComboUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteComboDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		
		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		
		ClienteCombo aux = clienteRepository.findByEmail(objDto.getEmail());
		
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		
		
		

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
