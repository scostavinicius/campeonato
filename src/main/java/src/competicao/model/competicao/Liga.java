package src.competicao.model.competicao;

public class Liga extends PontosCorridos {
    private int qtdRebaixados;

    public Liga(String nome,
                int maxParticipantes,
                int qtdClassificados,
                String nomeClassificacao,
                int qtdRebaixados) {
        super(nome, maxParticipantes, qtdClassificados, nomeClassificacao);
        setQtdRebaixados(qtdRebaixados);
    }

    public int getQtdRebaixados() {
        return qtdRebaixados;
    }

    public void setMaxParticipantes(int maxParticipantes) {
        if (maxParticipantes < this.qtdClassificados + this.qtdRebaixados) {
            throw new IllegalArgumentException(
                    "A quantidade máxima  de participantes tem que ser maior que a quantidade de classificados mais a de rebaixados na Liga");
        }

        super.setMaxParticipantes(maxParticipantes);
    }

    public void setQtdClassificados(int qtdClassificados) {
        qtdClassificados = Math.max(0, qtdClassificados);

        if (qtdClassificados + this.qtdRebaixados > maxParticipantes) {
            throw new IllegalArgumentException(
                    "A quantidade de classificados mais a de rebaixados não pode ser maior que o máximo de participantes da Liga");
        }

        super.setQtdClassificados(qtdClassificados);
    }

    public void setClassificacao(int qtdClassificados, String nomeClassificacao) {
        if (qtdClassificados + this.qtdRebaixados > maxParticipantes) {
            throw new IllegalArgumentException(
                    "A quantidade de classificados mais a de rebaixados não pode ser maior que o máximo de participantes da Liga");
        }

        super.setClassificacao(qtdClassificados, nomeClassificacao);
    }

    public void setQtdRebaixados(int qtdRebaixados) {
        if (this.qtdClassificados + qtdRebaixados > maxParticipantes) {
            throw new IllegalArgumentException(
                    "A quantidade de classificados mais a de rebaixados não pode ser maior que o máximo de participantes da Liga");
        }

        this.qtdRebaixados = Math.max(0, qtdRebaixados);
    }
}
