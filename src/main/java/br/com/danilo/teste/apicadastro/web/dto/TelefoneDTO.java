package br.com.danilo.teste.apicadastro.web.dto;

import br.com.danilo.teste.apicadastro.models.Telefone;
import br.com.danilo.teste.apicadastro.models.TipoTelefone;

import java.io.Serializable;

public class TelefoneDTO implements Serializable {

    private Integer codigoPais;
    private Integer ddd;
    private String numero;
    private String tipo;

    public TelefoneDTO() {

    }

    public TelefoneDTO(Telefone telefone) {
        codigoPais = telefone.getCodigoPais();
        ddd = telefone.getDdd();
        numero = telefone.getNumero();
        tipo = telefone.getTipo().getDescricao();
    }

    public void setCodigoPais(Integer codigoPais) {
        this.codigoPais = codigoPais;
    }

    public Integer getCodigoPais() {
        return codigoPais;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public Telefone toEntity() {
        TipoTelefone tipoTelefone = TipoTelefone.obterPorDescricao(tipo);
        return new Telefone(codigoPais, ddd, numero, tipoTelefone);
    }
}
