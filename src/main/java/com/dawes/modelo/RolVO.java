package com.dawes.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles")
public class RolVO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(length=9, unique = true)
	private int idrol;
	
	@Column(length=45)
	private String tipo;
	
	@OneToMany(mappedBy="rol")
	private List<UsuarioVO> usuarios;


	
	
	
}
