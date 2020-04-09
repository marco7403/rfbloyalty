package dedagroup.it.bootstrap;

import dedagroup.it.domain.*;
import dedagroup.it.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static dedagroup.it.security.AuthoritiesConstants.*;

@Component
public class RfbBootstrap implements CommandLineRunner {

    private final RfbLocationRepository rfbLocationRepository;
    private final RfbEventRepository rfbEventRepository;
    private final RfbEventAttendanceRepository rfbEventAttendanceRepository;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public RfbBootstrap(RfbLocationRepository rfbLocationRepository, RfbEventRepository rfbEventRepository,
                        RfbEventAttendanceRepository rfbEventAttendanceRepository, UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.rfbLocationRepository = rfbLocationRepository;
        this.rfbEventRepository = rfbEventRepository;
        this.rfbEventAttendanceRepository = rfbEventAttendanceRepository;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }
    @Transactional
    @Override
    public void run(String... strings) throws Exception {

        // init RFB Locations
        if(rfbLocationRepository.count() == 0){
            //only load data if no data loaded
            initData();
        }

    }

    private void initData() {
        Authority authorityRunner = new Authority();
        authorityRunner.setName(RUNNER);
        Authority authorityOrganizer = new Authority();
        authorityOrganizer.setName(ORGANIZER);
        Authority authorityAdmin = new Authority();
        authorityAdmin.setName(ADMIN);
        Authority authorityAnonymous = new Authority();
        authorityAnonymous.setName(ANONYMOUS);

        Set<Authority> authorityList = new HashSet<>();
        authorityList.add(authorityAdmin);
        authorityList.add(authorityOrganizer);
        authorityList.add(authorityRunner);
        authorityList.add(authorityAnonymous);

        User rfbUser = new User();
        rfbUser.setId("1111");
        rfbUser.setFirstName("Johnny");
        rfbUser.setLogin("johnny");
        rfbUser.setEmail("johnny@runningforbrews.com");
        rfbUser.setActivated(true);
        rfbUser.setAuthorities(authorityList);
        userRepository.save(rfbUser);

        //load data
        RfbLocation aleAndWitch = getRfbLocation("St Pete - Ale and the Witch", DayOfWeek.MONDAY.getValue());

        userRepository.save(rfbUser);

        RfbEvent aleEvent = getRfbEvent(aleAndWitch);

        getRfbEventAttendance(rfbUser, aleEvent);

        RfbLocation ratc = getRfbLocation("St Pete - Right Around The Corner", DayOfWeek.TUESDAY.getValue());

        RfbEvent ratcEvent = getRfbEvent(ratc);

        getRfbEventAttendance(rfbUser, ratcEvent);

        RfbLocation stPeteBrew = getRfbLocation("St Pete - St Pete Brewing", DayOfWeek.WEDNESDAY.getValue());

        RfbEvent stPeteBrewEvent = getRfbEvent(stPeteBrew);

        getRfbEventAttendance(rfbUser, stPeteBrewEvent);

        RfbLocation yardOfAle = getRfbLocation("St Pete - Yard of Ale", DayOfWeek.THURSDAY.getValue());

        RfbEvent yardOfAleEvent = getRfbEvent(yardOfAle);

        getRfbEventAttendance(rfbUser, yardOfAleEvent);

        RfbLocation pourHouse = getRfbLocation("Tampa - Pour House", DayOfWeek.MONDAY.getValue());
        RfbLocation macDintons = getRfbLocation("Tampa - Mac Dintons", DayOfWeek.TUESDAY.getValue());

        RfbLocation satRun = getRfbLocation("Saturday Run for testing", DayOfWeek.SATURDAY.getValue());
    }


    private void getRfbEventAttendance(User rfbUser, RfbEvent rfbEvent) {
        RfbEventAttendance rfbAttendance = new RfbEventAttendance();
        rfbAttendance.setRfbEvent(rfbEvent);
        rfbAttendance.setUser(rfbUser);
        rfbAttendance.setEventAttendance(LocalDate.now());

        System.out.println(rfbAttendance.toString());

        rfbEventAttendanceRepository.save(rfbAttendance);
        rfbEventRepository.save(rfbEvent);
    }

    private RfbEvent getRfbEvent(RfbLocation rfbLocation) {
        RfbEvent rfbEvent = new RfbEvent();
        rfbEvent.setEventCode(UUID.randomUUID().toString());
        rfbEvent.setEventDate(ZonedDateTime.now()); // will not be on assigned day...
        rfbLocation.addRfbEvent(rfbEvent);
        rfbLocationRepository.save(rfbLocation);
        rfbEventRepository.save(rfbEvent);
        return rfbEvent;
    }

    private RfbLocation getRfbLocation(String locationName, int value) {
        RfbLocation rfbLocation = new RfbLocation();
        rfbLocation.setLocationName(locationName);
        rfbLocation.setRunDayOfWeek(value);
        rfbLocationRepository.save(rfbLocation);
        return rfbLocation;
    }
}

