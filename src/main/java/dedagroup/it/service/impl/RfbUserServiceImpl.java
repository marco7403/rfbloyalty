package dedagroup.it.service.impl;

import dedagroup.it.service.RfbUserService;
import dedagroup.it.domain.RfbUser;
import dedagroup.it.repository.RfbUserRepository;
import dedagroup.it.service.dto.RfbUserDTO;
import dedagroup.it.service.mapper.RfbUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link RfbUser}.
 */
@Service
@Transactional
public class RfbUserServiceImpl implements RfbUserService {

    private final Logger log = LoggerFactory.getLogger(RfbUserServiceImpl.class);

    private final RfbUserRepository rfbUserRepository;

    private final RfbUserMapper rfbUserMapper;

    public RfbUserServiceImpl(RfbUserRepository rfbUserRepository, RfbUserMapper rfbUserMapper) {
        this.rfbUserRepository = rfbUserRepository;
        this.rfbUserMapper = rfbUserMapper;
    }

    /**
     * Save a rfbUser.
     *
     * @param rfbUserDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RfbUserDTO save(RfbUserDTO rfbUserDTO) {
        log.debug("Request to save RfbUser : {}", rfbUserDTO);
        RfbUser rfbUser = rfbUserMapper.toEntity(rfbUserDTO);
        rfbUser = rfbUserRepository.save(rfbUser);
        return rfbUserMapper.toDto(rfbUser);
    }

    /**
     * Get all the rfbUsers.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RfbUserDTO> findAll() {
        log.debug("Request to get all RfbUsers");
        return rfbUserRepository.findAll().stream()
            .map(rfbUserMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one rfbUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RfbUserDTO> findOne(Long id) {
        log.debug("Request to get RfbUser : {}", id);
        return rfbUserRepository.findById(id)
            .map(rfbUserMapper::toDto);
    }

    /**
     * Delete the rfbUser by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RfbUser : {}", id);
        rfbUserRepository.deleteById(id);
    }
}
