package com.dawes.serviciosImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.modelo.UsuarioVO;
import com.dawes.repository.IUsuarioRepository;
import com.dawes.servicios.IUsuarioServicio;

@Service
public class UsuarioServicioImpl implements IUsuarioServicio {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public Optional<UsuarioVO> findById(Integer id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public UsuarioVO save(UsuarioVO usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Optional<UsuarioVO> findByEmail(String email) {
		
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public List<UsuarioVO> findAll() {
		
		return usuarioRepository.findAll();
	}
	
	

}
