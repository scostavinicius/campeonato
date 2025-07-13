// src/competicao/main/Main.java
package src.competicao;

import src.competicao.model.core.Participante;
import src.competicao.model.core.Tabela;
import src.competicao.model.core.Time;
import src.competicao.service.Display;
import src.competicao.util.Cor;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando o Teste de Tabela e Display!");

        // --- 1. Criar Times (agora passando as constantes completas de Cor) ---
        System.out.println("\n--- Criando Times ---");
        // Passa as constantes ANSI completas (Cor.TEXT_VERMELHO, Cor.FUNDO_PRETO, etc.)
        Time timeVasco = new Time("Vasco", Cor.FUNDO_BRANCO, Cor.FUNDO_PRETO);
        Time timeCorinthians = new Time("Corinthians", Cor.FUNDO_PRETO, Cor.FUNDO_BRANCO);
        Time timePalmeiras = new Time("Palmeiras", Cor.FUNDO_VERDE_VIRIDIAN, Cor.FUNDO_BRANCO);
        // Time com 3 cores de fundo para teste
        Time timeSaoPaulo =
                new Time("São Paulo", Cor.FUNDO_VERMELHO_ESCURO, Cor.FUNDO_BRANCO, Cor.FUNDO_PRETO);
        Time timeGremio =
                new Time("Grêmio", Cor.FUNDO_PRETO, Cor.FUNDO_BRANCO, Cor.FUNDO_CIANO);
        Time timeInternacional = new Time("Internacional", Cor.FUNDO_VERMELHO, Cor.FUNDO_BRANCO);
        Time timeCruzeiro = new Time("Cruzeiro", Cor.FUNDO_AZUL_MARINHO, Cor.FUNDO_BRANCO);

        System.out.println("Exemplo de impressão de cores do Time:");
        System.out.println(
                Display.getStringCorTime(timeVasco) + " " + timeVasco.getNome());
        System.out.println(
                Display.getStringCorTime(timeSaoPaulo) + " " + timeSaoPaulo.getNome());
        System.out.println(
                Display.getStringCorTime(timeCruzeiro) + " " + timeCruzeiro.getNome());


        // Restante do código do Main permanece o mesmo...
        // --- 2. Criar Participantes para a Tabela ---
        System.out.println("\n--- Criando Participantes ---");
        Participante pFlamengo = new Participante(timeVasco);
        Participante pCorinthians = new Participante(timeCorinthians);
        Participante pPalmeiras = new Participante(timePalmeiras);
        Participante pSaoPaulo = new Participante(timeSaoPaulo);
        Participante pGremio = new Participante(timeGremio);
        Participante pInternacional = new Participante(timeInternacional);
        Participante pCruzeiro = new Participante(timeCruzeiro);


        // --- 3. Criar uma Tabela ---
        System.out.println("\n--- Criando Tabela e Adicionando Participantes ---");
        Tabela minhaTabela = new Tabela();
        minhaTabela.setQtdClassificados(2, "Libertadores");
        minhaTabela.setQtdRebaixados(2);

        minhaTabela.adicionarParticipante(pFlamengo);
        minhaTabela.adicionarParticipante(pCorinthians);
        minhaTabela.adicionarParticipante(pPalmeiras);
        minhaTabela.adicionarParticipante(pSaoPaulo);
        minhaTabela.adicionarParticipante(pGremio);
        minhaTabela.adicionarParticipante(pInternacional);
        minhaTabela.adicionarParticipante(pCruzeiro);

        try {
            minhaTabela.adicionarParticipante(pFlamengo);
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "DEBUG: Erro esperado ao adicionar participante duplicado: " + e.getMessage());
        }

        System.out.println(
                "Classificados configurados: " + minhaTabela.getQtdClassificados() + " (" +
                minhaTabela.getNomeClassificacao() + ")");
        System.out.println("Rebaixados configurados: " + minhaTabela.getQtdRebaixados());

        // --- 4. Simular Partidas e Atualizar Participantes (diretamente) ---
        System.out.println("\n--- Simulando Partidas e Atualizando Participantes ---");

//        Partida p1 = new Partida(UUID.randomUUID(), timeFlamengo, timeCorinthians);
//        p1.registrarResultado(3, 1);
//        pFlamengo.adicionarPartida(p1);
//        pCorinthians.adicionarPartida(p1);
//        System.out.println(timeFlamengo.getNome() + " vence " + timeCorinthians.getNome() + " 3x1");
//
//
//        Partida p2 = new Partida(UUID.randomUUID(), timePalmeiras, timeSaoPaulo);
//        p2.registrarResultado(0, 0);
//        pPalmeiras.adicionarPartida(p2);
//        pSaoPaulo.adicionarPartida(p2);
//        System.out.println(timePalmeiras.getNome() + " empata com " + timeSaoPaulo.getNome() + " 0x0");
//
//        Partida p3 = new Partida(UUID.randomUUID(), timeGremio, timeInternacional);
//        p3.registrarResultado(2, 1);
//        pGremio.adicionarPartida(p3);
//        pInternacional.adicionarPartida(p3);
//        System.out.println(timeGremio.getNome() + " vence " + timeInternacional.getNome() + " 2x1");
//
//        Partida p4 = new Partida(UUID.randomUUID(), timeCorinthians, timePalmeiras);
//        p4.registrarResultado(2, 0);
//        pCorinthians.adicionarPartida(p4);
//        pPalmeiras.adicionarPartida(p4);
//        System.out.println(timeCorinthians.getNome() + " vence " + timePalmeiras.getNome() + " 2x0");
//
//        Partida p5 = new Partida(UUID.randomUUID(), timeSaoPaulo, timeGremio);
//        p5.registrarResultado(1, 1);
//        pSaoPaulo.adicionarPartida(p5);
//        pGremio.adicionarPartida(p5);
//        System.out.println(timeSaoPaulo.getNome() + " empata com " + timeGremio.getNome() + " 1x1");
//
//        Partida p6 = new Partida(UUID.randomUUID(), timeInternacional, timeFlamengo);
//        p6.registrarResultado(0, 3);
//        pInternacional.adicionarPartida(p6);
//        pFlamengo.adicionarPartida(p6);
//        System.out.println(timeInternacional.getNome() + " perde para " + timeFlamengo.getNome() + " 0x3");
//
//        Partida p7 = new Partida(UUID.randomUUID(), timeCruzeiro, timeCorinthians);
//        p7.registrarResultado(1, 2);
//        pCruzeiro.adicionarPartida(p7);
//        pCorinthians.adicionarPartida(p7);
//        System.out.println(timeCruzeiro.getNome() + " perde para " + timeCorinthians.getNome() + " 1x2");


        // --- 5. Exibir a Tabela ---
        System.out.println("\n--- Tabela Final ---");
        Display.displayTabela(minhaTabela);

        // --- 6. Buscar participante por nome (Exemplo) ---
        System.out.println("\n--- Buscando Participante ---");
        Participante palmeirasNaTabela = minhaTabela.getParticipantePorNome("Palmeiras");
        if (palmeirasNaTabela != null) {
            System.out.println(
                    "Encontrado: " + palmeirasNaTabela.getTime().getNome() + " - Pontos: " +
                    palmeirasNaTabela.getPontos());
        } else {
            System.out.println("Palmeiras não encontrado na tabela.");
        }

        System.out.println("\nTeste de Tabela e Display Concluído!");
    }
}