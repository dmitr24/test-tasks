package siberteam.tests;

import sbierteam.tests.task.convertor.CelsiusConvertor;
import sbierteam.tests.task.convertor.FahrenheitConvertor;
import sbierteam.tests.task.convertor.KelvinConvertor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import sbierteam.tests.task.convertor.data.CelsiusTemperature;
import sbierteam.tests.task.convertor.data.FahrenheitTemperature;
import sbierteam.tests.task.convertor.data.KelvinTemperature;

public class ConvertorTests {
    @Nested
    class KelvinConvertorTests {
        @Test
        void convertFromCelsiusTest() {
            CelsiusTemperature temperature = new CelsiusTemperature(26);

            KelvinTemperature kelvinTemperature = KelvinConvertor.convert(temperature);

            Assertions.assertEquals("299K", kelvinTemperature.toString());
        }

        @Test
        void convertFromFahrenheitTest() {
            FahrenheitTemperature temperature = new FahrenheitTemperature(26);

            KelvinTemperature kelvinTemperature = KelvinConvertor.convert(temperature);

            Assertions.assertEquals("269K", kelvinTemperature.toString());
        }
    }

    @Nested
    class CelsiusConvertorTests {
        @Test
        void convertFromKelvinTest() {
            KelvinTemperature temperature = new KelvinTemperature(293);

            CelsiusTemperature celsiusTemperature = CelsiusConvertor.convert(temperature);

            Assertions.assertEquals("19C", celsiusTemperature.toString());
        }

        @Test
        void convertFromFahrenheitTest() {
            FahrenheitTemperature temperature = new FahrenheitTemperature(26);

            CelsiusTemperature celsiusTemperature = CelsiusConvertor.convert(temperature);

            Assertions.assertEquals("-3C", celsiusTemperature.toString());
        }
    }

    @Nested
    class FahrenheitConvertorTests {
        @Test
        void convertFromKelvinTest() {
            KelvinTemperature temperature = new KelvinTemperature(293);

            FahrenheitTemperature fahrenheitTemperature = FahrenheitConvertor.convert(temperature);

            Assertions.assertEquals("67F", fahrenheitTemperature.toString());
        }

        @Test
        void convertFromCelsiusTest() {
            CelsiusTemperature temperature = new CelsiusTemperature(26);

            FahrenheitTemperature fahrenheitTemperature = FahrenheitConvertor.convert(temperature);

            Assertions.assertEquals("78F", fahrenheitTemperature.toString());
        }
    }
}
