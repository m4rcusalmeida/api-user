package br.com.cadastro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.cadastro.dto.UsuarioDTO;
import br.com.cadastro.form.UsuarioForm;

public interface UsuarioService {

	UsuarioDTO save(UsuarioForm usuarioForm);

	UsuarioDTO findById(Long id);

	void delete(Long id);

	Page<UsuarioDTO> findAll(Pageable pageable);

	UsuarioDTO update(Long id, UsuarioForm usuarioForm);

	Page<UsuarioDTO> findByNome(String nome, Pageable pageable);
}
