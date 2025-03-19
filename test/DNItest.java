import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class DNItest {

    @ParameterizedTest
    @CsvSource({
            "00000000, T, true",
            "12345678, Z, true",
            "87654321, X, true",
            "00000000, A, false",
            "12345678, A, false"
    })
    void testComprobarDNI(String dni, char letra, boolean expected) {
        assertEquals(expected, Main.comprobarDNI(dni, letra));
    }

    @ParameterizedTest
    @CsvSource({
            "00000000, T",
            "12345678, Z",
            "87654321, X"
    })
    void testCalcularLetraDNI(String dni, char expectedLetter) {
        assertEquals(expectedLetter, Main.calcularLetraDNI(dni));
    }
}


