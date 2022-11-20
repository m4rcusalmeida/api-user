package br.com.cadastro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cadastro.models.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
//	@Query("SELECT obj FROM Telefone obj WHERE usuario_id = :id")
//	List<Telefone> findByUsuario(Long id);
}
