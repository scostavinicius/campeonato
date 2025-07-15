package src.competicao.model.competicao;

import src.competicao.model.core.Participante;
import src.competicao.model.core.Partida;
import src.competicao.util.ScanTipo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class PontosCorridos {
    protected String nome;
    protected List<Participante> participantes;
    protected List<Partida> partidas;
    protected int maxParticipantes;
    protected int qtdClassificados;
    protected String nomeClassificacao;

    public PontosCorridos() {
        this.nome = "Nome";
        this.participantes = new ArrayList<>();
        this.partidas = new ArrayList<>();
        this.maxParticipantes = 0;
        this.qtdClassificados = 0;
        this.nome = "Nome";
    }

    public PontosCorridos(String nome,
                          int maxParticipantes,
                          int qtdClassificados,
                          String nomeClassificacao) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da Liga não pode ser nulo ou vazio.");
        }

        this.nome = nome.trim();
        this.participantes = new ArrayList<>();
        this.partidas = new ArrayList<>();

        setMaxParticipantes(maxParticipantes);
        setClassificacao(qtdClassificados, nomeClassificacao);
    }

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

    public int getQtdPartidasPorRodadas() {
        return this.maxParticipantes / 2;
    }

    public int getQtdRodadas() {
        return this.partidas.size() / getQtdPartidasPorRodadas();
    }

    public List<Participante> getClassificacao() {
        List<Participante> classificacao = new ArrayList<>(this.participantes);
        Collections.sort(classificacao);
        return classificacao;
    }

    public List<Participante> getParticipantes() {
        return new ArrayList<>(participantes);
    }

    public List<Partida> getPartidas() {
        return new ArrayList<>(partidas);
    }

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

    public void setMaxParticipantes(int maxParticipantes) {
        if (maxParticipantes < this.participantes.size()) {
            throw new IllegalArgumentException(
                    "O número máximo de participantes não pode ser menor que o número atual de participantes na Liga.");
        }

        if (maxParticipantes < 4) {
            throw new IllegalArgumentException(
                    "O número máximo de participantes deve ser no mínimo 4.");
        }

        if (maxParticipantes % 2 != 0) {
            throw new IllegalArgumentException("O número máximo de participantes deve ser par.");
        }

        this.maxParticipantes = Math.max(0, maxParticipantes);
    }

    public void setClassificacao(int qtdClassificados, String nomeClassificacao) {
        this.qtdClassificados = Math.max(0, qtdClassificados);
        setNomeClassificacao(nomeClassificacao);
    }

    public void setQtdClassificados(int qtdClassificados) {
        this.qtdClassificados = qtdClassificados;

        if (this.qtdClassificados == 0) {
            this.nomeClassificacao = null;
        } else if (this.nomeClassificacao == null || this.nomeClassificacao.trim().isEmpty()) {
            this.nomeClassificacao = "Classificados";
        }
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

    public void imprimirPartidasPorRodada() {
        int i = 0;

        for (int r = 0; r < getQtdRodadas(); r++) {
            System.out.println("RODADA " + (r + 1) + ": ");
            for (int pr = 0; pr < getQtdPartidasPorRodadas(); pr++) {
                System.out.println(partidas.get(i).toString());
                i++;
            }
        }
    }

    public void pedirResultadoPorRodada(int rodada) {
        int i = 0;

        int indiceRodadaAtual = rodada * getQtdPartidasPorRodadas();

        for (int pr = 0; pr < getQtdPartidasPorRodadas(); pr++) {
            Partida partidaAtual = partidas.get(i + indiceRodadaAtual);

            System.out.println("DIGITE O RESULTADO DA PARTIDA " + partidaAtual.toString());
            int golsMandante =
                    ScanTipo.scanIntPositivo("Digite a quantidade de gols do primeiro time: ");
            int golsVisitante =
                    ScanTipo.scanIntPositivo("Digite a quantidade de gols do segundo time: ");

            partidaAtual.registrarResultado(golsMandante, golsVisitante);

            partidaAtual.toString();

            i++;
        }

    }
}
