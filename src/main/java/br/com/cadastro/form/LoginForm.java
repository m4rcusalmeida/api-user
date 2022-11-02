package br.com.cadastro.form;

import java.io.Serializable;

public class LoginForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8329343528379143971L;
	private String email;
	private String senha;

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

}
