package src.competicao.model.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Tabela {
    private List<Participante> participantes;
    private Integer qtdClassificados;
    private String nomeClassificacao;
    private Integer qtdRebaixados;

    public Tabela() {
        this(0, null, 0);
    }

    public Tabela(Integer qtdClassificados,
                  String nomeClassificacao,
                  Integer qtdRebaixados) {
        this.participantes = new ArrayList<>();
        setQtdClassificados(qtdClassificados, nomeClassificacao);
        setQtdRebaixados(qtdRebaixados);
    }

    public void adicionarParticipante(Participante participante) {
        Objects.requireNonNull(participante, "O participante não pode ser nulo.");

        Boolean repetido = participantes.contains(participante);

        if (!repetido) {
            this.participantes.add(participante);
        } else {
            throw new IllegalArgumentException(
                    "O participante " + participante.getTime().getNome() + " já existe na tabela.");
        }
    }

    public Participante getParticipantePorNome(String nome) {
        Objects.requireNonNull(nome, "O nome do time não pode ser nulo.");

        for (Participante p : this.participantes) {
            if (p.getTime().getNome().equals(nome)) {
                return p;
            }
        }

        return null;
    }

    public List<Participante> getClassificacao() {
        List<Participante> classificacao = new ArrayList<>(this.participantes);
        Collections.sort(classificacao);
        return classificacao;
    }

    public void limparTabela() {
        this.participantes.clear();
    }

    public Integer getQtdClassificados() {
        return qtdClassificados;
    }

    public String getNomeClassificacao() {
        return nomeClassificacao;
    }

    public Integer getQtdRebaixados() {
        return qtdRebaixados;
    }

    public void setQtdClassificados(Integer qtdClassificados) {
        this.setQtdClassificados(qtdClassificados, null);
    }

    public void setQtdClassificados(Integer qtdClassificados, String nomeClassificacao) {
        this.qtdClassificados = Math.max(0, qtdClassificados);

        if (this.qtdClassificados == 0) {
            this.nomeClassificacao = null;
        } else {
            this.nomeClassificacao =
                    (nomeClassificacao != null && !nomeClassificacao.trim().isEmpty()) ?
                    nomeClassificacao.trim() :
                    "Classificados";
        }
    }

    public void setQtdRebaixados(Integer qtdRebaixados) {
        this.qtdRebaixados = Math.max(0, qtdRebaixados);
    }
}
