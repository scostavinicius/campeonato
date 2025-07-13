package src.competicao.model.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO: PARTICIPANTE COMO TABELA INTERMEDIARIA ENTRE TIME E CAMPEONATO
public class Participante implements Comparable<Participante> {
    private Time time;
    private List<Partida> partidas;
    private Integer vitorias;
    private Integer empates;
    private Integer derrotas;
    private Integer golsMarcados;
    private Integer golsSofridos;

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

        Integer golsPro;
        Integer golsContra;

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

    public Integer getVitorias() {
        return vitorias;
    }

    public Integer getEmpates() {
        return empates;
    }

    public Integer getDerrotas() {
        return derrotas;
    }

    public Integer getGolsMarcados() {
        return golsMarcados;
    }

    public Integer getGolsSofridos() {
        return golsSofridos;
    }

    public Integer getQtdPartidasJogadas() {
        return vitorias + empates + derrotas;
    }

    public Integer getPontos() {
        return 3 * vitorias + empates;
    }

    public Integer getSaldoGols() {
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

        int comparacaoPontos = outro.getPontos().compareTo(getPontos());
        if (comparacaoPontos != 0) {
            return comparacaoPontos;
        }

        int comparacaoVitorias = outro.getVitorias().compareTo(getVitorias());
        if (comparacaoVitorias != 0) {
            return comparacaoVitorias;
        }

        int comparacaoSaldo = outro.getSaldoGols().compareTo(getSaldoGols());
        if (comparacaoSaldo != 0) {
            return comparacaoSaldo;
        }

        return outro.getGolsMarcados().compareTo(getGolsMarcados());
    }
}
