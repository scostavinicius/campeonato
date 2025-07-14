// src/competicao/main/Main.java
package src.competicao;

import src.competicao.model.competicao.Liga;
import src.competicao.model.core.Partida;
import src.competicao.model.core.Time;
import src.competicao.service.DisplayService;
import src.competicao.util.CLIUtil;
import src.competicao.util.Cor;
import src.competicao.util.ScanTipo;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String barraTraco = "-".repeat(77);
    private static final String barraIgual = "=".repeat(77);

    private static void decTraco(String message) {
        System.out.println(Cor.TEXT_VERDE_ESCURO_BOLD + barraTraco);
        System.out.println(message);
        System.out.println(barraTraco + Cor.RESET);
    }

    private static void decIgual(String message) {
        System.out.println(Cor.TEXT_VERDE_ESCURO_BOLD + barraIgual);
        System.out.println(message);
        System.out.println(barraIgual + Cor.RESET);
    }

    public static void main(String[] args) {
        decIgual("INÍCIO DO PROGRAMA");

        System.out.println(barraIgual);
        System.out.println("O que você deseja fazer?");

        while (true) {
            try {
                System.out.println("[1] Criar Copa");
                System.out.println("[2] Criar Liga");
                System.out.println("[3] SAIR");
                System.out.println();

                int opcao = ScanTipo.scanIntEmIntervalo("Digite sua opção: ", 1, 3);

                switch (opcao) {
                    case 1 -> fluxoCopa();
                    case 2 -> fluxoLiga();
                    default -> {
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro durante o programa: " + e.getMessage());
            }
        }
    }

    private static void fluxoCopa() {
    }

    private static void fluxoLiga() {
        Optional<Liga> novaLiga = criarLiga();

        if (novaLiga.isEmpty()) {
            System.out.println("A Liga não foi criada.");
            return;
        }

        Liga liga = novaLiga.get();

        decTraco("Liga criada com sucesso!");

        preencherLiga(liga);

        decTraco("Liga preenchido com sucesso!");

        DisplayService.displayTabela(liga);

        System.out.println("\n--- Partidas Geradas na Liga ---");
        List<Partida> partidas = liga.getPartidas();
        if (partidas.isEmpty()) {
            System.out.println("Nenhuma partida foi gerada.");
        } else {
            for (int i = 0; i < partidas.size(); i++) {
                System.out.println("Partida " + (i + 1) + ": " +
                                   partidas.get(i).toString());
            }
        }
    }

    public static Optional<Liga> criarLiga() {
        boolean ligaCriada = false;

        Liga liga = null;

        while (!ligaCriada) {
            boolean continuar = CLIUtil.desejaContinuar("Deseja continuar a criação de Liga?");

            if (!continuar) {
                return Optional.empty();
            }

            String nome = ScanTipo.scanString("Nome da liga: ");
            int maxParticipantes = ScanTipo.scanInt("Máximo de participantes da liga: ");
            int qtdClassificados = ScanTipo.scanInt("Quantidade de classificados da liga: ");

            String nomeClassificacao = null;
            if (qtdClassificados > 0) {
                nomeClassificacao = ScanTipo.scanString("Eles se classificarão para: ");
            }

            int qtdRebaixados = ScanTipo.scanInt("Quantidade de rebaixamentos da liga: ");

            try {
                liga = new Liga(nome,
                                maxParticipantes,
                                qtdClassificados,
                                nomeClassificacao,
                                qtdRebaixados);
                ligaCriada = true;
            } catch (Exception e) {
                System.out.println("Erro ao criar Liga: ");
                System.out.println(e.getMessage());
            }
        }

        return Optional.of(liga);
    }

    public static void preencherLiga(Liga liga) {
        int i = 1;

        while (liga.getParticipantes().size() < liga.getMaxParticipantes()) {
            adicionarParticipanteLiga(i, liga);
            System.out.println();

            i++;
        }
    }

    public static void adicionarParticipanteLiga(int iesimo, Liga liga) {
        String nome = ScanTipo.scanString("Nome do " + iesimo + "° time: ");

        System.out.println("Escolha até 3 cores para o time");
        imprimirCores();

        String cor1 = escolherCor();
        String cor2 = escolherCor();
        String cor3 = null;

        boolean querCor3 = CLIUtil.desejaContinuar("Deseja uma terceira cor?");

        if (querCor3) {
            cor3 = escolherCor();
        }

        Time time = new Time(nome, cor1, cor2, cor3);

        liga.adicionarParticipante(time);
    }

    private static void imprimirCores() {
        imprimirCor(Cor.FUNDO_BRANCO, "[1]BRANCO");
        imprimirCor(Cor.FUNDO_PRETO, "[2]PRETO");
        imprimirCor(Cor.FUNDO_CINZA, "[3]CINZA");
        imprimirCor(Cor.FUNDO_VERMELHO, "[4]VERMELHO");
        imprimirCor(Cor.FUNDO_VERDE, "[5]VERDE");

        System.out.println();

        imprimirCor(Cor.FUNDO_AZUL, "[6]AZUL");
        imprimirCor(Cor.FUNDO_LARANJA, "[7]LARANJA");
        imprimirCor(Cor.FUNDO_AMARELO, "[8]AMARELO");
        imprimirCor(Cor.FUNDO_CIANO, "[9]CIANO");
        imprimirCor(Cor.FUNDO_ROXO, "[10]ROXO");

        System.out.println();

        imprimirCor(Cor.FUNDO_ROSA, "[11]ROSA");
        imprimirCor(Cor.FUNDO_CINZA_CLARO, "[12]CINZA CLARO");
        imprimirCor(Cor.FUNDO_VERMELHO_ESCURO, "[13]VERMELHO ESCURO");
        imprimirCor(Cor.FUNDO_VERDE_ESCURO, "[14]VERDE ESCURO");
        imprimirCor(Cor.FUNDO_AZUL_MARINHO, "[15]AZUL MARINHO");

        System.out.println();

        imprimirCor(Cor.FUNDO_LARANJA_ESCURO, "[16]LARANJA ESCURO");
        imprimirCor(Cor.FUNDO_AMARELO_OURO, "[17]AMARELO OURO");
        imprimirCor(Cor.FUNDO_CIANO_ESCURO, "[18]CIANO ESCURO");
        imprimirCor(Cor.FUNDO_ROXO_ESCURO, "[19]ROXO ESCURO");
        imprimirCor(Cor.FUNDO_ROSA_ESCURO, "[20]ROSA ESCURO");

        System.out.println();
    }

    private static void imprimirCor(String cor, String nomeCor) {
        System.out.printf("%s   %-30s", cor, Cor.RESET + nomeCor);
    }

    private static String escolherCor() {
        int numCor = ScanTipo.scanIntEmIntervalo("Digite o número de uma das cores: ", 1, 20);

        return switch (numCor) {
            case 1 -> Cor.FUNDO_BRANCO;
            case 2 -> Cor.FUNDO_PRETO;
            case 3 -> Cor.FUNDO_CINZA;
            case 4 -> Cor.FUNDO_VERMELHO;
            case 5 -> Cor.FUNDO_VERDE;
            case 6 -> Cor.FUNDO_AZUL;
            case 7 -> Cor.FUNDO_LARANJA;
            case 8 -> Cor.FUNDO_AMARELO;
            case 9 -> Cor.FUNDO_CIANO;
            case 10 -> Cor.FUNDO_ROXO;
            case 11 -> Cor.FUNDO_ROSA;
            case 12 -> Cor.FUNDO_CINZA_CLARO;
            case 13 -> Cor.FUNDO_VERMELHO_ESCURO;
            case 14 -> Cor.FUNDO_VERDE_ESCURO;
            case 15 -> Cor.FUNDO_AZUL_MARINHO;
            case 16 -> Cor.FUNDO_LARANJA_ESCURO;
            case 17 -> Cor.FUNDO_AMARELO_OURO;
            case 18 -> Cor.FUNDO_CIANO_ESCURO;
            case 19 -> Cor.FUNDO_ROXO_ESCURO;
            case 20 -> Cor.FUNDO_ROSA_ESCURO;
            default -> {
                System.err.println(
                        "Erro: Número de cor fora do intervalo esperado. Retornando cor padrão.");
                yield Cor.RESET;
            }
        };
    }
}