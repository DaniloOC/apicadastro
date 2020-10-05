package br.com.danilo.teste.apicadastro.models;

import javax.persistence.*;

@Entity
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "telefone_id")
    private Long id;
    private Integer codigoPais;
    private Integer ddd;
    private String numero;
    private TipoTelefone tipo;

    public Telefone() {

    }

    public Telefone(Integer codigoPais, Integer ddd, String numero, TipoTelefone tipo) {
        this.codigoPais = codigoPais;
        this.ddd = ddd;
        this.numero = numero;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(Integer codigoPais) {
        this.codigoPais = codigoPais;
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }
}
