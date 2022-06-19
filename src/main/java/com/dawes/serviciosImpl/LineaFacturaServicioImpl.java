package com.dawes.serviciosImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.modelo.LineaFacturaVO;
import com.dawes.repository.ILineaFacturaRepository;
import com.dawes.servicios.ILineaFacturaServicio;

@Service
public class LineaFacturaServicioImpl implements ILineaFacturaServicio {

	@Autowired
	private ILineaFacturaRepository lineaFacturaRepository;
	
	@Override
	public LineaFacturaVO save(LineaFacturaVO lineaFactura) {
		
		return lineaFacturaRepository.save(lineaFactura);
	}

}
