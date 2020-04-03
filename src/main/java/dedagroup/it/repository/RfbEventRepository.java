package dedagroup.it.repository;

import dedagroup.it.domain.RfbEvent;
import dedagroup.it.domain.RfbLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

/**
 * Spring Data  repository for the RfbEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RfbEventRepository extends JpaRepository<RfbEvent, Long> {
    RfbEvent findByRfbLocationAndEventDate(RfbLocation location, ZonedDateTime date);
}
