package com.dawes.modelo;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios")
public class UsuarioVO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(length=20,unique=true)
	private int idusuario;
	
	@Column(length=45)
	private String nombre;
	
	@Column(length=45)
	private String direccion;
	
	@Column(length=60)
	private String password;
	
	@Column(length=60)
	private String email;
	
	
	@Column(length=60)
	private String tipo;
	
	@ManyToOne
	private RolVO rol;
	
	
	@OneToMany(mappedBy="usuario")
	private List<VentaVO> ventas;

	
}
