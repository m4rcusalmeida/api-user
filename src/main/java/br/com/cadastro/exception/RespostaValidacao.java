package br.com.cadastro.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RespostaValidacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2353444334085572394L;
	private String mensagem;
	private String campo;
}
