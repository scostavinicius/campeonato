package src.competicao.model.competicao;

import src.competicao.model.core.Participante;
import src.competicao.model.core.Partida;
import src.competicao.model.core.Time;

import java.util.List;
import java.util.Objects;

public class Liga extends PontosCorridos implements Campeonato {
    private int qtdRebaixados;

    public Liga(String nome,
                int maxParticipantes,
                int qtdClassificados,
                String nomeClassificacao,
                int qtdRebaixados) {
        super(nome, maxParticipantes, qtdClassificados, nomeClassificacao);
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
                gerarRodadasLiga();
            }
        }
    }

    private void gerarRodadasLiga() {
        List<Participante> participantes = this.getParticipantes();

        int numParticipantes = this.getMaxParticipantes();

        if (participantes.isEmpty()) {
            throw new IllegalStateException("A Liga não pode estar vazia.");
        }

        if (participantes.size() < numParticipantes) {
            throw new IllegalStateException("A Liga ainda não está cheia");
        }

//        if (liga.getPartidas().size() == (numParticipantes - 1) * (numParticipantes - 2)) {
//            throw new IllegalStateException("As partidas da Liga já foram registradas.");
//        }

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

    public int getQtdRebaixados() {
        return qtdRebaixados;
    }

    public void setMaxParticipantes(int maxParticipantes) {
        if (maxParticipantes < this.qtdClassificados + this.qtdRebaixados) {
            throw new IllegalArgumentException(
                    "A quantidade máxima  de participantes tem que ser maior que a quantidade de classificados mais a de rebaixados na Liga");
        }

        super.setMaxParticipantes(maxParticipantes);
    }

    public void setQtdClassificados(int qtdClassificados) {
        qtdClassificados = Math.max(0, qtdClassificados);

        if (qtdClassificados + this.qtdRebaixados > maxParticipantes) {
            throw new IllegalArgumentException(
                    "A quantidade de classificados mais a de rebaixados não pode ser maior que o máximo de participantes da Liga");
        }

        super.setQtdClassificados(qtdClassificados);
    }

    public void setClassificacao(int qtdClassificados, String nomeClassificacao) {
        if (qtdClassificados + this.qtdRebaixados > maxParticipantes) {
            throw new IllegalArgumentException(
                    "A quantidade de classificados mais a de rebaixados não pode ser maior que o máximo de participantes da Liga");
        }

        super.setClassificacao(qtdClassificados, nomeClassificacao);
    }

    public void setQtdRebaixados(int qtdRebaixados) {
        if (this.qtdClassificados + qtdRebaixados > maxParticipantes) {
            throw new IllegalArgumentException(
                    "A quantidade de classificados mais a de rebaixados não pode ser maior que o máximo de participantes da Liga");
        }

        this.qtdRebaixados = Math.max(0, qtdRebaixados);
    }
}
