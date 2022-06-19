package com.dawes.controller;

import java.io.IOException;
import java.util.Optional;

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
import org.springframework.web.multipart.MultipartFile;
import com.dawes.modelo.ProductoVO;
import com.dawes.servicios.IUsuarioServicio;
import com.dawes.servicios.ProductoServicio;
import com.dawes.serviciosImpl.UploadFileService;



@Controller
@RequestMapping("/productos")
public class ProductoController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoServicio productoServicio;
	
	@Autowired
	private UploadFileService upload;
	
	@Autowired
	private IUsuarioServicio usuarioServicio;
	
	@GetMapping("")
	public String show(Model modelo) {
		modelo.addAttribute("productos", productoServicio.findAll());
		return "productos/show";
	}
	
	@GetMapping("/create")
	public String create() {
		return "productos/create";
	}
	
	
	@PostMapping("/save")
	public String save(ProductoVO producto,@RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
		LOGGER.info("Este es el objeto producto de la vista {}",producto);
		
		//Usuario u = new Usuario(1,"","","","","","","","");
		//u=usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString() )).get();
		//producto.setUsuario(u);
		
		//imagen
		if(producto.getId()==0) {//Cuando se crea un producto
			String nombreImagen=upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}else {
			
			
		}
		
		productoServicio.save(producto);
		return "redirect:/productos";
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model modelo) {
		ProductoVO producto = new ProductoVO();
		Optional<ProductoVO> optionalProducto=productoServicio.get(id);
		producto=optionalProducto.get();
		
		LOGGER.info("Producto buscado: {}",producto);
		modelo.addAttribute("producto", producto);
		
		return "productos/edit";
	}
	
	@PostMapping("/update")
	public String update(ProductoVO producto, @RequestParam("img") MultipartFile file) throws IOException {
		
		if(file.isEmpty()) {//editamos el producto pero no cambiamos la imagen
			ProductoVO p=new ProductoVO();
			p=productoServicio.get(producto.getId()).get();
			producto.setImagen(p.getImagen());
		}else {//Cuando se edita tambien la imagen 
			
			ProductoVO p = new ProductoVO();
			p=productoServicio.get(producto.getId()).get();
			
			//Eliminar cuando no sea la imagen por defecto
			if(!p.getImagen().equals("default.jpg")) {
				upload.deleteImage(p.getImagen());
			}
			/*producto.setMarca(p.getMarca());*/
			String nombreImagen=upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}
		
		productoServicio.update(producto);
		return "redirect:/productos";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		
		ProductoVO p = new ProductoVO();
		p=productoServicio.get(id).get();
		
		//Eliminar cuando no sea la imagen por defecto
		if(!p.getImagen().equals("default.jpg")) {
			upload.deleteImage(p.getImagen());
		}
		
		productoServicio.delete(id);
		return "redirect:/productos";
		
	}
	
	
	
}
