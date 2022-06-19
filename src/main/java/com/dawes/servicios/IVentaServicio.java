package com.dawes.servicios;

import java.util.List;
import java.util.Optional;

import com.dawes.modelo.UsuarioVO;
import com.dawes.modelo.VentaVO;

public interface IVentaServicio {

	VentaVO save(VentaVO venta);
	List<VentaVO> findAll();
	String generarNumeroVenta();
	List<VentaVO> findByUsuario(UsuarioVO usuario);
	Optional<VentaVO> findById(Integer id);
	
}
