package br.com.danilo.teste.apicadastro.models;

import java.text.MessageFormat;
import java.util.stream.Stream;

public enum TipoDependente {

    ESPOSA("Esposa"),
    FILHO("Filho"),
    PAI("Pai"),
    MAE("Mãe");

    private final String descricao;

    TipoDependente(String descricao) {
        this.descricao = descricao;
    }

    public static TipoDependente obterPorDescricao(String descricao) {
        return Stream.of(TipoDependente.values())
                .filter(t -> t.getDescricao().equals(descricao))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("Não foi encontrado descrição: {0} para o TipoDependente.", descricao)));
    }

    public String getDescricao() {
        return descricao;
    }
}
