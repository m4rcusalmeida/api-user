package br.com.cadastro.models;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.cadastro.dto.TelefoneDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Telefone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6251026422651773277L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numero;

	public static Telefone convert(TelefoneDTO tel) {
		return new Telefone(tel.getId(), tel.getNumero());
	}

	public static List<Telefone> convert(List<TelefoneDTO> tels) {
		if (tels != null) {
			return tels.stream().map(Telefone::convert).collect(Collectors.toList());
		}
		return null;
	}
}
