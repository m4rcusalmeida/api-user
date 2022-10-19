package br.com.cadastro.dto;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import br.com.cadastro.models.Perfil;
import br.com.cadastro.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -6222910911618650497L;

	private Long id;
	private String nome;
	private String email;
	private String endereco;
	private String telefone;
	private Perfil perfil;

	public static UsuarioDTO convert(Usuario usuario) {
		return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getEndereco(),
				usuario.getTelefone(), usuario.getPerfil());
	}

	public static Page<UsuarioDTO> convert(Page<Usuario> usuarios) {
		return usuarios.map(UsuarioDTO::convert);
	}

}
