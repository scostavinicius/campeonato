package src.competicao.model.competicao;

import src.competicao.model.core.Partida;

import java.util.List;

public class FaseEliminatoria {
    private List<Partida> partidas;
    private String nomeFase;

    // TODO: IMPLEMENTAR CONSTRUTOR QUE ATUALIZE O NOME DA FASE PELA QUANTIA DE PARTIDAS
    public FaseEliminatoria() {}

    public List<Partida> getPartidas() {
        return partidas;
    }

    public String getNomeFase() {
        return nomeFase;
    }
}
