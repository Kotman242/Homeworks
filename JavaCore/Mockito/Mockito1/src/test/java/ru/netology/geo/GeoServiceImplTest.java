package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;


public class GeoServiceImplTest {

    @MethodSource("methodSource")
    @ParameterizedTest
    public void byIpTest(String ip, Location expected) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location result = geoService.byIp(ip);
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void byCoordinatesTest(){
        double latitude = 3.11, longitude = 1.54;
        GeoServiceImpl geoService = new GeoServiceImpl();
        Assertions.assertThrows(RuntimeException.class,()-> {
            geoService.byCoordinates(latitude,longitude);
        });
    }


    public static Stream<Arguments> methodSource() {
        return Stream.of(
                Arguments.of("172.0.32.11",new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149",new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.0.00.00",new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.00.000.000",new Location("New York", Country.USA, null,  0)),
                Arguments.of("127.0.0.1",new Location(null, null, null, 0)),
                Arguments.of("000.0.0.00", null)
                );

    }
}
