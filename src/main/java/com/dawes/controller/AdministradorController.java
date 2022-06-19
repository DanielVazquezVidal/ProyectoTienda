package com.dawes.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dawes.modelo.ProductoVO;
import com.dawes.modelo.VentaVO;
import com.dawes.servicios.IUsuarioServicio;
import com.dawes.servicios.IVentaServicio;
import com.dawes.servicios.ProductoServicio;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private ProductoServicio productoServicio;
	
	@Autowired
	private IUsuarioServicio usuarioServicio;
	
	@Autowired
	private IVentaServicio ventaServicio;
	
	private Logger logg= LoggerFactory.getLogger(AdministradorController.class); 
	
	@GetMapping("")
	public String home(Model modelo) {
		
		List<ProductoVO> productos=productoServicio.findAll();
		modelo.addAttribute("productos",productos);
		
		return "administrador/home";
	}
	
	@GetMapping("/usuarios")
	public String usuarios(Model modelo) {
		modelo.addAttribute("usuarios",usuarioServicio.findAll());
		
		return "administrador/usuarios";
	}
	
	@GetMapping("/ventas")
	public String ventas(Model modelo) {
		modelo.addAttribute("ventas",ventaServicio.findAll());
		
		return "administrador/ventas";
	}
	
	
	@GetMapping("/detalle/{id}")
	public String detalle(Model modelo, @PathVariable Integer id) {
		logg.info("Id de la venta {}",id);
		VentaVO venta=ventaServicio.findById(id).get();
		
		modelo.addAttribute("factura",venta.getLineafactura());
		
	return "administrador/lineafactura";	
	} 
	
	
	
}
