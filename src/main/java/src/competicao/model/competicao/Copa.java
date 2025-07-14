package src.competicao.model.competicao;

import src.competicao.model.core.Participante;
import src.competicao.model.core.Partida;
import src.competicao.model.core.Time;

import java.util.ArrayList;
import java.util.List;

public class Copa implements Campeonato {
    private List<Participante> participantes;
    private List<Partida> partidas;
    private List<Grupo> grupos;
    private Eliminatoria eliminatoria;

    public Copa() {
        this.participantes = new ArrayList<>();
        this.partidas = new ArrayList<>();
        this.grupos = new ArrayList<>();
        this.eliminatoria = new Eliminatoria();
    }

    @Override
    public void adicionarParticipante(Time time) {

    }

    @Override
    public void adicionarPartida(Partida partida) {

    }

    @Override
    public void registrarResultadoPartida(Partida partida, int golsMandante, int golsVisitante) {

    }

    @Override
    public List<Participante> getParticipantes() {
        return participantes;
    }

    @Override
    public List<Partida> getPartidas() {
        return partidas;
    }

    @Override
    public int getMaxParticipantes() {
        return 0;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public Eliminatoria getEliminatoria() {
        return eliminatoria;
    }
}
