package src.competicao.model.competicao;

import src.competicao.Main;
import src.competicao.model.core.Participante;
import src.competicao.model.core.Partida;

import java.util.ArrayList;
import java.util.List;

public class Eliminatoria {
    private List<Participante> classificados;
    private List<FaseEliminatoria> fases;
    private List<Participante> participantesFaseAtual;
    private int qtdFases;
    private int faseAtualIndex;

    public Eliminatoria() {
        this.classificados = new ArrayList<>();
        this.fases = new ArrayList<>();
        this.qtdFases = -1;
        this.participantesFaseAtual = new ArrayList<>();
        this.faseAtualIndex = 0;
    }

    public Eliminatoria(int qtdClassificados) {
        this.classificados = new ArrayList<>();
        this.fases = new ArrayList<>();
        this.participantesFaseAtual = new ArrayList<>();
        this.qtdFases = (int) Math.sqrt(qtdClassificados);
        this.faseAtualIndex = 0;
    }

    public void adicionarClassificados(List<Participante> classificados) {
        for (int quarteto = 0; quarteto < classificados.size(); quarteto += 4) {
            this.classificados.add(classificados.get(quarteto));
            this.classificados.add(classificados.get(quarteto + 3));
            this.classificados.add(classificados.get(quarteto + 1));
            this.classificados.add(classificados.get(quarteto + 2));
        }

        this.participantesFaseAtual.addAll(this.classificados);
    }

    public void jogarFaseEliminatoria() {
        int qtdParticipantesFaseAtual = participantesFaseAtual.size();

        FaseEliminatoria faseAtual = new FaseEliminatoria(qtdParticipantesFaseAtual);

        Main.decIgual(faseAtual.getNomeFase());

        for (int i = 0; i < qtdParticipantesFaseAtual; i += 2) {
            Partida partida =
                    new Partida(participantesFaseAtual.get(i), participantesFaseAtual.get(i + 1));

            faseAtual.adicionarPartida(partida);
        }

        faseAtual.pedirResultadosPartidas();

        this.participantesFaseAtual = new ArrayList<>();

        for (Partida partida : faseAtual.getPartidas()) {
            System.out.println(partida.toString());
            this.participantesFaseAtual.add(partida.getVencedor());
        }

        this.faseAtualIndex++;

        if (this.participantesFaseAtual.size() == 1) {
            Main.decIgual("VENCEDOR: " + participantesFaseAtual.getFirst().getTime().getNome());
            return;
        }

        jogarFaseEliminatoria();
    }

    public FaseEliminatoria getFaseAtual() {
        if (faseAtualIndex >= 0 && faseAtualIndex < fases.size()) {
            return fases.get(faseAtualIndex);
        }

        return null;
    }

    public List<Participante> getClassificados() {
        return new ArrayList<>(classificados);
    }

    public List<FaseEliminatoria> getFases() {
        return new ArrayList<>(fases);
    }

    public int getQtdFases() {
        return qtdFases;
    }
}
