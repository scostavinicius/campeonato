package src.competicao.model.competicao;

import src.competicao.model.core.Partida;
import src.competicao.util.ScanTipo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FaseEliminatoria {
    private List<Partida> partidas;
    private String nomeFase;

    public FaseEliminatoria(int qtdClassificados) {
        this.partidas = new ArrayList<>();
        setNomeFase(qtdClassificados);
    }

    public void adicionarPartida(Partida partida) {
        Objects.requireNonNull(partida, "A partida não pode ser nula.");

        if (partida.isJogada()) {
            throw new IllegalArgumentException(
                    "As partidas de uma não podem ter sigo jogadas antes de serem adicionadas.");
        }

        if (this.partidas.contains(partida)) {
            throw new IllegalArgumentException("Essa partida já foi adicionada.");
        }

        this.partidas.add(partida);
    }

    public void pedirResultadosPartidas() {
        for (Partida partida : partidas) {
            String nomeMandante = partida.getMandante().getTime().getNome();
            String nomeVisitante = partida.getVisitante().getTime().getNome();

            System.out.println("DIGITE O RESULTADO DA PARTIDA " + partida.toString());
            int golsMandante =
                    ScanTipo.scanIntMinimo("Digite a quantidade de gols do " + nomeMandante + ": ",
                                           0);
            int golsVisitante =
                    ScanTipo.scanIntMinimo("Digite a quantidade de gols do " + nomeVisitante + ": ",
                                           0);

            if (golsMandante == golsVisitante) {
                System.out.println("A PARTIDA FOI PARA A PRORROGAÇÃO");
                golsMandante +=
                        ScanTipo.scanIntMinimo(
                                "Digite a quantidade de gols do " + nomeMandante +
                                " na prorrogação: ", 0);
                golsVisitante +=
                        ScanTipo.scanIntMinimo(
                                "Digite a quantidade de gols do " + nomeVisitante +
                                " na prorrogação: ", 0);
            }

            if (golsMandante == golsVisitante) {
                System.out.println("A PARTIDA FOI PARA OS PENALTIS");

                // TODO: NÃO VOU FAZER A LÓGICA DE PÊNALTIS AGORA NÃO

                System.out.println("DIGITE QUEM GANHOU NOS PÊNALTIS");
                System.out.println("[1] " + nomeMandante);
                System.out.println("[2] " + nomeVisitante);
                int vencedor = ScanTipo.scanIntEmIntervalo("", 1, 2);

                if (vencedor == 1) {
                    golsMandante++;
                } else {
                    golsVisitante++;
                }
            }

            System.out.println();
            partida.registrarResultado(golsMandante, golsVisitante);
        }
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public String getNomeFase() {
        return nomeFase;
    }

    private void setNomeFase(int qtdClassificados) {
        if (qtdClassificados == 16) {
            this.nomeFase = "Oitavas de final";
        } else if (qtdClassificados == 8) {
            this.nomeFase = "Quartas de final";
        } else if (qtdClassificados == 4) {
            this.nomeFase = "Semi-final";
        } else if (qtdClassificados == 2) {
            this.nomeFase = "FINAL";
        } else throw new IllegalStateException(
                "setNomeFase deveria estar recebendo uma quantidade válida");
    }
}
