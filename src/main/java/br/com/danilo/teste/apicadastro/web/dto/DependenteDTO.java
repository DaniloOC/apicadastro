package br.com.danilo.teste.apicadastro.web.dto;

import br.com.danilo.teste.apicadastro.models.Dependente;
import br.com.danilo.teste.apicadastro.models.TipoDependente;

import java.io.Serializable;
import java.util.stream.Stream;

public class DependenteDTO implements Serializable {

    private String cpf;
    private String nome;
    private String tipo;

    public DependenteDTO() {

    }

    public DependenteDTO(Dependente dependente) {
        cpf = dependente.getCpf();
        nome = dependente.getNome();
        tipo = dependente.getTipo().getDescricao();
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public Dependente toEntity() {
        TipoDependente tipoDependente = TipoDependente.obterPorDescricao(tipo);
        return new Dependente(cpf, nome, tipoDependente);
    }
}
