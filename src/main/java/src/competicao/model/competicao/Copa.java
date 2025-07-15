package src.competicao.model.competicao;

import src.competicao.model.core.Participante;
import src.competicao.model.core.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Copa extends Campeonato {
    private List<Grupo> grupos;
    private Eliminatoria eliminatoria;

    public Copa(String nome, int maxParticipantes) {
        super(nome);
        setMaxParticipantes(maxParticipantes);
        this.grupos = new ArrayList<>();
        this.eliminatoria = new Eliminatoria();
    }

    public void adicionarParticipante(Time time) {
        super.adicionarParticipante(time);

        if (this.getParticipantes().size() == maxParticipantes) {
            gerarGrupos();
        }
    }

    private void gerarGrupos() {
        int qtdGrupos = maxParticipantes / 4;

        for (int g = 0; g < qtdGrupos; g++) {
            int indicePrimeiroParticipante = g * 4;

            Grupo grupo = new Grupo("Grupo " + (g + 1));

            for (int pg = 0; pg < 4; pg++) {
                Participante participante = participantes.get(pg + indicePrimeiroParticipante);

                grupo.adicionarParticipante(participante.getTime());
            }

            adicionarGrupo(grupo);
        }


    }

    private void adicionarGrupo(Grupo grupo) {
        Objects.requireNonNull(grupo, "Grupo não pode ser nulo");

        this.grupos.add(grupo);
    }

    public void avancarParaEliminatorias() {
        int qtdClassificados = grupos.size() * grupos.getFirst().getQtdClassificados();

        this.eliminatoria = new Eliminatoria(qtdClassificados);

        List<Participante> classificados = new ArrayList<>();

        for (Grupo grupo : grupos) {
            for (int c = 0; c < 2; c++) {
                classificados.add(grupo.getClassificacao().get(c));
            }
        }

        eliminatoria.adicionarClassificados(classificados);
        eliminatoria.jogarFaseEliminatoria();
    }

    public List<Grupo> getGrupos() {
        return new ArrayList<>(grupos);
    }

    public Eliminatoria getEliminatoria() {
        return eliminatoria;
    }

    public void setMaxParticipantes(int maxParticipantes) {
        if (maxParticipantes < 8) {
            throw new IllegalArgumentException(
                    "O número máximo de participantes deve ser no mínimo 8.");
        }

        if (maxParticipantes != 8 && maxParticipantes != 16 && maxParticipantes != 32) {
            throw new IllegalArgumentException(
                    "O número máximo de participantes deve ser 8, 16 ou 32.");
        }

        if (maxParticipantes < this.participantes.size()) {
            throw new IllegalArgumentException(
                    "O número máximo de participantes não pode ser menor que o número atual de participantes.");
        }

        this.maxParticipantes = maxParticipantes;
    }
}
