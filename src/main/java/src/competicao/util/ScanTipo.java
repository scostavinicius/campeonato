package src.competicao.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScanTipo {
    private static final Scanner scanner = new Scanner(System.in);

    public static int scanInt(String message) {
        int inteiro;

        while (true) {
            System.out.print(message);

            try {
                inteiro = scanner.nextInt();
                scanner.nextLine();

                break;
            } catch (InputMismatchException e) {
                System.out.println("Digite um número inteiro.");
                scanner.nextLine();
            }
        }

        return inteiro;
    }

    public static int scanIntEmIntervalo(String message, int min, int max) {
        int inteiro;

        while (true) {
            inteiro = scanInt(message);
            if (inteiro >= min && inteiro <= max) {
                break;
            } else {
                System.out.println("Digite um número entre " + min + " e " + max + ".");
            }
        }

        return inteiro;
    }

    public static int scanIntPositivo(String message) {
        int inteiro;

        while (true) {
            inteiro = scanInt(message);
            if (inteiro < 0) {
                System.out.println("Digite um número positivo.");
            } else {
                break;
            }
        }

        return inteiro;
    }

    public static String scanString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
