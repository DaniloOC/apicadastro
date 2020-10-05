package br.com.danilo.teste.apicadastro.service;

import br.com.danilo.teste.apicadastro.dao.PessoaRepository;
import br.com.danilo.teste.apicadastro.models.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class PessoaService {

    private static final ExampleMatcher CPF_MATCHER = ExampleMatcher.matching()
            .withIgnorePaths("id")
            .withMatcher("cpf", ignoreCase());

    private final PessoaRepository repository;

    @Autowired
    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    @Transient
    public Pessoa salvar(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    public boolean existe(Pessoa pessoa) {
        return repository.exists(Example.of(pessoa, CPF_MATCHER));
    }
}
