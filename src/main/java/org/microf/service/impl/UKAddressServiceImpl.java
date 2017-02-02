package org.microf.service.impl;

import org.microf.service.UKAddressService;
import org.microf.domain.UKAddress;
import org.microf.repository.UKAddressRepository;
import org.microf.service.dto.UKAddressDTO;
import org.microf.service.mapper.UKAddressMapper;
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
 * Service Implementation for managing UKAddress.
 */
@Service
public class UKAddressServiceImpl implements UKAddressService{

    private final Logger log = LoggerFactory.getLogger(UKAddressServiceImpl.class);
    
    private final UKAddressRepository uKAddressRepository;

    private final UKAddressMapper uKAddressMapper;

    public UKAddressServiceImpl(UKAddressRepository uKAddressRepository, UKAddressMapper uKAddressMapper) {
        this.uKAddressRepository = uKAddressRepository;
        this.uKAddressMapper = uKAddressMapper;
    }

    /**
     *  Get one uKAddress by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public UKAddressDTO findOne(String id) {
        log.debug("Request to get UKAddress : {}", id);
        UKAddress uKAddress = uKAddressRepository.findOne(UUID.fromString(id));
        UKAddressDTO uKAddressDTO = uKAddressMapper.uKAddressToUKAddressDTO(uKAddress);
        return uKAddressDTO;
    }

}
