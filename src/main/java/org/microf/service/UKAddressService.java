package org.microf.service;

import org.microf.service.dto.UKAddressDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing UKAddress.
 */
public interface UKAddressService {

 
    /**
     *  Get the "id" uKAddress.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    UKAddressDTO findOne(String id);

}
