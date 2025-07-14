package src.competicao.service;

import src.competicao.model.core.Time;
import src.competicao.util.Cor;

import java.util.Objects;

public class TimeService {
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
}
