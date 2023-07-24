package com.example;

import org.example.tak.convertor.CelsiusConvertor;
import org.example.tak.convertor.Convertor;
import org.example.tak.convertor.FahrenheitConvertor;
import org.example.tak.convertor.KelvinConvertor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ConvertorTests {

    @Nested
    public class KelvinConvertorTests {
        @Test
        public void convertFromCelsiusTest() {
            Convertor convertor = new KelvinConvertor();
            String temperature = "26C";

            String kelvinTemp = convertor.convert(temperature);

            Assertions.assertEquals("299K", kelvinTemp);
        }

        @Test
        public void convertFromFohrengeitTest() {
            Convertor convertor = new KelvinConvertor();
            String temperature = "26F";

            String kelvinTemp = convertor.convert(temperature);

            Assertions.assertEquals("269K", kelvinTemp);
        }
    }

    @Nested
    public class CelsiusConvertorTests {
        @Test
        public void convertFromKelvinTest() {
            Convertor convertor = new CelsiusConvertor();
            String temperature = "293K";

            String kelvinTemp = convertor.convert(temperature);

            Assertions.assertEquals("19C", kelvinTemp);
        }

        @Test
        public void convertFromFahrenheitTest() {
            Convertor convertor = new CelsiusConvertor();
            String temperature = "26F";

            String kelvinTemp = convertor.convert(temperature);

            Assertions.assertEquals("-3C", kelvinTemp);
        }
    }

    @Nested
    public class FahrenheitConvertorTests {
        @Test
        public void convertFromKelvinTest() {
            Convertor convertor = new FahrenheitConvertor();
            String temperature = "293K";

            String kelvinTemp = convertor.convert(temperature);

            Assertions.assertEquals("67F", kelvinTemp);
        }

        @Test
        public void convertFromCelsiusTest() {
            Convertor convertor = new FahrenheitConvertor();
            String temperature = "26C";

            String kelvinTemp = convertor.convert(temperature);

            Assertions.assertEquals("78F", kelvinTemp);
        }
    }
}
