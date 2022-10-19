package br.com.cadastro.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RespostaPadrao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1328998689773458275L;
	private String mensagem;

}
