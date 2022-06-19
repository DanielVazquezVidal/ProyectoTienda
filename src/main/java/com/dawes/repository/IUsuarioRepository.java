package com.dawes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.UsuarioVO;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioVO, Integer> {

	Optional<UsuarioVO> findByEmail(String email);

}
