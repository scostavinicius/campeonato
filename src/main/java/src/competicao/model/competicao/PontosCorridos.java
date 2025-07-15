package src.competicao.model.competicao;

import src.competicao.model.core.Participante;
import src.competicao.model.core.Partida;
import src.competicao.model.core.Time;
import src.competicao.util.ScanTipo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class PontosCorridos extends Campeonato {
    protected List<Partida> partidas;
    protected int qtdClassificados;
    protected String nomeClassificacao;

    public PontosCorridos(String nome,
                          int maxParticipantes,
                          int qtdClassificados,
                          String nomeClassificacao) {
        super(nome);
        this.partidas = new ArrayList<>();
        setMaxParticipantes(maxParticipantes);
        setClassificacao(qtdClassificados, nomeClassificacao);
    }

    public void adicionarParticipante(Time time) {
        super.adicionarParticipante(time);

        if (this.getParticipantes().size() == maxParticipantes) {
            gerarRodadas();
        }
    }

    private void gerarRodadas() {
        List<Participante> participantes = this.getParticipantes();

        int numParticipantes = this.getMaxParticipantes();

        if (participantes.isEmpty()) {
            throw new IllegalStateException("A Liga não pode estar vazia.");
        }

        if (participantes.size() < numParticipantes) {
            throw new IllegalStateException("A Liga ainda não está cheia");
        }

        if (getPartidas().size() == Math.pow(numParticipantes, 2) / 2) {
            throw new IllegalStateException("As partidas da Liga já foram registradas.");
        }

        for (int rodada = 0; rodada < numParticipantes - 1; rodada++) {
            for (int i = 0; i < (numParticipantes / 2); i++) {
                this.adicionarPartida(new Partida(participantes.get(i),
                                                  participantes.get(numParticipantes - i - 1)));
            }

            Participante temp = participantes.get(numParticipantes - 1);

            for (int i = numParticipantes - 1; i > 1; i--) {
                participantes.set(i, participantes.get(i - 1));
            }

            participantes.set(1, temp);
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

        int indicePrimeiroParticipante = rodada * getQtdPartidasPorRodadas();

        for (int pr = 0; pr < getQtdPartidasPorRodadas(); pr++) {
            Partida partidaAtual = partidas.get(i + indicePrimeiroParticipante);

            System.out.println("DIGITE O RESULTADO DA PARTIDA " + partidaAtual.toString());
            int golsMandante =
                    ScanTipo.scanIntMinimo("Digite a quantidade de gols do primeiro time: ", 0);
            int golsVisitante =
                    ScanTipo.scanIntMinimo("Digite a quantidade de gols do segundo time: ", 0);

            this.registrarResultadoPartida(partidaAtual, golsMandante, golsVisitante);
            System.out.println(partidaAtual.toString() + "\n");
            i++;
        }
    }

    public void adicionarPartida(Partida partida) {
        Objects.requireNonNull(partida, "A partida não pode ser nula.");

        if (this.participantes.size() < this.maxParticipantes) {
            throw new IllegalStateException(
                    "A quantidade máxima de participantes deve ser atingida para que as partidas possam ser iniciadas.");
        }

        if (partida.isJogada()) {
            throw new IllegalArgumentException(
                    "As partidas de uma não podem ter sigo jogadas antes de serem adicionadas.");
        }

        if (!participantes.contains(partida.getMandante()) ||
            !participantes.contains(partida.getVisitante())) {
            throw new IllegalArgumentException(
                    "Os participantes da partida devem ambos participar do(a) " + this.getNome());
        }

        if (this.partidas.contains(partida)) {
            throw new IllegalArgumentException("Essa partida já foi adicionada.");
        }

        this.partidas.add(partida);
    }

    public void registrarResultadoPartida(Partida partida,
                                          int golsMandante,
                                          int golsVisitante) {
        Objects.requireNonNull(partida, "A partida não pode ser nula.");

        if (!this.partidas.contains(partida)) {
            throw new IllegalArgumentException(this.nome + " não possui essa partida.");
        }

        partida.registrarResultado(golsMandante, golsVisitante);
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

    public List<Partida> getPartidas() {
        return new ArrayList<>(partidas);
    }

    public int getQtdClassificados() {
        return qtdClassificados;
    }

    public String getNomeClassificacao() {
        return nomeClassificacao;
    }

    public void setMaxParticipantes(int maxParticipantes) {
        if (maxParticipantes < 4) {
            throw new IllegalArgumentException(
                    "O número máximo de participantes deve ser no mínimo 4.");
        }

        if (maxParticipantes % 2 != 0) {
            throw new IllegalArgumentException("O número máximo de participantes deve ser par.");
        }

        super.setMaxParticipantes(maxParticipantes);
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
}
