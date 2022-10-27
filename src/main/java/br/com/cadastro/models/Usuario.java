package br.com.cadastro.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.UniqueElements;

import br.com.cadastro.form.UsuarioForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6419935343004749009L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "campo obrigatório!")
	private String nome;

	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	private String senha;
	private String endereco;
	private String telefone;
	@Enumerated(EnumType.STRING)
	private Perfil perfil;

	public static Usuario convert(UsuarioForm userForm) {
		return new Usuario(userForm.getId(), userForm.getNome(), userForm.getEmail(), userForm.getSenha(),
				userForm.getEndereco(), userForm.getTelefone(), userForm.getPerfil());
	}

}
