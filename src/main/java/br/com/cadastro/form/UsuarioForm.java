package br.com.cadastro.form;

import java.io.Serializable;

import br.com.cadastro.models.Perfil;
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
	private String endereco;
	private String telefone;
	private Perfil perfil;

}
