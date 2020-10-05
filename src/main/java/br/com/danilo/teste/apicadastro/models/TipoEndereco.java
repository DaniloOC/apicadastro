package br.com.danilo.teste.apicadastro.models;

import java.text.MessageFormat;
import java.util.stream.Stream;

public enum TipoEndereco {

    COMERCIAL("Comercial"),
    RESIDENCIAL("Residencial"),
    CONTATO("Contato");

    private final String descricao;

    TipoEndereco(String descricao) {
        this.descricao = descricao;
    }

    public static TipoEndereco obterPorDescricao(String descricao) {
        return Stream.of(TipoEndereco.values())
                .filter(t -> t.getDescricao().equals(descricao))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("Não foi encontrado descrição: {0} para o TipoEndereco.", descricao)));
    }

    public String getDescricao() {
        return descricao;
    }
}
