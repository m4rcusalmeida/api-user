package br.com.cadastro.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cadastro.dto.PerfilDTO;
import br.com.cadastro.dto.UsuarioDTO;
import br.com.cadastro.form.UsuarioForm;
import br.com.cadastro.models.Endereco;
import br.com.cadastro.models.Perfil;
import br.com.cadastro.models.Telefone;
import br.com.cadastro.models.Usuario;
import br.com.cadastro.repository.PerfilRepository;
import br.com.cadastro.repository.UsuarioRepository;
import br.com.cadastro.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private PerfilRepository perfilRepo;

	@Override
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

	@Override
	public UsuarioDTO findById(Long id) {
		return UsuarioDTO.convert(
				usuarioRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Objeto não encontrado!")));
	}

	@Override
	public void delete(Long id) {
		usuarioRepo.deleteById(id);
	}

	@Override
	public Page<UsuarioDTO> findAll(Pageable pageable) {
		return UsuarioDTO.convert(usuarioRepo.findAll(pageable));
	}

	@Override
	public UsuarioDTO update(Long id, UsuarioForm usuarioForm) {
		Optional<Usuario> optional = usuarioRepo.findById(id);
		optional.get().setNome(usuarioForm.getNome());
		optional.get().setEmail(usuarioForm.getEmail());
		optional.get().setSenha(usuarioForm.getSenha());
		optional.get().setEndereco(Endereco.convert(usuarioForm.getEndereco()));
		optional.get().setTelefone(Telefone.convert(usuarioForm.getTelefone()));
		List<Perfil> perfilUsuario = new ArrayList<Perfil>();
		usuarioForm.getPerfis().forEach(p -> {
			Optional<Perfil> optional2 = perfilRepo.findByNomeIgnoreCase(p.getNome());
			perfilUsuario.add(
					optional2.orElseThrow(() -> new NoSuchElementException(p.getNome() + " não é um perfil válido")));
		});
		if (perfilUsuario.size() <= perfilRepo.findAll().size()) {
			optional.get().setPerfis(perfilUsuario);

		}
		return UsuarioDTO.convert(usuarioRepo.save(optional.get()));
	}

	@Override
	public Page<UsuarioDTO> findByNome(String nome, Pageable pageable) {
		return UsuarioDTO.convert(usuarioRepo.findByNomeContainingIgnoreCase(nome, pageable));
	}

	@Override
	public List<PerfilDTO> findByPerfilUsuario(Long id) {
		Optional<Usuario> optional = usuarioRepo.findById(id);
		List<Perfil> perfis = new ArrayList<Perfil>();
		if (optional.isPresent()) {
			perfis = optional.get().getPerfis();
		}
		return PerfilDTO.convert(perfis);
	}

}
