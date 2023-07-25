package siberteam.tests;

import sbierteam.tests.tak.convertor.CelsiusConvertor;
import sbierteam.tests.tak.convertor.Convertor;
import sbierteam.tests.tak.convertor.FahrenheitConvertor;
import sbierteam.tests.tak.convertor.KelvinConvertor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ConvertorTests {
    @Nested
    class KelvinConvertorTests {
        @Test
        void convertFromCelsiusTest() {
            Convertor convertor = new KelvinConvertor();
            String temperature = "26C";

            String kelvinTemp = convertor.convert(temperature);

            Assertions.assertEquals("299K", kelvinTemp);
        }

        @Test
        void convertFromFahrenheitTest() {
            Convertor convertor = new KelvinConvertor();
            String temperature = "26F";

            String kelvinTemp = convertor.convert(temperature);

            Assertions.assertEquals("269K", kelvinTemp);
        }
    }

    @Nested
    class CelsiusConvertorTests {
        @Test
        void convertFromKelvinTest() {
            Convertor convertor = new CelsiusConvertor();
            String temperature = "293K";

            String kelvinTemp = convertor.convert(temperature);

            Assertions.assertEquals("19C", kelvinTemp);
        }

        @Test
        void convertFromFahrenheitTest() {
            Convertor convertor = new CelsiusConvertor();
            String temperature = "26F";

            String kelvinTemp = convertor.convert(temperature);

            Assertions.assertEquals("-3C", kelvinTemp);
        }
    }

    @Nested
    class FahrenheitConvertorTests {
        @Test
        void convertFromKelvinTest() {
            Convertor convertor = new FahrenheitConvertor();
            String temperature = "293K";

            String kelvinTemp = convertor.convert(temperature);

            Assertions.assertEquals("67F", kelvinTemp);
        }

        @Test
        void convertFromCelsiusTest() {
            Convertor convertor = new FahrenheitConvertor();
            String temperature = "26C";

            String kelvinTemp = convertor.convert(temperature);

            Assertions.assertEquals("78F", kelvinTemp);
        }
    }
}
