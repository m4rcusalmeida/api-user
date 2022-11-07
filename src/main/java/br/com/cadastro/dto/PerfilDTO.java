package br.com.cadastro.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.cadastro.models.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 6159858102884572140L;

	private Long id;
	private String nome;

	public static PerfilDTO convert(Perfil p) {
		return new PerfilDTO(p.getId(), p.getNome());
	}

	public static List<PerfilDTO> convert(List<Perfil> perfis) {
		return perfis.stream().map(PerfilDTO::convert).collect(Collectors.toList());
	}

}
