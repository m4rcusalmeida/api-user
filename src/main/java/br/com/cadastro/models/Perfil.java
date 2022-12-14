package br.com.cadastro.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cadastro.dto.PerfilDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perfil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5902482218087943713L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "perfis")
	@JsonIgnore
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	public static Perfil convert(PerfilDTO per) {
		Perfil perfil = new Perfil();
		perfil.setId(per.getId());
		perfil.setNome(per.getNome());
		return perfil;
	}

	public static List<Perfil> convert(List<PerfilDTO> perfs) {
		if (perfs != null) {
			return perfs.stream().map(Perfil::convert).collect(Collectors.toList());
		}
		return null;
	}

}
