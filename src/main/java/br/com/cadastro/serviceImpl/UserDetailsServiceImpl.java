package br.com.cadastro.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cadastro.models.Usuario;
import br.com.cadastro.repository.UsuarioRepository;
import br.com.cadastro.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = userRepo.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("%s não encontrado!", username)));
		return new UserDetailsImpl(usuario);
	}

}
