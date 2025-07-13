package src.competicao.model.core;

import java.util.Objects;
import java.util.UUID;

public class Partida {
    private final UUID id;

    private Participante mandante;
    private Participante visitante;
    private Integer golsMandante;
    private Integer golsVisitante;
    private Boolean jogada;

    public Partida(Participante mandante, Participante visitante) {
        Objects.requireNonNull(mandante, "O time mandante não pode ser nulo");
        Objects.requireNonNull(visitante, "O time visitante não pode ser nulo");

        if (mandante.equals(visitante)) {
            throw new IllegalArgumentException("Mandante e visitante não podem ser o mesmo time.");
        }

        this.id = UUID.randomUUID();
        this.mandante = mandante;
        this.visitante = visitante;
        this.jogada = false;
    }

    public void registrarResultado(Integer golsMandante, Integer golsVisitante) {
        if (jogada) {
            throw new IllegalArgumentException("O resultado dessa partida já foi registrado.");
        }

        if (golsMandante < 0 || golsVisitante < 0) {
            throw new IllegalArgumentException("Gols não podem ser negativos.");
        }

        this.golsMandante = golsMandante;
        this.golsVisitante = golsVisitante;
        this.jogada = true;

        this.mandante.adicionarPartida(this);
        this.visitante.adicionarPartida(this);
    }

    public Participante getMandante() {
        return mandante;
    }

    public Participante getVisitante() {
        return visitante;
    }

    public Integer getGolsMandante() {
        return golsMandante;
    }

    public Integer getGolsVisitante() {
        return golsVisitante;
    }

    public Boolean isJogada() {
        return jogada;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Partida partida = (Partida) o;
        return Objects.equals(id, partida.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
