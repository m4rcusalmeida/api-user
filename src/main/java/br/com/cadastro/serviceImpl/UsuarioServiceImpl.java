package br.com.cadastro.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cadastro.dto.PerfilDTO;
import br.com.cadastro.dto.TelefoneDTO;
import br.com.cadastro.dto.UsuarioDTO;
import br.com.cadastro.form.UsuarioForm;
import br.com.cadastro.models.Endereco;
import br.com.cadastro.models.Perfil;
import br.com.cadastro.models.Telefone;
import br.com.cadastro.models.Usuario;
import br.com.cadastro.repository.EnderecoRepository;
import br.com.cadastro.repository.PerfilRepository;
import br.com.cadastro.repository.UsuarioRepository;
import br.com.cadastro.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private PerfilRepository perfilRepo;
	@Autowired
	private EnderecoRepository enderecoRepo;



	public UsuarioDTO save(UsuarioForm usuarioForm) {
		Usuario user = Usuario.convert(usuarioForm);
		user.setId(null);
		List<Perfil> perfis = new ArrayList<Perfil>();
		if (usuarioForm.getPerfis() != null) {
			usuarioForm.getPerfis().forEach(p -> {
				Optional<Perfil> perfil = perfilRepo.findByNomeIgnoreCase(p.getNome());
				if (perfil.isPresent()) {
					perfis.add(perfil.get());
				} else {
					throw new NoSuchElementException(p.getNome() + " não é um perfil válido");
				}

			});
		}

		user.setPerfis(perfis);
		return UsuarioDTO.convert(usuarioRepo.save(user));
	}


	public UsuarioDTO findById(Long id) {
		return UsuarioDTO.convert(usuarioRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não existe usuário com o ID! " + id)));
	}


	public void delete(Long id) {
		usuarioRepo.deleteById(id);
	}


	public Page<UsuarioDTO> findAll(Pageable pageable) {
		return UsuarioDTO.convert(usuarioRepo.findAll(pageable));
	}


	public UsuarioDTO update(Long id, UsuarioForm usuarioForm) {
		Usuario optional = usuarioRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não existe usuário com o ID! " + id));
		optional.setNome(usuarioForm.getNome());
		optional.setEmail(usuarioForm.getEmail());
		optional.setSenha(usuarioForm.getSenha());
		List<Endereco> enderecos = new ArrayList<Endereco>();
		if (usuarioForm.getEndereco() != null && usuarioForm.getEndereco().size() <= 2) {
			if (optional.getEndereco().size() <= 2) {
				usuarioForm.getEndereco().forEach(endereco -> {
					if (endereco.getId() != null) {
						Endereco end = enderecoRepo.findById(endereco.getId()).orElseThrow(
								() -> new NoSuchElementException("ID informado não existe na base de dados"));
						end.setRua(endereco.getRua());
						end.setNumero(endereco.getNumero());
						end.setBairro(endereco.getBairro());
						end.setComplemento(endereco.getComplemento());
						end.setCidade(endereco.getCidade());
						end.setEstado(endereco.getEstado());
						enderecos.add(end);
					} else {
						Endereco e = new Endereco();
						e.setRua(endereco.getRua());
						e.setNumero(endereco.getNumero());
						e.setBairro(endereco.getBairro());
						e.setComplemento(endereco.getComplemento());
						e.setCidade(endereco.getCidade());
						e.setEstado(endereco.getEstado());
						enderecos.add(e);
					}

				});
				optional.setEndereco(enderecos);
			}
		} else {
			throw new NoSuchElementException("Só é permitido cadastrar 2 endereços por usuário");
		}
		optional.setTelefone(Telefone.convert(usuarioForm.getTelefone()));
		List<Perfil> perfilUsuario = new ArrayList<Perfil>();
		usuarioForm.getPerfis().forEach(p -> {
			Optional<Perfil> optional2 = perfilRepo.findByNomeIgnoreCase(p.getNome());
			perfilUsuario.add(
					optional2.orElseThrow(() -> new NoSuchElementException(p.getNome() + " não é um perfil válido")));
		});
		if (perfilUsuario.size() <= perfilRepo.findAll().size()) {
			optional.setPerfis(perfilUsuario);

		}
		return UsuarioDTO.convert(usuarioRepo.save(optional));
	}


	public Page<UsuarioDTO> findByNome(String nome, Pageable pageable) {
		return UsuarioDTO.convert(usuarioRepo.findByNomeContainingIgnoreCase(nome, pageable));
	}


	public List<PerfilDTO> findByPerfilUsuario(Long id) {
		Optional<Usuario> optional = usuarioRepo.findById(id);
		List<Perfil> perfis = new ArrayList<Perfil>();
		if (optional.isPresent()) {
			perfis = optional.get().getPerfis();
		}
		return PerfilDTO.convert(perfis);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepo
				.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Login Não Encontrado."));


		return User
				.builder()
				.username(usuario.getEmail())
				.password(usuario.getSenha())
				.roles("USER")
				.build();
	}

}
