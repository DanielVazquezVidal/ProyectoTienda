package com.dawes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dawes.modelo.RolVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.modelo.VentaVO;
import com.dawes.servicios.IUsuarioServicio;
import com.dawes.servicios.IVentaServicio;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	
	private final Logger logger= LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private IUsuarioServicio usuarioServicio;
	
	@Autowired
	private IVentaServicio ventaServicio;
	
	
	BCryptPasswordEncoder passEncode= new BCryptPasswordEncoder();
	
	// /usuario/registro
	@GetMapping("/registro")
	public String create() {
		return "usuario/registro";
	}
	
	
	@PostMapping("/save")
	public String save(UsuarioVO usuario) {
		logger.info("Usuario registro: {}", usuario);
		/*RolVO rol1=new RolVO(1,"ADMIN",new ArrayList<UsuarioVO>());*/
		/*usuario.setRol(rol1);*/
		usuario.setTipo("USER");
		usuario.setPassword(passEncode.encode(usuario.getPassword()));
		usuarioServicio.save(usuario);
		
		return "redirect:/";
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "usuario/login";
	}
	
	
	@GetMapping("/acceder")
	public String acceder(UsuarioVO usuario, HttpSession session) {
		logger.info("Accesos : {}", usuario);
		
		Optional<UsuarioVO> user=usuarioServicio.findById(Integer.parseInt(session.getAttribute("idusuario").toString()));
		//logger.info("Usuario de db: {}", user.get());
		
		if(user.isPresent()) {
			session.setAttribute("idusuario", user.get().getIdusuario());
			if(user.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador";
			}else {
				return "redirect:/";
			}
			
		}else {
			logger.info("Usuario no existe");
		}
		
		return "redirect:/";
	}
	
	
	@GetMapping("/compras")
	public String obtenerCompras(HttpSession session, Model modelo) {
		
		modelo.addAttribute("sesion",session.getAttribute("idusuario"));
		UsuarioVO usuario=usuarioServicio.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		List<VentaVO> ventas=ventaServicio.findByUsuario(usuario);
		
		modelo.addAttribute("ventas",ventas);
		
		return "usuario/compras";
	}
	
	
	@GetMapping("/detalle/{id}")
	public String detalleCompra(@PathVariable Integer id, HttpSession session, Model modelo) {
		logger.info("Id de la venta: {}", id);
		Optional<VentaVO> venta= ventaServicio.findById(id);
		
		modelo.addAttribute("factura", venta.get().getLineafactura());
		
		//session
		modelo.addAttribute("sesion",session.getAttribute("idusuario"));
		
		return "usuario/detallecompra";
	}
	
	
	@GetMapping("/cerrar")
	public String cerrarSesion(HttpSession session) {
		session.removeAttribute("idusuario");
		
		return "redirect:/";
	}
	
	
	
	
	
	
	
}
