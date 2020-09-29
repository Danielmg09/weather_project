package client.utils;

import com.tecnara.weather.client.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilsTest {
    @Test
    public void checkerTestOK() {
        //1. Inicializacion del entorno
        String test = "{\"lon\":46.07, \"lat\":2.10}";
        //2. Ejecucion del codigo
        boolean result = Utils.checker(test);
        //3. Comprobacion de resultado
        Assertions.assertEquals(true, result);
    }

    @Test
    public void checkFormatTestLetterNotOK() {
        //1. Inicializacion del entorno
        String test = "{\"lon\":tr, \"lat\":tr}";
        //2. Ejecucion del codigo
        boolean result = Utils.checker(test);
        //3. Comprobacion de resultado
        Assertions.assertEquals(false, result);
    }
}
