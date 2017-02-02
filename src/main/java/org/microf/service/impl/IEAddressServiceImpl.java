package org.microf.service.impl;

import org.microf.service.IEAddressService;
import org.microf.domain.IEAddress;
import org.microf.repository.IEAddressRepository;
import org.microf.service.dto.IEAddressDTO;
import org.microf.service.mapper.IEAddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing IEAddress.
 */
@Service
public class IEAddressServiceImpl implements IEAddressService{

    private final Logger log = LoggerFactory.getLogger(IEAddressServiceImpl.class);
    
    private final IEAddressRepository iEAddressRepository;

    private final IEAddressMapper iEAddressMapper;

    public IEAddressServiceImpl(IEAddressRepository iEAddressRepository, IEAddressMapper iEAddressMapper) {
        this.iEAddressRepository = iEAddressRepository;
        this.iEAddressMapper = iEAddressMapper;
    }

    /**
     * Save a iEAddress.
     *
     * @param iEAddressDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public IEAddressDTO save(IEAddressDTO iEAddressDTO) {
        log.debug("Request to save IEAddress : {}", iEAddressDTO);
        IEAddress iEAddress = iEAddressMapper.iEAddressDTOToIEAddress(iEAddressDTO);
        iEAddress = iEAddressRepository.save(iEAddress);
        IEAddressDTO result = iEAddressMapper.iEAddressToIEAddressDTO(iEAddress);
        return result;
    }

    /**
     *  Get all the iEAddresses.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    public Page<IEAddressDTO> findAll(Pageable pageable) {
        log.debug("Request to get all IEAddresses");
        Page<IEAddress> result = iEAddressRepository.findAll(pageable);
        return result.map(iEAddress -> iEAddressMapper.iEAddressToIEAddressDTO(iEAddress));
    }

    /**
     *  Get one iEAddress by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public IEAddressDTO findOne(String id) {
        log.debug("Request to get IEAddress : {}", id);
        IEAddress iEAddress = iEAddressRepository.findOne(UUID.fromString(id));
        IEAddressDTO iEAddressDTO = iEAddressMapper.iEAddressToIEAddressDTO(iEAddress);
        return iEAddressDTO;
    }

    /**
     *  Delete the  iEAddress by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete IEAddress : {}", id);
        iEAddressRepository.delete(UUID.fromString(id));
    }
}
