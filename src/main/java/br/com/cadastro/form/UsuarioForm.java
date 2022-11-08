package br.com.cadastro.form;

import java.io.Serializable;
import java.util.List;

import br.com.cadastro.dto.EnderecoDTO;
import br.com.cadastro.dto.PerfilDTO;
import br.com.cadastro.dto.TelefoneDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioForm implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -2516956966474314108L;

	private Long id;
	private String nome;
	private String email;
	private String senha;
	private List<EnderecoDTO> endereco;
	private List<TelefoneDTO> telefone;
	private List<PerfilDTO> perfis;

}
