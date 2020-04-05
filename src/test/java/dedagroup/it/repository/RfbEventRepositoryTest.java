package dedagroup.it.repository;

import dedagroup.it.RfbloyaltyApp;
import dedagroup.it.bootstrap.RfbBootstrap;
import dedagroup.it.domain.RfbEvent;
import dedagroup.it.domain.RfbLocation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RfbloyaltyApp.class})
public class RfbEventRepositoryTest extends AbstractRepositoryTest {
    @Before
    public void setUp() throws Exception {
        RfbBootstrap rfbBootstrap = new RfbBootstrap(rfbLocationRepository, rfbEventRepository,
            rfbEventAttendanceRepository, userRepository);
    }

    @Test
    public void findAllByRfbLocationAndEventDate() throws Exception {
        List<RfbLocation> rfbLocation = rfbLocationRepository.findAll();
        rfbLocation.stream().forEach(item -> System.out.println(item.getLocationName()));
        RfbLocation aleAndTheWitch = rfbLocationRepository.findByLocationName(rfbLocation.get(0).getLocationName());
        assertNotNull(aleAndTheWitch);
        List<RfbEvent> rfbEvents = rfbEventRepository.findAll();
        RfbEvent event = rfbEventRepository.findByRfbLocationAndEventDate(aleAndTheWitch, rfbEvents.get(0).getEventDate());
        assertNotNull(event);
    }

}
