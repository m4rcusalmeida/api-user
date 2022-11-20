package br.com.cadastro.controllers;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/online")
public class OnlineController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3855562032063231602L;

	@GetMapping
	public ResponseEntity<?> online() {
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
