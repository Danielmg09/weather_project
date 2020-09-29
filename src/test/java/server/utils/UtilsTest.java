package server.utils;

import com.tecnara.weather.server.domain.Coordinates;
import com.tecnara.weather.server.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilsTest {
    @Test
    public void parseCoordinatesTestOk(){
        //1. Inicializacion del entorno
        Coordinates expected = new Coordinates(87.54f,62.41f);
        String coordinates = "{\"lon\":" + 62.41 + ", \"lat\":" + 87.54 + "}";
        //2. Ejecucion del codigo
        Coordinates result = Utils.parseCoordinates(coordinates);
        //3. Compobacion del resultado
        Assertions.assertEquals(expected, result);
    }
}
