package dedagroup.it.service;

import dedagroup.it.domain.RfbEvent;
import dedagroup.it.domain.RfbLocation;
import dedagroup.it.repository.RfbEventRepository;
import dedagroup.it.repository.RfbLocationRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class RfbEventCodeService {
    private final Logger log = LoggerFactory.getLogger(RfbEventCodeService.class);

    private final RfbLocationRepository rfbLocationRepository;
    private final RfbEventRepository rfbEventRepository;

    public RfbEventCodeService(RfbLocationRepository rfbLocationRepository, RfbEventRepository rfbEventRepository) {
        this.rfbLocationRepository = rfbLocationRepository;
        this.rfbEventRepository = rfbEventRepository;
    }

    //@Scheduled(cron = "0 0 * * * ?") //run once per hour, at top of hour
    @Scheduled(cron = "0 * * * * ?") //run once per min
    //@Scheduled(cron = "* * * * * ?") //run once per sec
    public void generateRunEventCodes(){

        log.debug("Generating Events");

        List<RfbLocation> rfbLocations = rfbLocationRepository.findAllByRunDayOfWeek(LocalDate.now().getDayOfWeek().getValue());

        log.debug("Locations Found for Events: " + rfbLocations.size());

        rfbLocations.forEach(location -> {
            log.debug("Checking Events for location: " + location.getId() );
            RfbEvent existingEvent = rfbEventRepository.findByRfbLocationAndEventDate(location, ZonedDateTime.now());

            if(existingEvent == null) {
                log.debug("Event Not Found, Creating Event");
                //create event for day
                RfbEvent newEvent = new RfbEvent();
                newEvent.setRfbLocation(location);
                newEvent.setEventDate(ZonedDateTime.now());
                newEvent.setEventCode(RandomStringUtils.randomAlphanumeric(10).toUpperCase());

                rfbEventRepository.save(newEvent);

                log.debug("Created Event:" + newEvent.toString());
            } else {
                log.debug("Event exists for day");
            }
        });

    }
}
