package src.competicao.model.competicao;

import src.competicao.model.core.Participante;
import src.competicao.model.core.Partida;
import src.competicao.model.core.Time;

import java.util.List;

public interface Campeonato {
    public void adicionarParticipante(Time time);

    public void adicionarPartida(Partida partida);

    public void registrarResultadoPartida(Partida partida,
                                          Integer golsMandante,
                                          Integer golsVisitante);

    List<Participante> getParticipantes();

    List<Partida> getPartidas();
}
