package com.dawes.serviciosImpl;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dawes.modelo.UsuarioVO;
import com.dawes.servicios.IUsuarioServicio;

@Service
public class UserDetailServicioImpl implements UserDetailsService {

	@Autowired
	private IUsuarioServicio usuarioServicio;
	
	//Para la encriptacion de la contraseña
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	HttpSession session;
	
	private Logger log=LoggerFactory.getLogger(UserDetailServicioImpl.class);
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("Este es el username");
		Optional<UsuarioVO> optionalUser=usuarioServicio.findByEmail(username);
		if(optionalUser.isPresent()) {
			log.info("Esto es el id del usuario {}",optionalUser.get().getIdusuario());
			session.setAttribute("idusuario", optionalUser.get().getIdusuario());
			UsuarioVO usuario=optionalUser.get();
			return User.builder().username(usuario.getNombre()).password(usuario.getPassword()).roles(usuario.getTipo()).build();
		}else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		
	}

}
