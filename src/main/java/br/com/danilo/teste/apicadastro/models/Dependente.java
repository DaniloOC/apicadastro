package br.com.danilo.teste.apicadastro.models;

import javax.persistence.*;

@Entity
public class Dependente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "dependente_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pessoa_id")
    private Pessoa pessoa;

    private String cpf;

    private String nome;

    private TipoDependente tipo;

    public Dependente() {

    }

    public Dependente(String cpf, String nome, TipoDependente tipo) {
        this.cpf = cpf;
        this.nome = nome;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public TipoDependente getTipo() {
        return tipo;
    }

    public void setTipo(TipoDependente tipo) {
        this.tipo = tipo;
    }
}
