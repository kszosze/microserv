package org.microf.service.mapper;

import org.microf.domain.*;
import org.microf.service.dto.UKAddressDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity UKAddress and its DTO UKAddressDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UKAddressMapper {

    UKAddressDTO uKAddressToUKAddressDTO(UKAddress uKAddress);

    List<UKAddressDTO> uKAddressesToUKAddressDTOs(List<UKAddress> uKAddresses);

    UKAddress uKAddressDTOToUKAddress(UKAddressDTO uKAddressDTO);

    List<UKAddress> uKAddressDTOsToUKAddresses(List<UKAddressDTO> uKAddressDTOs);
}
