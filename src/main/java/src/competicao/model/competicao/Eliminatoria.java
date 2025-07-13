package src.competicao.model.competicao;

import java.util.ArrayList;
import java.util.List;

public class Eliminatoria {
    private List<FaseEliminatoria> fases;
    private Integer faseAtualIndex;

    public Eliminatoria() {
        this.fases = new ArrayList<>();
        this.faseAtualIndex = -1;
    }

    public List<FaseEliminatoria> getFases() {
        return fases;
    }

    public FaseEliminatoria getFaseAtual() {
        if (faseAtualIndex >= 0 && faseAtualIndex < fases.size()) {
            return fases.get(faseAtualIndex);
        }

        return null;
    }
}
