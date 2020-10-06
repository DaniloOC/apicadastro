package br.com.danilo.teste.apicadastro.service;

import br.com.danilo.teste.apicadastro.dao.PessoaRepository;
import br.com.danilo.teste.apicadastro.models.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.persistence.Transient;

import static java.util.Optional.ofNullable;

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

    public boolean existe(Pessoa pessoa) {
        return repository.findByCpf(pessoa.getCpf()).isPresent();
    }

    @Transient
    public Pessoa atualizar(String cpf, Pessoa newPessoa) {
        return repository.findByCpf(cpf)
                .map(pessoa -> {
                    ofNullable(newPessoa.getNome()).ifPresent(pessoa::setNome);
                    ofNullable(newPessoa.getApelido()).ifPresent(pessoa::setApelido);
                    ofNullable(newPessoa.getDataNascimento()).ifPresent(pessoa::setDataNascimento);
                    ofNullable(newPessoa.getDependentes()).ifPresent(pessoa::setDependentes);
                    ofNullable(newPessoa.getEnderecos()).ifPresent(pessoa::setEnderecos);
                    ofNullable(newPessoa.getProfissao()).ifPresent(pessoa::setProfissao);
                    ofNullable(newPessoa.getSalario()).ifPresent(pessoa::setSalario);
                    ofNullable(newPessoa.getTelefones()).ifPresent(pessoa::setTelefones);
                    return repository.save(pessoa);
                }).orElseThrow(() -> new NoResultException("Pessoa não foi encontrada."));
    }
}
