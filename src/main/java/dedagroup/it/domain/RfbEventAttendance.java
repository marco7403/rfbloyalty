package dedagroup.it.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A RfbEventAttendance.
 */
@Entity
@Table(name = "rfb_event_attendance")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RfbEventAttendance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_attendance")
    private LocalDate eventAttendance;

    @ManyToOne
    @JsonIgnoreProperties("rfbEventAttendances")
    private RfbEvent rfbEvent;

    @ManyToOne
    @JsonIgnoreProperties("rfbEventAttendances")
    private RfbUser rfbUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEventAttendance() {
        return eventAttendance;
    }

    public RfbEventAttendance eventAttendance(LocalDate eventAttendance) {
        this.eventAttendance = eventAttendance;
        return this;
    }

    public void setEventAttendance(LocalDate eventAttendance) {
        this.eventAttendance = eventAttendance;
    }

    public RfbEvent getRfbEvent() {
        return rfbEvent;
    }

    public RfbEventAttendance rfbEvent(RfbEvent rfbEvent) {
        this.rfbEvent = rfbEvent;
        return this;
    }

    public void setRfbEvent(RfbEvent rfbEvent) {
        this.rfbEvent = rfbEvent;
    }

    public RfbUser getRfbUser() {
        return rfbUser;
    }

    public RfbEventAttendance rfbUser(RfbUser rfbUser) {
        this.rfbUser = rfbUser;
        return this;
    }

    public void setRfbUser(RfbUser rfbUser) {
        this.rfbUser = rfbUser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RfbEventAttendance)) {
            return false;
        }
        return id != null && id.equals(((RfbEventAttendance) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RfbEventAttendance{" +
            "id=" + getId() +
            ", eventAttendance='" + getEventAttendance() + "'" +
            "}";
    }
}
