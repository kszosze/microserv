package org.microf.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.microf.service.IEAddressService;
import org.microf.web.rest.util.HeaderUtil;
import org.microf.web.rest.util.PaginationUtil;
import org.microf.service.dto.IEAddressDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing IEAddress.
 */
@RestController
@RequestMapping("/api")
public class IEAddressResource {

    private final Logger log = LoggerFactory.getLogger(IEAddressResource.class);

    private static final String ENTITY_NAME = "iEAddress";
        
    private final IEAddressService iEAddressService;

    public IEAddressResource(IEAddressService iEAddressService) {
        this.iEAddressService = iEAddressService;
    }

 
    /**
     * GET  /i-e-addresses : get all the iEAddresses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of iEAddresses in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/i-e-addresses")
    @Timed
    public ResponseEntity<List<IEAddressDTO>> getAllIEAddresses(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of IEAddresses");
        Page<IEAddressDTO> page = iEAddressService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/i-e-addresses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /i-e-addresses/:id : get the "id" iEAddress.
     *
     * @param id the id of the iEAddressDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the iEAddressDTO, or with status 404 (Not Found)
     */
    @GetMapping("/i-e-addresses/{id}")
    @Timed
    public ResponseEntity<IEAddressDTO> getIEAddress(@PathVariable String id) {
        log.debug("REST request to get IEAddress : {}", id);
        IEAddressDTO iEAddressDTO = iEAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(iEAddressDTO));
    }

}
