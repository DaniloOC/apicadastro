package br.com.danilo.teste.apicadastro.dao;

import br.com.danilo.teste.apicadastro.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("FROM Pessoa p WHERE p.cpf = ?1")
    Optional<Pessoa> findByCpf(String cpf);
}
