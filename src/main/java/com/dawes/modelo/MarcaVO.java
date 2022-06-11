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
@Table(name="marcas")
public class MarcaVO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(length=20, unique=true)
	private int idmarca;
	private String nombrefabricante;
	private String telefono;
	
	
	@OneToMany(mappedBy="marca")
	private List<ProductoVO> productos;
}
