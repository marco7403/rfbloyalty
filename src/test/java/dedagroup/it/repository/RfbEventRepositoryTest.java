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

import java.time.LocalDate;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RfbloyaltyApp.class})
public class RfbEventRepositoryTest extends AbstractRepositoryTest {
    @Before
    public void setUp() throws Exception {
        RfbBootstrap rfbBootstrap = new RfbBootstrap(rfbLocationRepository, rfbEventRepository,
            rfbEventAttendanceRepository, rfbUserRepository);
    }

    @Test
    public void findAllByRfbLocationAndEventDate() throws Exception {
        RfbLocation aleAndTheWitch = rfbLocationRepository.findByLocationName("Refined Soft Keyboard");
        assertNotNull(aleAndTheWitch);

        RfbEvent event = rfbEventRepository.findByRfbLocationAndEventDate(aleAndTheWitch, LocalDate.now());
        assertNotNull(event);

    }

}
