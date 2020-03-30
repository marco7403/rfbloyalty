package dedagroup.it.service.mapper;


import dedagroup.it.domain.*;
import dedagroup.it.service.dto.RfbUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RfbUser} and its DTO {@link RfbUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RfbUserMapper extends EntityMapper<RfbUserDTO, RfbUser> {


    @Mapping(target = "rfbEventAttendances", ignore = true)
    @Mapping(target = "removeRfbEventAttendance", ignore = true)
    RfbUser toEntity(RfbUserDTO rfbUserDTO);

    default RfbUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        RfbUser rfbUser = new RfbUser();
        rfbUser.setId(id);
        return rfbUser;
    }
}
