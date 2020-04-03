package dedagroup.it.service.mapper;


import dedagroup.it.domain.RfbEventAttendance;
import dedagroup.it.service.dto.RfbEventAttendanceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link RfbEventAttendance} and its DTO {@link RfbEventAttendanceDTO}.
 */
@Mapper(componentModel = "spring", uses = {RfbEventMapper.class, UserMapper.class})
public interface RfbEventAttendanceMapper extends EntityMapper<RfbEventAttendanceDTO, RfbEventAttendance> {

    @Mapping(source = "rfbEvent.id", target = "rfbEventId")
    @Mapping(source = "user.id", target = "userId")
    RfbEventAttendanceDTO toDto(RfbEventAttendance rfbEventAttendance);

    @Mapping(source = "rfbEventId", target = "rfbEvent")
    @Mapping(source = "userId", target = "user")
    RfbEventAttendance toEntity(RfbEventAttendanceDTO rfbEventAttendanceDTO);

    default RfbEventAttendance fromId(Long id) {
        if (id == null) {
            return null;
        }
        RfbEventAttendance rfbEventAttendance = new RfbEventAttendance();
        rfbEventAttendance.setId(id);
        return rfbEventAttendance;
    }
}
