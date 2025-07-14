package src.competicao.model.competicao;

import src.competicao.model.core.Participante;

import java.util.List;

public interface PontosCorridos {
    Participante getParticipantePorNome(String nome);

    List<Participante> getClassificacao();

    int getQtdClassificados();

    String getNomeClassificacao();

}
