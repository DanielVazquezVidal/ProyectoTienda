package com.dawes.modelo;

import java.time.LocalDate;
import java.util.Date;
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
	
	@Column(unique=true)
	private Integer id;
	private String numero;
	private Date fechaCreacion;
	private Date fechaRecibida;
	private double total;
	
	@OneToMany(mappedBy="venta")
	private List<LineaFacturaVO> lineafactura;
	
	
	@OneToOne(mappedBy="factura")
	private ProductoVO productos;
	
	@ManyToOne
	private UsuarioVO usuario;
	
	
	
	
	
	
	
	
	
	
	
}
