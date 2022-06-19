package com.dawes.serviciosImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.modelo.UsuarioVO;
import com.dawes.modelo.VentaVO;
import com.dawes.repository.IVentaRepository;
import com.dawes.servicios.IVentaServicio;

@Service
public class VentaServicioImpl implements IVentaServicio {

	@Autowired
	private IVentaRepository ventaRepository;
	
	@Override
	public VentaVO save(VentaVO venta) {
		
		return ventaRepository.save(venta);
	}

	@Override
	public List<VentaVO> findAll() {
		
		return ventaRepository.findAll();
	}
	
	
	public String generarNumeroVenta() {
		int numero=0;
		String numeroConcatenado="";
		
		List<VentaVO> ventas = findAll();
		
		List<Integer> numeros = new ArrayList<Integer>();
		
		ventas.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));
		
		if(ventas.isEmpty()) {
			numero=1;
		}else {
			numero=numeros.stream().max(Integer::compare).get();
			numero++;
		}
		
		if(numero<10) {
			numeroConcatenado="000000000"+String.valueOf(numero);
		}else if(numero<100) {
			numeroConcatenado="00000000"+String.valueOf(numero);
		}else if(numero<1000) {
			numeroConcatenado="0000000"+String.valueOf(numero);
		}else if(numero<10000) {
			numeroConcatenado="000000"+String.valueOf(numero);
		}//no voy a tener mas de 10 mil ventas sino seguiria añadiendo
		
		return numeroConcatenado;
	}

	@Override
	public List<VentaVO> findByUsuario(UsuarioVO usuario) {
		
		return ventaRepository.findByUsuario(usuario);
	}

	@Override
	public Optional<VentaVO> findById(Integer id) {
		
		return ventaRepository.findById(id);
	}

}
