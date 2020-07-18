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
import com.rafael.ibltelecom.domain.ClientePlano;
import com.rafael.ibltelecom.domain.Endereco;
import com.rafael.ibltelecom.domain.Logradouro;
import com.rafael.ibltelecom.domain.Plano;
import com.rafael.ibltelecom.domain.enums.FormaPagamento;
import com.rafael.ibltelecom.domain.enums.TipoCliente;
import com.rafael.ibltelecom.domain.enums.Vencimento;
import com.rafael.ibltelecom.dto.ClienteDTO;
import com.rafael.ibltelecom.dto.ClienteNewDTO;
import com.rafael.ibltelecom.repositories.ClientePlanoRepository;
import com.rafael.ibltelecom.services.exceptions.DataIntegrityException;
import com.rafael.ibltelecom.services.exceptions.ObjectNotFoundException;


@Service
public class ClientePlanoService {

	@Autowired
	private ClientePlanoRepository clienteRepository;
	
	public ClientePlano find(Integer id) {
		Optional<ClientePlano> cliente = clienteRepository.findById(id);
		
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado id: " + ", Tipo: " + ClientePlano.class.getName()));
	}
	
	@Transactional
	public ClientePlano insert(ClientePlano obj) {
		obj.setId(null);
		return clienteRepository.save(obj);
	}
	
	
	public ClientePlano update(ClientePlano obj) {
		ClientePlano newObj = find(obj.getId());
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Cliente que possui planos associados a ele.");
		}
	}
	
	public List<ClientePlano> findAll() {
		return clienteRepository.findAll();
	}
	
	public Page<ClientePlano> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public ClientePlano fromDTO(ClienteDTO objDto) {
		return new ClientePlano(objDto.getId(), objDto.getNome(), null, null, null, objDto.getEmail(), null, null, null, null, null);
	}
	
	
	public ClientePlano fromDTO(ClienteNewDTO objDto) {		
		Bairro bairro = new Bairro(objDto.getBairroId(), null);
		Logradouro logradouro = new Logradouro(objDto.getLogradouroId(), null, bairro);
		Plano plano = new Plano(objDto.getPlanosId(), null, null, null, null, null, null, null, null);
		ClientePlano cli = new ClientePlano(null, objDto.getNome(), objDto.getRg(), objDto.getCpfOuCnpj(), objDto.getDataNascimento(), objDto.getEmail(), TipoCliente.toEnum(objDto.getTipo()), logradouro, plano,FormaPagamento.toEnum(objDto.getFormaPagamento()), Vencimento.toEnum(objDto.getVencimento()));
		Endereco end = new Endereco(null, objDto.getNumero(), objDto.getComplemento(), objDto.getCep(), cli, null);
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
	
	private void updateData(ClientePlano newObj, ClientePlano obj) {
		if (obj.getNome() != null) {
			newObj.setNome(obj.getNome());
		}
		if (obj.getEmail() != null) {
			newObj.setEmail(obj.getEmail());
		}
	}

}
