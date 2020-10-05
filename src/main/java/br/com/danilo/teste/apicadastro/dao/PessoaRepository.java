package br.com.danilo.teste.apicadastro.dao;

import br.com.danilo.teste.apicadastro.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {}
