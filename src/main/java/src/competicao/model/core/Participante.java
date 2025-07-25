package src.competicao.model.core;

import src.competicao.service.TimeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Participante implements Comparable<Participante> {
    private Time time;
    private List<Partida> partidas;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int golsMarcados;
    private int golsSofridos;

    public Participante(Time time) {
        Objects.requireNonNull(time, "O Time participante não pode ser nulo.");
        this.time = time;
        this.partidas = new ArrayList<>();
        this.vitorias = 0;
        this.empates = 0;
        this.derrotas = 0;
        this.golsMarcados = 0;
        this.golsSofridos = 0;
    }

    /**
     * Adiciona uma partida e atualiza as estatísticas do Participante.
     * Deve ser chamado logo após o resultado da partida ser registrado.
     *
     * @param partida
     */
    public void adicionarPartida(Partida partida) {
        Objects.requireNonNull(partida, "A partida não pode ser nula.");

        if (!partida.isJogada()) {
            throw new IllegalArgumentException(
                    "Não é possível adicionar uma partida que não tenha sido jogada.");
        }

        if (!partida.getMandante().getTime().equals(time) &&
            !partida.getVisitante().getTime().equals(time)) {
            throw new IllegalArgumentException(
                    "A partida precisa envolver esse participante: " + time.getNome());
        }

        if (partidas.contains(partida)) {
            return;
        }

        this.partidas.add(partida);

        int golsPro;
        int golsContra;

        if (partida.getMandante().equals(this)) {
            golsPro = partida.getGolsMandante();
            golsContra = partida.getGolsVisitante();
        } else {
            golsPro = partida.getGolsVisitante();
            golsContra = partida.getGolsMandante();
        }

        this.golsMarcados += golsPro;
        this.golsSofridos += golsContra;

        if (golsPro > golsContra) {
            this.vitorias++;
        } else if (golsPro < golsContra) {
            this.derrotas++;
        } else {
            this.empates++;
        }
    }

    public Time getTime() {
        return time;
    }

    public List<Partida> getPartidas() {
        return new ArrayList<>(partidas);
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getGolsMarcados() {
        return golsMarcados;
    }

    public int getGolsSofridos() {
        return golsSofridos;
    }

    public int getQtdPartidasJogadas() {
        return vitorias + empates + derrotas;
    }

    public int getPontos() {
        return 3 * vitorias + empates;
    }

    public int getSaldoGols() {
        return golsMarcados - golsSofridos;
    }

    @Override
    public String toString() {
        return "Participante{" +
               "time=" + time +
               ", partidas=" + partidas +
               ", vitorias=" + vitorias +
               ", empates=" + empates +
               ", derrotas=" + derrotas +
               ", golsMarcados=" + golsMarcados +
               ", golsSofridos=" + golsSofridos +
               '}';
    }

    public String toStringL() {
        return TimeService.getStringCorTime(this.getTime()) + " " + this.getTime().getNome();
    }

    public String toStringR() {
        return this.getTime().getNome() + " " + TimeService.getStringCorTime(this.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Participante that = (Participante) o;
        return Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(time);
    }

    @Override
    public int compareTo(Participante outro) {
        Objects.requireNonNull(outro, "O participante não pode ser nulo.");

        if (this.getPontos() != outro.getPontos()) {
            return outro.getPontos() - this.getPontos();
        }

        if (this.vitorias != outro.vitorias) {
            return outro.vitorias - this.vitorias;
        }

        if (this.getSaldoGols() != outro.getSaldoGols()) {
            return outro.getSaldoGols() - this.getSaldoGols();
        }

        return outro.getGolsMarcados() - this.golsMarcados;
    }
}
