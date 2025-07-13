package src.competicao.model.competicao;

import src.competicao.model.core.Participante;
import src.competicao.model.core.Partida;
import src.competicao.model.core.Tabela;
import src.competicao.model.core.Time;

import java.util.ArrayList;
import java.util.List;

public class Liga implements Campeonato {
    private List<Participante> participantes;
    private List<Partida> partidas;
    private Tabela tabela;

    public Liga() {
        this.participantes = new ArrayList<>();
        this.partidas = new ArrayList<>();
        this.tabela = new Tabela();
    }

    @Override
    public void adicionarParticipante(Time time) {

    }

    @Override
    public void adicionarPartida(Partida partida) {

    }

    @Override
    public void registrarResultadoPartida(Partida partida,
                                          Integer golsMandante,
                                          Integer golsVisitante) {

    }

    @Override
    public List<Participante> getParticipantes() {
        return participantes;
    }

    @Override
    public List<Partida> getPartidas() {
        return partidas;
    }

    public Tabela getTabela() {
        return tabela;
    }
}
