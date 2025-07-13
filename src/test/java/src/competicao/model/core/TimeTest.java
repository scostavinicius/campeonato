package src.competicao.model.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.competicao.util.Cor;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da Classe Time")
public class TimeTest {

    @Test
    @DisplayName("Time com duas cores.")
    void timeDuasCores() {
        Time time = new Time("AzulVerde", Cor.FUNDO_AZUL_MARINHO, Cor.FUNDO_VERDE_VIRIDIAN);

        assertNotNull(time, "Time não deve ser nulo.");
        assertEquals("AzulVerde", time.getNome());
        assertEquals(Cor.FUNDO_AZUL_MARINHO, time.getCor1());
        assertEquals(Cor.FUNDO_VERDE_VIRIDIAN, time.getCor2());
        assertEquals(null, time.getCor3());
    }

    @Test
    @DisplayName("Time com três cores.")
    void timeTresCores() {
        Time time =
                new Time("BrancoCinzaPreto", Cor.FUNDO_BRANCO, Cor.FUNDO_CINZA, Cor.FUNDO_PRETO);

        assertNotNull(time, "Time não deve ser nulo.");
        assertEquals("BrancoCinzaPreto", time.getNome());
        assertEquals(Cor.FUNDO_BRANCO, time.getCor1());
        assertEquals(Cor.FUNDO_CINZA, time.getCor2());
        assertEquals(Cor.FUNDO_PRETO, time.getCor3());
    }

    @Test
    @DisplayName("Exceção por nome nulo")
    void excecaoNomeNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Time(null, Cor.FUNDO_AZUL_MARINHO, Cor.FUNDO_VERDE_VIRIDIAN);
        });
    }

    @Test
    @DisplayName("Exceção por nome vazio")
    void excecaoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Time(" ", Cor.FUNDO_AZUL_MARINHO, Cor.FUNDO_VERDE_VIRIDIAN);
        });
    }

    @Test
    @DisplayName("Equals e hashCode para nomes iguais")
    void testeEqualsHashCode() {
        Time time1 = new Time("Mesmo", Cor.FUNDO_AZUL, Cor.FUNDO_VERDE);
        Time time2 = new Time("Mesmo", Cor.FUNDO_VERMELHO, Cor.FUNDO_PRETO);
        Time time3 = new Time("Outro", Cor.FUNDO_AZUL, Cor.FUNDO_VERDE);

        assertEquals(time1, time2);
        assertEquals(time1.hashCode(), time2.hashCode());

        assertNotEquals(time1, time3);
        assertNotEquals(time1.hashCode(), time3.hashCode());
    }
}
