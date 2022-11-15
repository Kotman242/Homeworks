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
import java.util.stream.Stream;

public class MedicalServiceTest {
    @MethodSource("methodSource2")
    @ParameterizedTest
    public void checkTemperatureTest(String id, BigDecimal temperature, int number) {
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        PatientInfo patientInfo = Mockito.spy(PatientInfo.class);
        HealthInfo healthInfo = Mockito.spy(HealthInfo.class);
        SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);
        Mockito.when(patientInfoRepository.getById(id))
                .thenReturn(patientInfo);
        Mockito.when(patientInfo.getHealthInfo())
                .thenReturn(healthInfo);
        Mockito.when(healthInfo.getNormalTemperature())
                .thenReturn(new BigDecimal("36.6"));
        Mockito.when(patientInfo.getId()).
                thenReturn(id);
        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        medicalService.checkTemperature(id, temperature);

        Mockito.verify(sendAlertService, Mockito.times(number)).send(String.format("Warning, patient with id: %s, need help", patientInfo.getId()));

    }

    @MethodSource("methodSource")
    @ParameterizedTest
    public void checkBloodPressureTest(String id, BloodPressure bloodPressure, int number) {
        BloodPressure bloodPressure1 = new BloodPressure(120, 80);
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        PatientInfo patientInfo = Mockito.spy(PatientInfo.class);
        HealthInfo healthInfo = Mockito.spy(HealthInfo.class);
        SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);
        Mockito.when(patientInfoRepository.getById(id))
                .thenReturn(patientInfo);
        Mockito.when(patientInfo.getHealthInfo())
                .thenReturn(healthInfo);
        Mockito.when(healthInfo.getBloodPressure())
                .thenReturn(bloodPressure1);
        Mockito.when(patientInfo.getId())
                .thenReturn(id);
        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);

        medicalService.checkBloodPressure(id, bloodPressure);


        Mockito.verify(sendAlertService, Mockito.times(number)).send(String.format("Warning, patient with id: %s, need help", patientInfo.getId()));
    }


    public static Stream<Arguments> methodSource() {
        return Stream.of(Arguments.of("id1", new BloodPressure(200, 140), 1),
                Arguments.of("id1", new BloodPressure(120, 80), 0));
    }

    public static Stream<Arguments> methodSource2() {
        return Stream.of(Arguments.of("id1", new BigDecimal("35.0"), 1),
                Arguments.of("id1", new BigDecimal("36.8"), 0)
        );
    }
}


