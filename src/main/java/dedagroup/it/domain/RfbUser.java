package dedagroup.it.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A RfbUser.
 */
@Entity
@Table(name = "rfb_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RfbUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @OneToOne
    @JoinColumn(unique = true)
    private RfbLocation locationName;

    @OneToMany(mappedBy = "rfbUser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RfbEventAttendance> rfbEventAttendances = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public RfbUser userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RfbLocation getLocationName() {
        return locationName;
    }

    public RfbUser locationName(RfbLocation rfbLocation) {
        this.locationName = rfbLocation;
        return this;
    }

    public void setLocationName(RfbLocation rfbLocation) {
        this.locationName = rfbLocation;
    }

    public Set<RfbEventAttendance> getRfbEventAttendances() {
        return rfbEventAttendances;
    }

    public RfbUser rfbEventAttendances(Set<RfbEventAttendance> rfbEventAttendances) {
        this.rfbEventAttendances = rfbEventAttendances;
        return this;
    }

    public RfbUser addRfbEventAttendance(RfbEventAttendance rfbEventAttendance) {
        this.rfbEventAttendances.add(rfbEventAttendance);
        rfbEventAttendance.setRfbUser(this);
        return this;
    }

    public RfbUser removeRfbEventAttendance(RfbEventAttendance rfbEventAttendance) {
        this.rfbEventAttendances.remove(rfbEventAttendance);
        rfbEventAttendance.setRfbUser(null);
        return this;
    }

    public void setRfbEventAttendances(Set<RfbEventAttendance> rfbEventAttendances) {
        this.rfbEventAttendances = rfbEventAttendances;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RfbUser)) {
            return false;
        }
        return id != null && id.equals(((RfbUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RfbUser{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            "}";
    }
}
