package br.com.cadastro.models;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.cadastro.dto.EnderecoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -79860491528121302L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rua;
	private String numero;
	private String bairro;
	private String complemento;
	private String cidade;
	private String estado;

	public static Endereco convert(EnderecoDTO end) {
		return new Endereco(end.getId(), end.getRua(), end.getNumero(), end.getBairro(), end.getComplemento(),
				end.getCidade(), end.getEstado());
	}

	public static List<Endereco> convert(List<EnderecoDTO> ends) {
		if (ends != null) {
			return ends.stream().map(Endereco::convert).collect(Collectors.toList());
		}
		return null;
	}

}
