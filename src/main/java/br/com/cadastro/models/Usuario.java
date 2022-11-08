package br.com.cadastro.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private List<Endereco> endereco;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private List<Telefone> telefone;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_perfil", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
			@JoinColumn(name = "perfil_id") })
	private List<Perfil> perfis;

	public static Usuario convert(UsuarioForm userForm) {
		Usuario user = new Usuario();
		user.setNome(userForm.getNome());
		user.setEmail(userForm.getEmail());
		user.setSenha(userForm.getSenha());
		user.setEndereco(Endereco.convert(userForm.getEndereco()));
		user.setTelefone(Telefone.convert(userForm.getTelefone()));
		return user;
	}

	public void addPerfil(Perfil perfil) {
		if (this.perfis == null) {
			this.perfis = new ArrayList<Perfil>();
		}
		this.perfis.add(perfil);
		perfil.getUsuarios().add(this);
	}

	public void removePerfil(Perfil perfil) {
		if (this.perfis == null) {
			this.perfis = new ArrayList<Perfil>();
		}
		this.perfis.remove(perfil);
		perfil.getUsuarios().remove(this);
	}

}
