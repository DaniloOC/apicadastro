package br.com.danilo.teste.apicadastro.web.dto;

import br.com.danilo.teste.apicadastro.models.Endereco;
import br.com.danilo.teste.apicadastro.models.TipoEndereco;
import br.com.danilo.teste.apicadastro.models.TipoLogradouro;

import java.io.Serializable;

public class EnderecoDTO implements Serializable {

    private String tipoEndereco;
    private String tipo;
    private String nome;
    private String numero;
    private String complemento;
    private String cep;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;

    public EnderecoDTO() {

    }

    public EnderecoDTO(Endereco endereco) {
        tipoEndereco = endereco.getTipoEndereco().getDescricao();
        tipo = endereco.getTipo().getDescricao();
        nome = endereco.getNome();
        numero = endereco.getNumero();
        complemento = endereco.getComplemento();
        cep = endereco.getCep();
        bairro = endereco.getBairro();
        cidade = endereco.getCidade();
        estado = endereco.getEstado();
        pais = endereco.getPais();
    }

    public String getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(String tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Endereco toEntity() {
        TipoEndereco tipoEnderecoEnum = TipoEndereco.obterPorDescricao(getTipoEndereco());
        TipoLogradouro tipoLogradouro = TipoLogradouro.obterPorDescricao(getTipo());

        Endereco endereco = new Endereco();
        endereco.setTipoEndereco(tipoEnderecoEnum);
        endereco.setTipo(tipoLogradouro);
        endereco.setNome(getNome());
        endereco.setNumero(getNumero());
        endereco.setComplemento(getComplemento());
        endereco.setCep(getCep());
        endereco.setBairro(getBairro());
        endereco.setCidade(getCidade());
        endereco.setEstado(getEstado());
        endereco.setPais(getPais());
        return endereco;
    }
}
