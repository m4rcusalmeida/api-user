package br.com.cadastro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.cadastro.models.Usuario;
import br.com.cadastro.repository.UsuarioRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = userRepo.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("%s não existe", username)));
		return new UserDetailsImpl(usuario);
	}

}
