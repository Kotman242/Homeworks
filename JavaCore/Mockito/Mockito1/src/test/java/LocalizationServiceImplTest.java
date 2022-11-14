import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LocalizationServiceImplTest {

    @MethodSource("methodSource")
    @ParameterizedTest
    public void localeTest(Country country, String s) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        assertEquals(localizationService.locale(country), s);

    }

    public static Stream<Arguments> methodSource() {
        return Stream.of(
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.USA, "Welcome")
        );
    }
}
