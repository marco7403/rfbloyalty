package dedagroup.it.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link dedagroup.it.domain.RfbEventAttendance} entity.
 */
public class RfbEventAttendanceDTO implements Serializable {

    private Long id;

    private LocalDate eventAttendance;


    private Long rfbEventId;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEventAttendance() {
        return eventAttendance;
    }

    public void setEventAttendance(LocalDate eventAttendance) {
        this.eventAttendance = eventAttendance;
    }

    public Long getRfbEventId() {
        return rfbEventId;
    }

    public void setRfbEventId(Long rfbEventId) {
        this.rfbEventId = rfbEventId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RfbEventAttendanceDTO rfbEventAttendanceDTO = (RfbEventAttendanceDTO) o;
        if (rfbEventAttendanceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rfbEventAttendanceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RfbEventAttendanceDTO{" +
            "id=" + getId() +
            ", eventAttendance='" + getEventAttendance() + "'" +
            ", rfbEventId=" + getRfbEventId() +
            ", userId=" + getUserId() +
            "}";
    }
}
