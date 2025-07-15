package src.competicao.service;

import src.competicao.model.competicao.Grupo;
import src.competicao.model.competicao.Liga;
import src.competicao.model.core.Participante;
import src.competicao.util.Cor;

import java.util.List;
import java.util.Objects;

public class DisplayService {
    public static void displayTabela(Liga liga) {
        Objects.requireNonNull(liga, "A liga não pode ser nula.");

        List<Participante> classificacao = liga.getClassificacao();

        if (classificacao.isEmpty()) {
            System.out.println("A tabela está vazia.");
            return;
        }

        System.out.printf(Cor.FUNDO_CINZA);
        System.out.printf("%77s%n", Cor.RESET);
        System.out.printf(Cor.FUNDO_CINZA + Cor.TEXT_PRETO_BOLD);
        System.out.printf("  TABELA DE CLASSIFICAÇÃO%52s%n", Cor.RESET);
        System.out.printf(Cor.FUNDO_CINZA);
        System.out.printf("%77s%n", Cor.RESET);

        System.out.print(Cor.FUNDO_CINZA + Cor.TEXT_PRETO_BOLD);
        System.out.printf("%1s %-3s %2s %-20s %4s %4s %4s %4s %4s %4s %4s %4s %7s%n",
                          "",
                          "POS",
                          "",
                          "TIME",
                          "PTS",
                          "JG",
                          "VIT",
                          "EMP",
                          "DER",
                          "GM",
                          "GS",
                          "SG",
                          Cor.RESET);

        for (int i = 0; i < classificacao.size(); i++) {
            Participante participante = classificacao.get(i);
            Integer posicao = i + 1;

            String corPosicao = Cor.RESET + " ";

            if (posicao <= liga.getQtdClassificados()) {
                corPosicao = Cor.FUNDO_VERDE + " " + Cor.RESET;
            } else if (posicao > (classificacao.size() - liga.getQtdRebaixados())) {
                corPosicao = Cor.FUNDO_VERMELHO + " " + Cor.RESET;
            }

            System.out.print(corPosicao);
            System.out.print(Cor.FUNDO_CINZA_CLARO + Cor.TEXT_PRETO_BOLD);

            System.out.printf("%3s %3s %-20s %4s %4s %4s %4s %4s %4s %4s %4s %7s%n",
                              posicao,
                              TimeService.getStringCorTime(participante.getTime()) +
                              Cor.FUNDO_CINZA_CLARO +
                              Cor.TEXT_PRETO,
                              participante.getTime().getNome(),
                              participante.getPontos(),
                              participante.getQtdPartidasJogadas(),
                              participante.getVitorias(),
                              participante.getEmpates(),
                              participante.getDerrotas(),
                              participante.getGolsMarcados(),
                              participante.getGolsSofridos(),
                              participante.getSaldoGols(), Cor.RESET);

            System.out.printf(Cor.FUNDO_CINZA);
            System.out.printf("%77s%n", Cor.RESET);
        }

        System.out.println(
                Cor.FUNDO_PRETO +
                Cor.TEXT_BRANCO_BOLD +
                " ".repeat(10) + liga.getNome() + " ".repeat(10) +
                Cor.RESET);

        if (liga.getQtdClassificados() > 0) {
            System.out.println(
                    Cor.FUNDO_VERDE + "   " + Cor.RESET + " " + liga.getNomeClassificacao());
        }

        if (liga.getQtdRebaixados() > 0) {
            System.out.println(Cor.FUNDO_VERMELHO + "   " + Cor.RESET + " Rebaixamento");
        }
    }

    public static void displayTabela(Grupo grupo) {
        Objects.requireNonNull(grupo, "O grupo não pode ser nula.");

        List<Participante> classificacao = grupo.getClassificacao();

        if (classificacao.isEmpty()) {
            System.out.println("A tabela está vazia.");
            return;
        }

        System.out.printf(Cor.FUNDO_CINZA);
        System.out.printf("%77s%n", Cor.RESET);
        System.out.printf(Cor.FUNDO_CINZA + Cor.TEXT_PRETO_BOLD);
        System.out.printf("  TABELA DE CLASSIFICAÇÃO%52s%n", Cor.RESET);
        System.out.printf(Cor.FUNDO_CINZA);
        System.out.printf("%77s%n", Cor.RESET);

        System.out.print(Cor.FUNDO_CINZA + Cor.TEXT_PRETO_BOLD);
        System.out.printf("%1s %-3s %2s %-20s %4s %4s %4s %4s %4s %4s %4s %4s %7s%n",
                          "",
                          "POS",
                          "",
                          "TIME",
                          "PTS",
                          "JG",
                          "VIT",
                          "EMP",
                          "DER",
                          "GM",
                          "GS",
                          "SG",
                          Cor.RESET);

        for (int i = 0; i < classificacao.size(); i++) {
            Participante participante = classificacao.get(i);
            Integer posicao = i + 1;

            String corPosicao = Cor.RESET + " ";

            if (posicao <= grupo.getQtdClassificados()) {
                corPosicao = Cor.FUNDO_VERDE + " " + Cor.RESET;
            }

            System.out.print(corPosicao);
            System.out.print(Cor.FUNDO_CINZA_CLARO + Cor.TEXT_PRETO_BOLD);

            System.out.printf("%3s %3s %-20s %4s %4s %4s %4s %4s %4s %4s %4s %7s%n",
                              posicao,
                              TimeService.getStringCorTime(participante.getTime()) +
                              Cor.FUNDO_CINZA_CLARO +
                              Cor.TEXT_PRETO,
                              participante.getTime().getNome(),
                              participante.getPontos(),
                              participante.getQtdPartidasJogadas(),
                              participante.getVitorias(),
                              participante.getEmpates(),
                              participante.getDerrotas(),
                              participante.getGolsMarcados(),
                              participante.getGolsSofridos(),
                              participante.getSaldoGols(), Cor.RESET);

            System.out.printf(Cor.FUNDO_CINZA);
            System.out.printf("%77s%n", Cor.RESET);
        }

        System.out.println(
                Cor.FUNDO_PRETO +
                Cor.TEXT_BRANCO_BOLD +
                " ".repeat(10) + grupo.getNome() + " ".repeat(10) +
                Cor.RESET);

        if (grupo.getQtdClassificados() > 0) {
            System.out.println(
                    Cor.FUNDO_VERDE + "   " + Cor.RESET + " " + grupo.getNomeClassificacao());
        }
    }
}
