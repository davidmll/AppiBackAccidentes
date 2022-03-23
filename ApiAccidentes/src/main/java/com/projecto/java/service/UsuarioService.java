package com.projecto.java.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public interface UsuarioService {

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}