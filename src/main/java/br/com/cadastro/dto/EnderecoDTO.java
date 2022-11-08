package br.com.cadastro.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.cadastro.models.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 5008071657973286399L;

	private Long id;
	private String rua;
	private String numero;
	private String bairro;
	private String complemento;
	private String cidade;
	private String estado;

	public EnderecoDTO(String rua, String numero, String bairro, String complemento, String cidade, String estado) {
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cidade = cidade;
		this.estado = estado;
	}

	public static EnderecoDTO convert(Endereco end) {
		return new EnderecoDTO(end.getId(), end.getRua(), end.getNumero(), end.getBairro(), end.getComplemento(),
				end.getCidade(), end.getEstado());
	}

	public static List<EnderecoDTO> convert(List<Endereco> ends) {
		return ends.stream().map(EnderecoDTO::convert).collect(Collectors.toList());
	}

}
