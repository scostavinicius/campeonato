package src.competicao.model.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.competicao.util.Cor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParticipanteTest {
    private Time timeA;
    private Time timeB;
    private Participante participanteA;
    private Participante participanteB;

    @BeforeEach
    void setUp() {
        timeA = new Time("TimeA", Cor.FUNDO_AZUL, Cor.FUNDO_PRETO);
        timeB = new Time("TimeB", Cor.FUNDO_BRANCO, Cor.FUNDO_CIANO_ESCURO);
        participanteA = new Participante(timeA);
        participanteB = new Participante(timeB);
    }

    @Test
    @DisplayName("Criar participante corretamente")
    void criarParticipante() {
        assertNotNull(participanteA);
        assertEquals(timeA, participanteA.getTime());
        assertEquals(0, participanteA.getPartidas().size());
        assertEquals(0, participanteA.getPontos());
        assertEquals(0, participanteA.getVitorias());
        assertEquals(0, participanteA.getEmpates());
        assertEquals(0, participanteA.getDerrotas());
        assertEquals(0, participanteA.getGolsMarcados());
        assertEquals(0, participanteA.getGolsSofridos());
        assertEquals(0, participanteA.getSaldoGols());
    }

    @Test
    @DisplayName("Exceção por partida nula")
    void excecaoPartidaNula() {
        assertThrows(NullPointerException.class, () -> {
            participanteA.adicionarPartida(null);
        });
    }

    @Test
    @DisplayName("Exceção por adicionar partida não jogada")
    void excecaoPartidaNaoJogada() {
        assertThrows(IllegalArgumentException.class, () -> {
            Partida partida = new Partida(participanteA, participanteB);
            participanteA.adicionarPartida(partida);
        });
    }

    @Test
    @DisplayName("Exceção por mandante igual visitante")
    void excecaoPartidaMandanteIgualVisitante() {
        assertThrows(IllegalArgumentException.class, () -> {
            Partida partida = new Partida(participanteA, participanteA);
        });
    }

    @Test
    @DisplayName("Adicionar vitória")
    void adicionarVitoria() {
        Partida partida = new Partida(participanteA, participanteB);
        partida.registrarResultado(2, 1);

        assertEquals(1, participanteA.getQtdPartidasJogadas());
        assertEquals(1, participanteA.getVitorias());
        assertEquals(0, participanteA.getEmpates());
        assertEquals(0, participanteA.getDerrotas());
        assertEquals(2, participanteA.getGolsMarcados());
        assertEquals(1, participanteA.getGolsSofridos());
        assertEquals(1, participanteA.getSaldoGols());
        assertEquals(3, participanteA.getPontos());
    }

    @Test
    @DisplayName("Adicionar empate")
    void adicionarEmpate() {
        Partida partida = new Partida(participanteA, participanteB);
        partida.registrarResultado(1, 1);

        assertEquals(1, participanteA.getQtdPartidasJogadas());
        assertEquals(0, participanteA.getVitorias());
        assertEquals(1, participanteA.getEmpates());
        assertEquals(0, participanteA.getDerrotas());
        assertEquals(1, participanteA.getGolsMarcados());
        assertEquals(1, participanteA.getGolsSofridos());
        assertEquals(0, participanteA.getSaldoGols());
        assertEquals(1, participanteA.getPontos());
    }

    @Test
    @DisplayName("Adicionar derrota")
    void adicionarDerrota() {
        Partida partida = new Partida(participanteA, participanteB);
        partida.registrarResultado(0, 2);

        assertEquals(1, participanteA.getQtdPartidasJogadas());
        assertEquals(0, participanteA.getVitorias());
        assertEquals(0, participanteA.getEmpates());
        assertEquals(1, participanteA.getDerrotas());
        assertEquals(0, participanteA.getGolsMarcados());
        assertEquals(2, participanteA.getGolsSofridos());
        assertEquals(-2, participanteA.getSaldoGols());
        assertEquals(0, participanteA.getPontos());
    }

    @Test
    @DisplayName("Ordenar participantes")
    void deveOrdenarParticipantesCorretamente() {
        Participante p1 = new Participante(new Time("Time X", Cor.FUNDO_AZUL, Cor.FUNDO_BRANCO));
        Participante p2 = new Participante(new Time("Time Y", Cor.FUNDO_VERMELHO, Cor.FUNDO_PRETO));
        Participante p3 = new Participante(new Time("Time Z", Cor.FUNDO_VERDE, Cor.FUNDO_AMARELO));

        Partida p1x2 = new Partida(p1, p2);
        p1x2.registrarResultado(1, 0);

        Partida p2x3 = new Partida(p2, p3);
        p2x3.registrarResultado(0, 0);

        Partida p3x1 = new Partida(p3, p1);
        p3x1.registrarResultado(1, 0);

        Partida p1x3 = new Partida(p1, p3);
        p1x3.registrarResultado(1, 0);

        List<Participante> participantes = new ArrayList<>();
        participantes.add(p1);
        participantes.add(p2);
        participantes.add(p3);

        Collections.sort(participantes);

        assertEquals(p1, participantes.get(0), "Primeiro deve ser p1");
        assertEquals(p3, participantes.get(1), "Segundo deve ser p3");
        assertEquals(p2, participantes.get(2), "Terceiro deve ser p2");
    }
}
