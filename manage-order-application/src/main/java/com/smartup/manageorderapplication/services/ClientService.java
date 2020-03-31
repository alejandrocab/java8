package com.smartup.manageorderapplication.services;

import java.util.List;

import com.smartup.manageorderapplication.dto.ClientDto;

public interface ClientService {

	public List<ClientDto> getClients();
	public ClientDto getClient(Long id);
	public ClientDto createClient(ClientDto inDto);
}
