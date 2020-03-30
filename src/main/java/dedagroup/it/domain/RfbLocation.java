package dedagroup.it.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A RfbLocation.
 */
@Entity
@Table(name = "rfb_location")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RfbLocation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "run_day_oj_week")
    private String runDayOjWeek;

    @OneToMany(mappedBy = "rfbLocation")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RfbEvent> rfbEvents = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public RfbLocation locationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getRunDayOjWeek() {
        return runDayOjWeek;
    }

    public RfbLocation runDayOjWeek(String runDayOjWeek) {
        this.runDayOjWeek = runDayOjWeek;
        return this;
    }

    public void setRunDayOjWeek(String runDayOjWeek) {
        this.runDayOjWeek = runDayOjWeek;
    }

    public Set<RfbEvent> getRfbEvents() {
        return rfbEvents;
    }

    public RfbLocation rfbEvents(Set<RfbEvent> rfbEvents) {
        this.rfbEvents = rfbEvents;
        return this;
    }

    public RfbLocation addRfbEvent(RfbEvent rfbEvent) {
        this.rfbEvents.add(rfbEvent);
        rfbEvent.setRfbLocation(this);
        return this;
    }

    public RfbLocation removeRfbEvent(RfbEvent rfbEvent) {
        this.rfbEvents.remove(rfbEvent);
        rfbEvent.setRfbLocation(null);
        return this;
    }

    public void setRfbEvents(Set<RfbEvent> rfbEvents) {
        this.rfbEvents = rfbEvents;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RfbLocation)) {
            return false;
        }
        return id != null && id.equals(((RfbLocation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RfbLocation{" +
            "id=" + getId() +
            ", locationName='" + getLocationName() + "'" +
            ", runDayOjWeek='" + getRunDayOjWeek() + "'" +
            "}";
    }
}
