package com.dawes.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name="productos")
public class ProductoVO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int idproducto;
	private String nombre;
	private double precio;
	private String tallaje;
	private String tiposuela;
	private String superficie;
	
	
	@ManyToOne
	private LineaFacturaVO factura;
	
	
	@ManyToOne
	private MarcaVO marca;
	
}
