package br.com.cadastro.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.cadastro.models.Endereco;
import br.com.cadastro.models.Telefone;
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
		List<Endereco> listaEnderecos = new ArrayList<Endereco>();
		if(usuario.getEndereco() != null){
			listaEnderecos = usuario.getEndereco();
		}

		List<Telefone> listaTelefones = new ArrayList<>();
		if(usuario.getTelefone() != null){
			listaTelefones = usuario.getTelefone();
		}

		return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(),
				EnderecoDTO.convert(listaEnderecos), TelefoneDTO.convert(listaTelefones),
				PerfilDTO.convert(usuario.getPerfis()));
	}

	public static Page<UsuarioDTO> convert(Page<Usuario> usuarios) {
		return usuarios.map(UsuarioDTO::convert);
	}

}
