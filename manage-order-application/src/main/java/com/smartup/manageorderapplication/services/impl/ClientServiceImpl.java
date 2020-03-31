package com.smartup.manageorderapplication.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartup.manageorderapplication.dto.ClientDto;
import com.smartup.manageorderapplication.entities.Client;
import com.smartup.manageorderapplication.repositories.ClientRepository;
import com.smartup.manageorderapplication.services.ClientService;
import com.smartup.manageorderapplication.utils.mappers.ClientMapper;

@Service
public class ClientServiceImpl implements ClientService{
	
	private ClientRepository clientRepository;
	private ClientMapper clientMapper;
	
	public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
		this.clientRepository=clientRepository;
		this.clientMapper=clientMapper;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ClientDto> getClients() {
		return clientRepository.findAll().stream().map(clientMapper::entity2Dto).collect(Collectors.toList());
	}
	
	@Override
	@Transactional(readOnly = true)
	public ClientDto getClient(Long id) {
		return clientRepository.findById(id).map(clientMapper::entity2Dto).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
	}

	@Override
	@Transactional(readOnly = false)
	public ClientDto createClient(ClientDto inDto) {
		inDto.setId(null);
		Client data = clientRepository.save(clientMapper.dto2Entity(inDto));
		return clientMapper.entity2Dto(data);
	}

	

}
