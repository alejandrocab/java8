package com.smartup.manageorderapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartup.manageorderapplication.dto.ClientDto;
import com.smartup.manageorderapplication.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	private ClientService clientSrv;
	
	public ClientController(ClientService clientSrv) {
		this.clientSrv = clientSrv;
	}
	
	@GetMapping
	public ResponseEntity<List<ClientDto>> getClients() {
		List<ClientDto> response = clientSrv.getClients();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDto> getClient(@PathVariable(name = "id") Long id) {
		ClientDto response = clientSrv.getClient(id);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ClientDto> createClient(@RequestBody @Valid ClientDto inDto) {
		ClientDto response = clientSrv.createClient(inDto);
		return ResponseEntity.ok(response);
	}
}
