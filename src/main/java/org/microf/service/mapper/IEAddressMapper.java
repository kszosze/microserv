package org.microf.service.mapper;

import org.microf.domain.*;
import org.microf.service.dto.IEAddressDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity IEAddress and its DTO IEAddressDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface IEAddressMapper {

    IEAddressDTO iEAddressToIEAddressDTO(IEAddress iEAddress);

    List<IEAddressDTO> iEAddressesToIEAddressDTOs(List<IEAddress> iEAddresses);

    IEAddress iEAddressDTOToIEAddress(IEAddressDTO iEAddressDTO);

    List<IEAddress> iEAddressDTOsToIEAddresses(List<IEAddressDTO> iEAddressDTOs);
}
