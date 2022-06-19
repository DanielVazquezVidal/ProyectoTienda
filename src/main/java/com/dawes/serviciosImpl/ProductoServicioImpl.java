package com.dawes.serviciosImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.modelo.ProductoVO;
import com.dawes.repository.IProductoRepository;
import com.dawes.servicios.ProductoServicio;

@Service
public class ProductoServicioImpl implements ProductoServicio {

	@Autowired
	private IProductoRepository productorepository;
	
	@Override
	public ProductoVO save(ProductoVO producto) {
		
		return productorepository.save(producto) ;
	}

	@Override
	public Optional<ProductoVO> get(Integer id) {
		
		return productorepository.findById(id);
	}

	@Override
	public void update(ProductoVO producto) {
		productorepository.save(producto);
		
	}

	@Override
	public void delete(Integer id) {
		productorepository.deleteById(id);
		
	}

	@Override
	public List<ProductoVO> findAll() {
		
		return productorepository.findAll();
	}

}
