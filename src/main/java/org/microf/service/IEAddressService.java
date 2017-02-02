package org.microf.service;

import org.microf.service.dto.IEAddressDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing IEAddress.
 */
public interface IEAddressService {

    /**
     * Save a iEAddress.
     *
     * @param iEAddressDTO the entity to save
     * @return the persisted entity
     */
    IEAddressDTO save(IEAddressDTO iEAddressDTO);

    /**
     *  Get all the iEAddresses.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<IEAddressDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" iEAddress.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    IEAddressDTO findOne(String id);

    /**
     *  Delete the "id" iEAddress.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
