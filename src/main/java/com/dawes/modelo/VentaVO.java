package com.dawes.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="ventas")
public class VentaVO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(length=20, unique=true)
	private int idventa;
	private double importe;
	private double iva;
	private LocalDate fecha;
	
	
	@ManyToOne
	private UsuarioVO usuario;
	
	
	@OneToMany(mappedBy="venta")
	private List<LineaFacturaVO> facturas;
	
	
	
	
	
	
	
	
	
	
	
}
