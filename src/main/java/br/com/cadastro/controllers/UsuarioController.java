package br.com.cadastro.controllers;

import java.io.Serializable;
import java.net.URI;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cadastro.dto.UsuarioDTO;
import br.com.cadastro.form.UsuarioForm;
import br.com.cadastro.service.UsuarioService;

@RequestMapping("/usuario")
@RestController
public class UsuarioController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1111465893422640414L;

	@Autowired
	private UsuarioService usuarioService;

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

	@PostMapping
	public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioForm usuarioForm) {
		UsuarioDTO usuarioDTO = null;
		if (Objects.isNull(usuarioForm.getId())) {
			System.out.println(usuarioForm.getPerfil());
			usuarioDTO = usuarioService.save(usuarioForm);
		} else {
			usuarioDTO = usuarioService.update(usuarioForm.getId(), usuarioForm);
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(usuarioDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		usuarioService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
