package src.competicao.util;

import java.util.Objects;

public class CLIUtil {
    public static boolean desejaContinuar(String message) {
        System.out.print(message + " (s/n) ");
        String resposta = ScanTipo.scanString("");

        while (!Objects.equals(resposta, "s") && !Objects.equals(resposta, "n")) {
            System.out.print(message + " (s/n) ");
            resposta = ScanTipo.scanString("");
        }

        return Objects.equals(resposta, "s");
    }
}
