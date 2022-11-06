package br.com.cadastro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cadastro.models.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	Optional<Perfil> findByNomeIgnoreCase(String nome);
}
