package src.competicao.model.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.competicao.util.Cor;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Partida")
public class PartidaTest {
    private Participante mandante;
    private Participante visitante;

    @BeforeEach
    void setUp() {
        Time m = new Time("Time Mandante", Cor.FUNDO_AZUL, Cor.FUNDO_BRANCO);
        Time v = new Time("Time Visitante", Cor.FUNDO_VERMELHO, Cor.FUNDO_PRETO);
        mandante = new Participante(m);
        visitante = new Participante(v);
    }

    @Test
    @DisplayName("Partida criada corretamente")
    void criarPartida() {
        Partida partida = new Partida(mandante, visitante);

        assertNotNull(partida);
        assertNotNull(partida.getMandante());
        assertNotNull(partida.getVisitante());
        assertEquals(mandante, partida.getMandante());
        assertEquals(visitante, partida.getVisitante());
        assertEquals(0, partida.getGolsMandante());
        assertEquals(0, partida.getGolsVisitante());
        assertFalse(partida.isJogada());
        assertNotNull(partida.getId());
    }

    @Test
    @DisplayName("Exceção por mandante nulo")
    void excecaoMandanteNulo() {
        assertThrows(NullPointerException.class, () -> {
            new Partida(null, visitante);
        });
    }

    @Test
    @DisplayName("Exceção por visitante nulo")
    void excecaoVisitanteNulo() {
        assertThrows(NullPointerException.class, () -> {
            new Partida(mandante, null);
        });
    }

    @Test
    @DisplayName("Exceção mandante igual visitante")
    void deveLancarExcecaoMesmoParticipante() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Partida(mandante, mandante);
        });
    }

    @Test
    @DisplayName("Registrar resultado e atualizar participantes corretamente")
    void deveRegistrarResultadoEAtualizarParticipantes() {
        Partida partida = new Partida(mandante, visitante);

        assertEquals(0, mandante.getPontos());
        assertEquals(0, visitante.getPontos());
        assertFalse(partida.isJogada());

        partida.registrarResultado(3, 1);

        assertEquals(3, partida.getGolsMandante());
        assertEquals(1, partida.getGolsVisitante());
        assertTrue(partida.isJogada());

        assertEquals(3, mandante.getPontos());
        assertEquals(1, mandante.getQtdPartidasJogadas());
        assertEquals(1, mandante.getVitorias());
        assertEquals(0, mandante.getEmpates());
        assertEquals(0, mandante.getDerrotas());
        assertEquals(3, mandante.getGolsMarcados());
        assertEquals(1, mandante.getGolsSofridos());
        assertEquals(2, mandante.getSaldoGols());

        assertEquals(0, visitante.getPontos());
        assertEquals(1, visitante.getQtdPartidasJogadas());
        assertEquals(0, visitante.getVitorias());
        assertEquals(0, visitante.getEmpates());
        assertEquals(1, visitante.getDerrotas());
        assertEquals(1, visitante.getGolsMarcados());
        assertEquals(3, visitante.getGolsSofridos());
        assertEquals(-2, visitante.getSaldoGols());
    }

    @Test
    @DisplayName("Registrar empate e atualizar participantes")
    void deveRegistrarResultadoEmpate() {
        Partida partida = new Partida(mandante, visitante);
        partida.registrarResultado(2, 2);

        assertEquals(2, partida.getGolsMandante());
        assertEquals(2, partida.getGolsVisitante());
        assertTrue(partida.isJogada());

        assertEquals(1, mandante.getPontos());
        assertEquals(1, mandante.getEmpates());
        assertEquals(1, visitante.getPontos());
        assertEquals(1, visitante.getEmpates());
    }

    @Test
    @DisplayName("Exceção por registrar resultado duas vezes")
    void excecaoRegistroDuplicado() {
        Partida partida = new Partida(mandante, visitante);
        partida.registrarResultado(1, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            partida.registrarResultado(2, 0);
        });
    }

    @Test
    @DisplayName("Exceção se gols mandante for negativo")
    void excecaoGolsMandanteNegativo() {
        Partida partida = new Partida(mandante, visitante);
        assertThrows(IllegalArgumentException.class, () -> {
            partida.registrarResultado(-1, 0);
        });
    }

    @Test
    @DisplayName("Exceção se gols visitante for negativo")
    void excecaoVisitanteNegativo() {
        Partida partida = new Partida(mandante, visitante);
        assertThrows(IllegalArgumentException.class, () -> {
            partida.registrarResultado(0, -1);
        });
    }

    @Test
    @DisplayName("Teste equals e hashCode")
    void duasPartidasDiferentesDevemSerDesiguais() {
        Partida partida1 = new Partida(mandante, visitante);
        Partida partida2 = new Partida(mandante, visitante); // Outra instância, outro UUID

        assertNotEquals(partida1, partida2);
        assertNotEquals(partida1.hashCode(), partida2.hashCode());

        assertEquals(partida1, partida1);
        assertEquals(partida1.hashCode(), partida1.hashCode());

        assertNotEquals(null, partida1);
    }
}