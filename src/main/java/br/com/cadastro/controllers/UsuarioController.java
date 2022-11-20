package br.com.cadastro.controllers;

import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import br.com.cadastro.serviceImpl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cadastro.dto.PerfilDTO;
import br.com.cadastro.dto.TelefoneDTO;
import br.com.cadastro.dto.UsuarioDTO;
import br.com.cadastro.form.UsuarioForm;
import br.com.cadastro.service.UsuarioService;

@RequestMapping("/usuario")
@RestController
@CrossOrigin("*")
public class UsuarioController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1111465893422640414L;

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
		UsuarioDTO usuarioDTO = usuarioService.findById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(usuarioDTO);
	}

	@GetMapping
	public ResponseEntity<Page<UsuarioDTO>> findAll(@RequestParam(name = "nome", required = false) String nome,
			@PageableDefault(sort = "id", page = 0, size = 10, direction = Direction.ASC) Pageable pageable) {
		if (Objects.nonNull(nome)) {
			return ResponseEntity.ok().body(usuarioService.findByNome(nome, pageable));
		}
		return ResponseEntity.ok().body(usuarioService.findAll(pageable));
	}

	@GetMapping("/{id}/perfil")
	public ResponseEntity<List<PerfilDTO>> findByPerfil(@PathVariable Long id) {
		return ResponseEntity.ok().body(usuarioService.findByPerfilUsuario(id));
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioForm usuarioForm) {
		UsuarioDTO usuarioDTO = usuarioService.save(usuarioForm);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(usuarioDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioForm usuarioForm) {
		UsuarioDTO usuarioDTO = usuarioService.update(id, usuarioForm);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(usuarioDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(usuarioDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		usuarioService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
