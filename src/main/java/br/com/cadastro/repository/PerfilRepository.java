package br.com.cadastro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cadastro.models.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	Optional<Perfil> findByNomeIgnoreCase(String nome);

	Perfil findByUsuarios_Nome(String nomeCurso);
}
