package com.dawes.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dawes.modelo.UsuarioVO;


public interface IUsuarioServicio {

	List<UsuarioVO> findAll();
	Optional<UsuarioVO> findById(Integer id);
	UsuarioVO save (UsuarioVO usuario);
	Optional<UsuarioVO> findByEmail(String email);
}
