package src.competicao.model.core;

import java.util.Objects;

public class Time {
    private String nome;
    private String cor1;
    private String cor2;
    private String cor3; // Opcional

    public Time(String nome, String cor1, String cor2) {
        this(nome, cor1, cor2, null);
    }

    public Time(String nome, String cor1, String cor2, String cor3) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do time não pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();

        if (cor1 == null || cor1.trim().isEmpty()) {
            throw new IllegalArgumentException("A cor1 do time não pode ser nulo ou vazio.");
        }
        this.cor1 = cor1;

        if (cor2 == null || cor2.trim().isEmpty()) {
            throw new IllegalArgumentException("A cor2 do time não pode ser nulo ou vazio.");
        }
        this.cor2 = cor2;

        this.cor3 = cor3;
    }

    public String getCor1() {
        return cor1;
    }

    public void setCor1(String cor1) {
        if (cor1 == null || cor1.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do time não pode ser nulo ou vazio.");
        }
        this.cor1 = cor1;
    }

    public String getCor2() {
        return cor2;
    }

    public void setCor2(String cor2) {
        if (cor2 == null || cor2.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do time não pode ser nulo ou vazio.");
        }
        this.cor2 = cor2;
    }

    public String getCor3() {
        return cor3;
    }

    public void setCor3(String cor3) {
        this.cor3 = cor3;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do time não pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();
    }

    @Override
    public String toString() {
        return "Time{" +
               "nome='" + nome + '\'' +
               ", cor1='" + cor1 + '\'' +
               ", cor2='" + cor2 + '\'' +
               ", cor3='" + cor3 + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return Objects.equals(nome, time.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }
}
