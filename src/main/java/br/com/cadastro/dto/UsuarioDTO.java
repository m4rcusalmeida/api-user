package br.com.cadastro.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

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
	private List<EnderecoDTO> endereco;
	private List<TelefoneDTO> telefone;
	private List<PerfilDTO> perfis;

	public static UsuarioDTO convert(Usuario usuario) {
		return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(),
				EnderecoDTO.convert(usuario.getEndereco()), TelefoneDTO.convert(usuario.getTelefone()),
				PerfilDTO.convert(usuario.getPerfis()));
	}

	public static Page<UsuarioDTO> convert(Page<Usuario> usuarios) {
		return usuarios.map(UsuarioDTO::convert);
	}

}
