package com.smartup.manageorderapplication.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_EMPTY)
public class ClientDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	@NotNull(message = "{client.dni.notnull}")
	@Pattern(regexp = "\\d{8}[a-zA-Z]", message = "{client.dni.pattern}")
	private String dni;
	@NotNull(message = "{client.name.notnull}")
	@NotEmpty(message = "{client.name.notempty}")
	private String name;
	@NotNull(message = "{client.lastname.notnull}")
	@NotEmpty(message = "{client.lastname.notempty}")
	private String lastname;
	@Email(regexp = ".+@.+\\..+", message = "{client.email.pattern}")
	private String email;
}
