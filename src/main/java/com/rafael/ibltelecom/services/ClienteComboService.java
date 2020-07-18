package com.rafael.ibltelecom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.ibltelecom.domain.Bairro;
import com.rafael.ibltelecom.domain.ClienteCombo;
import com.rafael.ibltelecom.domain.Combo;
import com.rafael.ibltelecom.domain.Endereco;
import com.rafael.ibltelecom.domain.Logradouro;
import com.rafael.ibltelecom.domain.enums.FormaPagamento;
import com.rafael.ibltelecom.domain.enums.TipoCliente;
import com.rafael.ibltelecom.domain.enums.Vencimento;
import com.rafael.ibltelecom.dto.ClienteComboDTO;
import com.rafael.ibltelecom.dto.ClienteNewComboDTO;
import com.rafael.ibltelecom.repositories.ClienteComboRepository;
import com.rafael.ibltelecom.services.exceptions.DataIntegrityException;
import com.rafael.ibltelecom.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteComboService {

	@Autowired
	private ClienteComboRepository clienteRepository;
	
	public ClienteCombo find(Integer id) {
		Optional<ClienteCombo> cliente = clienteRepository.findById(id);
		
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado id: " + ", Tipo: " + ClienteCombo.class.getName()));
	}
	
	@Transactional
	public ClienteCombo insert(ClienteCombo obj) {
		obj.setId(null);
		return clienteRepository.save(obj);
	}
	
	
	public ClienteCombo update(ClienteCombo obj) {
		ClienteCombo newObj = find(obj.getId());
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Cliente que possui Combos associados a ele.");
		}
	}
	
	public List<ClienteCombo> findAll() {
		return clienteRepository.findAll();
	}
	
	public Page<ClienteCombo> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public ClienteCombo fromDTO(ClienteComboDTO objDto) {
		return new ClienteCombo(objDto.getId(), objDto.getNome(), null, null, null, objDto.getEmail(), null, null, null, null, null);
	}
	
	
	public ClienteCombo fromDTO(ClienteNewComboDTO objDto) {		
		Bairro bairro = new Bairro(objDto.getBairroId(), null);
		Logradouro logradouro = new Logradouro(objDto.getLogradouroId(), null, bairro);
		Combo combos = new Combo(objDto.getCombosId(), null, null, null, null, null);
		ClienteCombo cli = new ClienteCombo(null, objDto.getNome(), objDto.getRg(), objDto.getCpfOuCnpj(), objDto.getDataNascimento(), objDto.getEmail(), logradouro, TipoCliente.toEnum(objDto.getTipo()), FormaPagamento.toEnum(objDto.getFormaPagamento()), Vencimento.toEnum(objDto.getVencimento()), combos);
		Endereco end = new Endereco(null, objDto.getNumero(), objDto.getComplemento(), objDto.getCep(), null, cli);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		
		return cli;
	}
	
	private void updateData(ClienteCombo newObj, ClienteCombo obj) {
		if (obj.getNome() != null) {
			newObj.setNome(obj.getNome());
		}
		if (obj.getEmail() != null) {
			newObj.setEmail(obj.getEmail());
		}
	}

}
