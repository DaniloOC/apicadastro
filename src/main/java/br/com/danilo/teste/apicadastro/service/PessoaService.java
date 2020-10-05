package br.com.danilo.teste.apicadastro.service;

import br.com.danilo.teste.apicadastro.dao.PessoaRepository;
import br.com.danilo.teste.apicadastro.models.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    @Autowired
    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    @Transient
    public Pessoa salvar(Pessoa pessoa) {
        return repository.save(pessoa);
    }
}
