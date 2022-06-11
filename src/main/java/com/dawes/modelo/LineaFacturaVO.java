package com.dawes.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name="lineafacturas")
public class LineaFacturaVO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(length=20, unique=true)
	private int idlineafactura;
	private double precio;
	private String codigo;
	private LocalDate fecha;
	
	@ManyToOne
	private VentaVO venta;
	
	
	@OneToMany(mappedBy="factura")
	private List<ProductoVO> productos;
	
	
	
}
