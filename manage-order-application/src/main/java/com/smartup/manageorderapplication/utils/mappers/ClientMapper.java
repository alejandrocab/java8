package com.smartup.manageorderapplication.utils.mappers;

import org.springframework.stereotype.Component;

import com.smartup.manageorderapplication.dto.ClientDto;
import com.smartup.manageorderapplication.entities.Client;

@Component
public class ClientMapper {

	public Client dto2Entity (ClientDto inDto) {
		return Client.builder()
				.id(inDto.getId())
				.dni(inDto.getDni())
				.name(inDto.getName())
				.lastname(inDto.getLastname())
				.email(inDto.getEmail()).build();
	}
	
	public ClientDto entity2Dto (Client entity) {
		return ClientDto.builder()
				.id(entity.getId())
				.dni(entity.getDni())
				.name(entity.getName())
				.lastname(entity.getLastname())
				.email(entity.getEmail()).build();
	}
}
