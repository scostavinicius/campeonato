package src.competicao.service;

import src.competicao.model.competicao.Liga;
import src.competicao.model.core.Participante;
import src.competicao.model.core.Partida;

import java.util.List;
import java.util.Objects;

public class GerarRodadasService {
    public static void gerarRodadasLiga(Liga liga) {
        Objects.requireNonNull(liga, "Liga não pode ser nula.");

        List<Participante> participantes = liga.getParticipantes();

        int numParticipantes = liga.getMaxParticipantes();

        if (participantes.isEmpty()) {
            throw new IllegalStateException("A Liga não pode estar vazia.");
        }

        if (participantes.size() < numParticipantes) {
            throw new IllegalStateException("A Liga ainda não está cheia");
        }

        if (liga.getPartidas().size() == (numParticipantes - 1) * (numParticipantes - 2)) {
            throw new IllegalStateException("As partidas da Liga já foram registradas.");
        }

        for (int rodada = 0; rodada < numParticipantes - 1; rodada++) {
            for (int i = 0; i < (numParticipantes / 2); i++) {
                liga.adicionarPartida(new Partida(participantes.get(i),
                                                  participantes.get(numParticipantes - i - 1)));
            }

            Participante temp = participantes.get(numParticipantes - 1);

            for (int i = numParticipantes - 1; i > 1; i--) {
                participantes.set(i, participantes.get(i - 1));
            }

            participantes.set(1, temp);
        }
    }
}
