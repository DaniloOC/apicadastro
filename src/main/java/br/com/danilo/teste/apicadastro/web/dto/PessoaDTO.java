package br.com.danilo.teste.apicadastro.web.dto;

import br.com.danilo.teste.apicadastro.models.Dependente;
import br.com.danilo.teste.apicadastro.models.Pessoa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PessoaDTO implements Serializable {

    private String nome;
    private String cpf;
    private String apelido;
    private String profissao;
    private BigDecimal salario;
    private LocalDate dataNascimento;
    private Set<EnderecoDTO> enderecos;
    private Set<DependenteDTO> dependentes;
    private Set<TelefoneDTO> telefones;

    public PessoaDTO() {

    }

    public PessoaDTO(Pessoa pessoa) {
        cpf = pessoa.getCpf();
        nome = pessoa.getNome();
        apelido = pessoa.getApelido();
        profissao = pessoa.getProfissao();
        salario = pessoa.getSalario();
        dataNascimento = pessoa.getDataNascimento();
        enderecos = pessoa.getEnderecos().stream().map(EnderecoDTO::new).collect(Collectors.toSet());
        dependentes = pessoa.getDependentes().stream().map(DependenteDTO::new).collect(Collectors.toSet());
        telefones = pessoa.getTelefones().stream().map(TelefoneDTO::new).collect(Collectors.toSet());
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getApelido() {
        return apelido;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Set<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<DependenteDTO> getDependentes() {
        return dependentes;
    }

    public void setDependentes(Set<DependenteDTO> dependentes) {
        this.dependentes = dependentes;
    }

    public Set<TelefoneDTO> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<TelefoneDTO> telefones) {
        this.telefones = telefones;
    }

    public Pessoa toEntity() {
        Pessoa pessoa = new Pessoa(getCpf(), getNome());
        pessoa.setApelido(getApelido());
        if (getEnderecos() != null) {
            pessoa.setEnderecos(getEnderecos().stream().map(EnderecoDTO::toEntity).collect(Collectors.toSet()));
        }
        pessoa.setProfissao(getProfissao());
        pessoa.setSalario(getSalario());
        if (getDependentes() != null) {
            Set<Dependente> dependentes = getDependentes()
                    .stream()
                    .map(dto -> {
                        Dependente dpt = dto.toEntity();
                        dpt.setPessoa(pessoa);
                        return dpt;
                    })
                    .collect(Collectors.toSet());
            pessoa.setDependentes(dependentes);
        }
        pessoa.setDataNascimento(getDataNascimento());
        if (getTelefones() != null) {
            pessoa.setTelefones(getTelefones().stream().map(TelefoneDTO::toEntity).collect(Collectors.toSet()));
        }
        return pessoa;
    }
}
