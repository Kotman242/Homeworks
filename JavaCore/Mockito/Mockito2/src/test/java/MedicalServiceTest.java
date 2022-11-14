import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoRepository;
import ru.netology.patient.service.alert.SendAlertService;
import ru.netology.patient.service.medical.MedicalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

public class MedicalServiceTest {
    @MethodSource("methodSource")
    @ParameterizedTest
    public void checkTemperatureTest(String id, BigDecimal temperature, int number, PatientInfo patientInfo){
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);
        Mockito.when(patientInfoRepository.getById(id))
                .thenReturn(patientInfo);
        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        medicalService.checkTemperature(id,temperature);

        Mockito.verify(sendAlertService, Mockito.times(number)).send(Mockito.anyString());

    }


    public static Stream<Arguments> methodSource() {
        return Stream.of(Arguments.of(Arguments.of("id1",new BigDecimal("38.0"), 1,
                new PatientInfo("Семен", "Михайлов", LocalDate.of(1982, 1, 16),
                new HealthInfo(new BigDecimal("36.6"), new BloodPressure(125, 78))))),
                Arguments.of("id1",new BigDecimal("36.6"), 0,
                        new PatientInfo("Семен", "Михайлов", LocalDate.of(1982, 1, 16),
                        new HealthInfo(new BigDecimal("36.6"), new BloodPressure(125, 78))
                ))
        );
    }
}/*Arguments.of("id1",new BigDecimal("36.6"), 0,new PatientInfo("Семен", "Михайлов", LocalDate.of(1982, 1, 16),
                new HealthInfo(new BigDecimal("36.6"), new BloodPressure(125, 78))
                )), */
