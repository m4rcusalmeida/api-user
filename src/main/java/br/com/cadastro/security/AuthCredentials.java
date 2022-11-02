package br.com.cadastro.security;

import lombok.Data;

@Data
public class AuthCredentials {
	private String email;
	private String senha;
}
