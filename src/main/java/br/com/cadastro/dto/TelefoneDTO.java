package br.com.cadastro.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.cadastro.models.Telefone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 315725567076688548L;

	private Long id;
	private String numero;

	public TelefoneDTO(String numero) {
		this.numero = numero;
	}

	public static TelefoneDTO convert(Telefone tel) {
		return new TelefoneDTO(tel.getId(), tel.getNumero());
	}

	public static List<TelefoneDTO> convert(List<Telefone> tels) {
		return tels.stream().map(TelefoneDTO::convert).collect(Collectors.toList());
	}

}
