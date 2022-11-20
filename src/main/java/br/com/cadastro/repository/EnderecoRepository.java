package br.com.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cadastro.models.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
