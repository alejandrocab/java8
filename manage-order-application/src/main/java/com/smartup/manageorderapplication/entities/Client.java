package com.smartup.manageorderapplication.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "dni", length = 9, nullable = false)
	private String dni;
	
	@Column(name="name", nullable = false)
	private String name;
	@Column(name="lastname", nullable = false)
	private String lastname;
	@Column(name="email", nullable = true)
	private String email;
	
	@Column(name = "ts", nullable = false)
	private Timestamp ts;
	
	@PrePersist
	@PreUpdate
	public void updateTs() {
		ts = new Timestamp(System.currentTimeMillis());
	}
}
