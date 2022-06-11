package com.dawes.servicios;

import java.util.Optional;

import com.dawes.modelo.ProductoVO;

public interface ProductoServicio {

	public ProductoVO save(ProductoVO producto);
	
	public Optional<ProductoVO> get(Integer id);
	
	public void update(ProductoVO producto);
	
	public void delete(Integer id);
}
