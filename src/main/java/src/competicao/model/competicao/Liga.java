package src.competicao.model.competicao;

import src.competicao.model.core.Participante;
import src.competicao.model.core.Partida;
import src.competicao.model.core.Time;
import src.competicao.service.GerarRodadasService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Liga implements Campeonato, PontosCorridos {
    private String nome;
    private List<Participante> participantes;
    private List<Partida> partidas;
    private int maxParticipantes;
    private int qtdClassificados;
    private String nomeClassificacao;
    private int qtdRebaixados;

    public Liga(String nome,
                int maxParticipantes,
                int qtdClassificados,
                String nomeClassificacao,
                int qtdRebaixados) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da Liga não pode ser nulo ou vazio.");
        }

        this.nome = nome.trim();
        this.participantes = new ArrayList<>();
        this.partidas = new ArrayList<>();

        setMaxParticipantes(maxParticipantes);
        setClassificacao(qtdClassificados, nomeClassificacao);
        setQtdRebaixados(qtdRebaixados);
    }

    public void adicionarParticipante(Time time) {
        Objects.requireNonNull(time, "O time não pode ser nulo.");

        if (this.participantes.size() >= this.maxParticipantes) {
            throw new IllegalStateException(
                    "A liga " + this.nome + " já atingiu o número máximo de " +
                    this.maxParticipantes + " participantes.");
        }

        Participante participante = new Participante(time);

        if (participantes.contains(participante)) {
            throw new IllegalArgumentException(
                    "O participante " + participante.getTime().getNome() + " já existe na Liga");
        } else {
            this.participantes.add(participante);
            if (this.getParticipantes().size() == maxParticipantes) {
                GerarRodadasService.gerarRodadasLiga(this);
            }
        }
    }

    @Override
    public void adicionarPartida(Partida partida) {
        Objects.requireNonNull(partida, "A partida não pode ser nula.");

        if (this.participantes.size() < this.maxParticipantes) {
            throw new IllegalStateException(
                    "A Liga deve estar cheia para que as partidas possam ser iniciadas.");
        }

        if (partida.isJogada()) {
            throw new IllegalArgumentException(
                    "As partidas de uma Liga não podem ter sigo jogadas ao serem adicionadas.");
        }

        if (!participantes.contains(partida.getMandante()) ||
            !participantes.contains(partida.getVisitante())) {
            throw new IllegalArgumentException(
                    "Os participantes da partida devem ambos participar da Liga.");
        }

        if (this.partidas.contains(partida)) {
            throw new IllegalArgumentException("A Liga já adicionou essa partida");
        }

        this.partidas.add(partida);
    }

    @Override
    public void registrarResultadoPartida(Partida partida,
                                          int golsMandante,
                                          int golsVisitante) {
        Objects.requireNonNull(partida, "A partida não pode ser nula.");

        if (!this.partidas.contains(partida)) {
            throw new IllegalArgumentException("A partida não pertence à liga " + this.nome + ".");
        }

        partida.registrarResultado(golsMandante, golsVisitante);
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

    @Override
    public List<Participante> getParticipantes() {
        return new ArrayList<>(participantes);
    }

    @Override
    public List<Partida> getPartidas() {
        return new ArrayList<>(partidas);
    }

    @Override
    public int getMaxParticipantes() {
        return maxParticipantes;
    }

    public String getNome() {
        return nome;
    }

    public int getQtdClassificados() {
        return qtdClassificados;
    }

    public String getNomeClassificacao() {
        return nomeClassificacao;
    }

    public int getQtdRebaixados() {
        return qtdRebaixados;
    }

    public void setMaxParticipantes(int maxParticipantes) {
        if (maxParticipantes < this.qtdClassificados + this.qtdRebaixados) {
            throw new IllegalArgumentException(
                    "A quantidade máxima  de participantes tem que ser maior que a quantidade de classificados mais a de rebaixados na Liga");
        }

        if (maxParticipantes < this.participantes.size()) {
            throw new IllegalArgumentException(
                    "O número máximo de participantes não pode ser menor que o número atual de participantes na Liga.");
        }

        if (maxParticipantes <= 0) {
            throw new IllegalArgumentException("O número máximo de participantes deve ser positivo.");
        }

        if (maxParticipantes % 2 != 0) {
            throw new IllegalArgumentException("O número máximo de participantes deve ser par.");
        }

        this.maxParticipantes = Math.max(0, maxParticipantes);
    }

    public void setQtdClassificados(int qtdClassificados) {
        qtdClassificados = Math.max(0, qtdClassificados);

        if (qtdClassificados + this.qtdRebaixados > maxParticipantes) {
            throw new IllegalArgumentException(
                    "A quantidade de classificados mais a de rebaixados não pode ser maior que o máximo de participantes da Liga");
        }

        this.qtdClassificados = qtdClassificados;

        if (this.qtdClassificados == 0) {
            this.nomeClassificacao = null;
        } else if (this.nomeClassificacao == null || this.nomeClassificacao.trim().isEmpty()) {
            this.nomeClassificacao = "Classificados";
        }
    }

    public void setClassificacao(int qtdClassificados, String nomeClassificacao) {
        if (qtdClassificados + this.qtdRebaixados > maxParticipantes) {
            throw new IllegalArgumentException(
                    "A quantidade de classificados mais a de rebaixados não pode ser maior que o máximo de participantes da Liga");
        }

        this.qtdClassificados = Math.max(0, qtdClassificados);
        setNomeClassificacao(nomeClassificacao);
    }

    public void setNomeClassificacao(String nomeClassificacao) {
        if (this.qtdClassificados == 0) {
            this.nomeClassificacao = null;
        } else {
            this.nomeClassificacao =
                    (nomeClassificacao != null && !nomeClassificacao.trim().isEmpty()) ?
                    nomeClassificacao.trim() :
                    "Classificados";
        }
    }

    public void setQtdRebaixados(int qtdRebaixados) {
        if (this.qtdClassificados + qtdRebaixados > maxParticipantes) {
            throw new IllegalArgumentException(
                    "A quantidade de classificados mais a de rebaixados não pode ser maior que o máximo de participantes da Liga");
        }

        this.qtdRebaixados = Math.max(0, qtdRebaixados);
    }
}
