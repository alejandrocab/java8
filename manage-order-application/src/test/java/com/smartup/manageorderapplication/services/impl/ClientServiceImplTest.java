package com.smartup.manageorderapplication.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.smartup.manageorderapplication.dto.ClientDto;
import com.smartup.manageorderapplication.entities.Client;
import com.smartup.manageorderapplication.repositories.ClientRepository;
import com.smartup.manageorderapplication.utils.mappers.ClientMapper;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

	@Mock
	private ClientRepository repository;
	
	@Mock
	private ClientMapper clientMapper;
	
	@InjectMocks
	private ClientServiceImpl service;
	
	@Test
	public void shouldCreateClientWhenInputValid() {
		Client entity = Client.builder().build();
		ClientDto dto = ClientDto.builder().build();
		when(repository.save(Mockito.any(Client.class))).thenReturn(entity);
		when(clientMapper.dto2Entity(Mockito.any(ClientDto.class))).thenReturn(entity);
		when(clientMapper.entity2Dto(Mockito.any(Client.class))).thenReturn(dto);
		
		ClientDto response = service.createClient(dto);
		
		assertEquals(dto.getId(), response.getId());
	}
	
	@Test
	public void shouldGetClients() {
		List<Client> listEntities = Arrays.asList(Client.builder().build());
		ClientDto emptyDto = ClientDto.builder().build();
		when(repository.findAll()).thenReturn(listEntities);
		when(clientMapper.entity2Dto(Mockito.any(Client.class))).thenReturn(emptyDto);
		
		List<ClientDto> response = service.getClients();
		
		assertEquals(listEntities.size(), response.size());
	}
	
	@Test
	public void shouldGetClient() {
		Long idClientFound = 1L;
		Optional<Client> entityFound = Optional.of(Client.builder().id(idClientFound).build());
		ClientDto clientDto = ClientDto.builder().id(idClientFound).build();
		when(repository.findById(idClientFound)).thenReturn(entityFound);
		when(clientMapper.entity2Dto(Mockito.any(Client.class))).thenReturn(clientDto);
		ClientDto responseEntityFound = service.getClient(idClientFound);
		assertEquals(idClientFound, responseEntityFound.getId());
	}
	
	@Test
	public void shouldThrowRunTimeExceptionWhenIdClientNotFound() {
		Long idClientNotFound = 0L;
		when(repository.findById(idClientNotFound)).thenThrow(RuntimeException.class);
		assertThrows(RuntimeException.class, () -> service.getClient(idClientNotFound));
	}
}
