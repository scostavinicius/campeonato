package src.competicao.service;

import src.competicao.model.core.Participante;
import src.competicao.model.core.Tabela;
import src.competicao.model.core.Time;
import src.competicao.util.Cor;

import java.util.List;
import java.util.Objects;

public class Display {
    public static String getStringCorTime(Time time) throws NullPointerException {
        Objects.requireNonNull(time, "O time não pode ser nulo.");

        StringBuilder sb = new StringBuilder();

        if (time.getCor3() == null || time.getCor3().isEmpty()) {
            sb.append(time.getCor1()).append(" ").append(Cor.RESET);
            sb.append(time.getCor2()).append(" ").append(Cor.RESET);
            sb.append(time.getCor1()).append(" ").append(Cor.RESET);
        } else {
            sb.append(time.getCor1()).append(" ").append(Cor.RESET);
            sb.append(time.getCor2()).append(" ").append(Cor.RESET);
            sb.append(time.getCor3()).append(" ").append(Cor.RESET);
        }

        return sb.toString();
    }

    // TODO: IMPRIMIR LIGA / GRUPO
    public static void displayTabela(Tabela tabela) throws NullPointerException {
        Objects.requireNonNull(tabela, "A tabela não pode ser nula.");

        List<Participante> classificacao = tabela.getClassificacao();

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

            if (posicao <= tabela.getQtdClassificados()) {
                corPosicao = Cor.FUNDO_VERDE + " " + Cor.RESET;
            } else if (posicao > (classificacao.size() - tabela.getQtdRebaixados())) {
                corPosicao = Cor.FUNDO_VERMELHO + " " + Cor.RESET;
            }

            System.out.print(corPosicao);
            System.out.print(Cor.FUNDO_CINZA_CLARO + Cor.TEXT_PRETO_BOLD);

            System.out.printf("%3s %3s %-20s %4s %4s %4s %4s %4s %4s %4s %4s %7s%n",
                              posicao,
                              getStringCorTime(participante.getTime()) +
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

        if (tabela.getQtdClassificados() > 0) {
            System.out.println(
                    Cor.FUNDO_VERDE + "   " + Cor.RESET + " " + tabela.getNomeClassificacao());
        }

        if (tabela.getQtdRebaixados() > 0) {
            System.out.println(Cor.FUNDO_VERMELHO + "   " + Cor.RESET + " Rebaixamento");
        }
    }
}
