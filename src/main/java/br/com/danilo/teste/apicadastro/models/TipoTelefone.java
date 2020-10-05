package br.com.danilo.teste.apicadastro.models;

import java.text.MessageFormat;
import java.util.stream.Stream;

public enum TipoTelefone {

    CONTATO("Contato"),
    RESIDENCIAL("Residencial"),
    COMERCIAL("Comercial");

    private final String descricao;

    TipoTelefone(String descricao) {
        this.descricao = descricao;
    }

    public static TipoTelefone obterPorDescricao(String descricao) {
        return Stream.of(TipoTelefone.values())
                .filter(t -> t.getDescricao().equals(descricao))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("Não foi encontrado descrição: {0} para o TipoTelefone.", descricao)));
    }

    public String getDescricao() {
        return descricao;
    }
}
