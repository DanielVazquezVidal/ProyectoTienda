package com.dawes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.UsuarioVO;
import com.dawes.modelo.VentaVO;

@Repository
public interface IVentaRepository extends JpaRepository<VentaVO,Integer> {

	List<VentaVO> findByUsuario(UsuarioVO usuario);
	
	
}
