package com.dawes.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.modelo.LineaFacturaVO;
import com.dawes.modelo.ProductoVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.modelo.VentaVO;
import com.dawes.servicios.ILineaFacturaServicio;
import com.dawes.servicios.IUsuarioServicio;
import com.dawes.servicios.IVentaServicio;
import com.dawes.servicios.ProductoServicio;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private final Logger log=LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ProductoServicio productoServicio;
	
	@Autowired
	private IUsuarioServicio usuarioServicio;
	
	@Autowired
	private IVentaServicio ventaServicio;
	
	
	@Autowired
	private ILineaFacturaServicio lineaFacturaServicio;
	
	//Para almacenar los detalles de la orden
	List<LineaFacturaVO> factura= new ArrayList<LineaFacturaVO>();
	
	//Datos de la venta
	VentaVO venta= new VentaVO();
	
	@GetMapping("")
	public String home(Model modelo, HttpSession session) {
		
		log.info("Sesion del usuario: {}",session.getAttribute("idusuario"));
		
		modelo.addAttribute("productos",productoServicio.findAll());
		
		//session
		modelo.addAttribute("sesion",session.getAttribute("idusuario"));
		
		
		return "usuario/home";
	}
	
	
	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id, Model modelo) {
		log.info("Id producto enviado como parámetro {}", id);
		ProductoVO producto= new ProductoVO();
		Optional<ProductoVO> productoOptional=productoServicio.get(id);
		producto=productoOptional.get();
		
		modelo.addAttribute("producto",producto);
		
		return "usuario/productohome";
	}
	
	
	
	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id,@RequestParam Integer cantidad,Model modelo) {
		LineaFacturaVO lineaFactura= new LineaFacturaVO();
		ProductoVO producto = new ProductoVO();
		double sumaTotal=0;
		
		Optional<ProductoVO> optionalProducto= productoServicio.get(id);
		log.info("Producto añadido: {}"+optionalProducto.get());
		log.info("Cantidad: {}",cantidad);
		producto=optionalProducto.get();
		
		lineaFactura.setCantidad(cantidad);
		lineaFactura.setPrecio(producto.getPrecio());
		lineaFactura.setNombre(producto.getNombre());
		lineaFactura.setTotal(producto.getPrecio()*cantidad);
		lineaFactura.setProducto(producto);
		
		//Validar que el msimo producto no se añada dos veces
		Integer idProducto=producto.getId();
		boolean ingresado=factura.stream().anyMatch(p -> p.getProducto().getId()==idProducto);
		
		if(!ingresado) {
			factura.add(lineaFactura);
		}
		
	
		
		sumaTotal=factura.stream().mapToDouble(dt->dt.getTotal()).sum();
		venta.setTotal(sumaTotal);
		modelo.addAttribute("cart",factura);
		modelo.addAttribute("venta", venta);
		
		
		
		
		return "usuario/carrito";
	}
	
	
	//Quitar un producto del carrito 
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Integer id, Model modelo) {
		
		//Lista nueva de productos
		List<LineaFacturaVO> facturasNueva=new ArrayList<LineaFacturaVO>();
		
		for(LineaFacturaVO lineafactura: factura ) {
			if(lineafactura.getProducto().getId()!=id) {
				facturasNueva.add(lineafactura);
			}
		}
		
		//poner la nueva factura con los productos restantes
		factura=facturasNueva;
		double sumaTotal=0;
		sumaTotal=factura.stream().mapToDouble(dt->dt.getTotal()).sum();
		
		venta.setTotal(sumaTotal);
		modelo.addAttribute("cart",factura);
		modelo.addAttribute("venta", venta);
		
		
		return "usuario/carrito";
	}
	
	
	@GetMapping("/getCart")
	public String getCart(Model modelo, HttpSession session) {
		
		
		modelo.addAttribute("cart",factura);
		modelo.addAttribute("venta", venta);
		
		//sesion
		modelo.addAttribute("sesion",session.getAttribute("idusuario"));
		
		return "/usuario/carrito";
	}
	
	
	@GetMapping("/order")
	public String order(Model modelo, HttpSession session) {
		
  		UsuarioVO usuario=usuarioServicio.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		
		modelo.addAttribute("cart",factura);
		modelo.addAttribute("venta", venta);
		modelo.addAttribute("usuario",usuario);
		
		
		return "usuario/resumenorden";
	}
	
	//guardar la venta
	@GetMapping("/saveVenta")
	public String saveOrder(HttpSession session) {
		Date fechaCreacion = new Date();
		venta.setFechaCreacion(fechaCreacion);
		venta.setNumero(ventaServicio.generarNumeroVenta());
		
		//usuario que va a hacer referencia a esa venta
		UsuarioVO usuario=usuarioServicio.findById( Integer.parseInt(session.getAttribute("idusuario").toString()) ).get();
		
		venta.setUsuario(usuario);
		ventaServicio.save(venta);
		
		//guardar la parte de la facturas
		for(LineaFacturaVO dt:factura) {
			dt.setVenta(venta);
			lineaFacturaServicio.save(dt);
		}
		
		//limpiar la lista y la venta
		venta = new VentaVO();
		factura.clear();
		
		return "redirect:/";
	}
	
	
	@PostMapping("/search")
	public String searchProduct(@RequestParam String nombre, Model modelo) {
		log.info("Nombre del producto: {}", nombre);
		List<ProductoVO> productos=productoServicio.findAll().stream().filter(p -> p.getNombre().contains(nombre)).collect(Collectors.toList());
		modelo.addAttribute("productos",productos);
		
		return "usuario/home";
	}
	
	
	
	
	
}
