package br.com.danilo.teste.apicadastro.models;

import java.text.MessageFormat;
import java.util.stream.Stream;

public enum TipoLogradouro {

    RUA("Rua"),
    AVENIDA("Avenida"),
    ESTRADA("Estrada");

    private final String descricao;

    TipoLogradouro(String descricao) {
        this.descricao = descricao;
    }

    public static TipoLogradouro obterPorDescricao(String descricao) {
        return Stream.of(TipoLogradouro.values())
                .filter(t -> t.getDescricao().equals(descricao))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("Não foi encontrado descrição: {0} para o TipoLogradouro.", descricao)));
    }

    public String getDescricao() {
        return descricao;
    }
}
