package ru.netology.sender;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

public class MessageSenderImplTest {
    @MethodSource("methodSource")
    @ParameterizedTest
    public void sendTest(String ip, Location location, String str) {
        HashMap<String, String> map = Mockito.mock(HashMap.class);
        Mockito.when(map.get(IP_ADDRESS_HEADER)).
                thenReturn(ip);
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(map.get(IP_ADDRESS_HEADER)))
                .thenReturn(location);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(location.getCountry()))
                .thenReturn(str);
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        assertEquals(messageSender.send(map), str);

    }

    public static Stream<Arguments> methodSource() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        return Stream.of(Arguments.of("96.44.183.149", new Location("Moscow", Country.RUSSIA, "Lenina", 15), "Welcome"),
                Arguments.of("172.0.32.11", new Location("New York", Country.USA, " 10th Avenue", 32), "Добро пожаловать"));
    }
}
