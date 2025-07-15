package src.competicao.model.competicao;

import src.competicao.model.core.Participante;
import src.competicao.model.core.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Campeonato {
    protected String nome;
    protected List<Participante> participantes;
    protected int maxParticipantes;

    public Campeonato() {
        this.nome = null;
        this.participantes = new ArrayList<>();
        this.maxParticipantes = 0;
    }

    public Campeonato(String nome) {
        setNome(nome);
        this.participantes = new ArrayList<>();
    }

    public Campeonato(String nome, int maxParticipantes) {
        setNome(nome);
        this.participantes = new ArrayList<>();
        setMaxParticipantes(maxParticipantes);
    }

    public void adicionarParticipante(Time time) {
        Objects.requireNonNull(time, "O time não pode ser nulo.");

        if (this.participantes.size() >= this.maxParticipantes) {
            throw new IllegalStateException(
                    this.nome + " já atingiu o número máximo de " +
                    this.maxParticipantes + " participantes.");
        }

        Participante participante = new Participante(time);

        if (participantes.contains(participante)) {
            throw new IllegalArgumentException(
                    "O participante " + participante.getTime().getNome() + " já foi adicionado");
        } else {
            this.participantes.add(participante);
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

    public String getNome() {
        return nome;
    }

    public List<Participante> getParticipantes() {
        return new ArrayList<>(participantes);
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }

        this.nome = nome.trim();
    }

    public void setMaxParticipantes(int maxParticipantes) {
        if (maxParticipantes < this.participantes.size()) {
            throw new IllegalArgumentException(
                    "O número máximo de participantes não pode ser menor que o número atual de participantes.");
        }

        this.maxParticipantes = maxParticipantes;
    }
}
