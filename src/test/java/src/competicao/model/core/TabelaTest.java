package src.competicao.model.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.competicao.util.Cor;

import static org.junit.jupiter.api.Assertions.*;

public class TabelaTest {
    private Tabela tabela;
    private Time timeA, timeB, timeC, timeD;
    private Participante pA, pB, pC, pD;

    @BeforeEach
    void setUp() {
        tabela = new Tabela();

        timeA = new Time("TimeA", Cor.FUNDO_AZUL, Cor.FUNDO_BRANCO);
        timeB = new Time("TimeB", Cor.FUNDO_VERMELHO, Cor.FUNDO_PRETO);
        timeC = new Time("TimeC", Cor.FUNDO_VERDE, Cor.FUNDO_AMARELO);
        timeD = new Time("TimeD", Cor.FUNDO_ROXO, Cor.FUNDO_CINZA);

        pA = new Participante(timeA);
        pB = new Participante(timeB);
        pC = new Participante(timeC);
        pD = new Participante(timeD);
    }

    @Test
    @DisplayName("Criar Tabela vazia por padrão")
    void criarTabela() {
        assertTrue(tabela.getClassificacao().isEmpty(), "A tabela deve estar vazia ao ser criada");
        assertEquals(0, tabela.getQtdClassificados(), "QtdClassificados deve ser 0 por padrão");
        assertNull(tabela.getNomeClassificacao(), "NomeClassificacao deve ser nulo por padrão");
        assertEquals(0, tabela.getQtdRebaixados(), "QtdRebaixados deve ser 0 por padrão");
    }
}