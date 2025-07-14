package src.competicao.model.competicao;

import src.competicao.model.core.Participante;
import src.competicao.model.core.Partida;

import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private Integer qtdClassificados;
    private List<Participante> participantes;
    private List<Partida> partidas;

    public Grupo(Integer qtdClassificados) {
        this.qtdClassificados = qtdClassificados;
        this.participantes = new ArrayList<>();
        this.partidas = new ArrayList<>();
    }

    public Integer getQtdClassificados() {
        return qtdClassificados;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }
}
