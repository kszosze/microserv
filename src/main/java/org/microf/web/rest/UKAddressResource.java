package org.microf.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.microf.service.UKAddressService;
import org.microf.web.rest.util.HeaderUtil;
import org.microf.web.rest.util.PaginationUtil;
import org.microf.service.dto.UKAddressDTO;
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
 * REST controller for managing UKAddress.
 */
@RestController
@RequestMapping("/api")
public class UKAddressResource {

    private final Logger log = LoggerFactory.getLogger(UKAddressResource.class);

    private static final String ENTITY_NAME = "uKAddress";
        
    private final UKAddressService uKAddressService;

    public UKAddressResource(UKAddressService uKAddressService) {
        this.uKAddressService = uKAddressService;
    }

    /**
     * POST  /u-k-addresses : Create a new uKAddress.
     *
     * @param uKAddressDTO the uKAddressDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new uKAddressDTO, or with status 400 (Bad Request) if the uKAddress has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/u-k-addresses")
    @Timed
    public ResponseEntity<UKAddressDTO> createUKAddress(@RequestBody UKAddressDTO uKAddressDTO) throws URISyntaxException {
        log.debug("REST request to save UKAddress : {}", uKAddressDTO);
        if (uKAddressDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new uKAddress cannot already have an ID")).body(null);
        }
        UKAddressDTO result = uKAddressService.save(uKAddressDTO);
        return ResponseEntity.created(new URI("/api/u-k-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /u-k-addresses : Updates an existing uKAddress.
     *
     * @param uKAddressDTO the uKAddressDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated uKAddressDTO,
     * or with status 400 (Bad Request) if the uKAddressDTO is not valid,
     * or with status 500 (Internal Server Error) if the uKAddressDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/u-k-addresses")
    @Timed
    public ResponseEntity<UKAddressDTO> updateUKAddress(@RequestBody UKAddressDTO uKAddressDTO) throws URISyntaxException {
        log.debug("REST request to update UKAddress : {}", uKAddressDTO);
        if (uKAddressDTO.getId() == null) {
            return createUKAddress(uKAddressDTO);
        }
        UKAddressDTO result = uKAddressService.save(uKAddressDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, uKAddressDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /u-k-addresses : get all the uKAddresses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of uKAddresses in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/u-k-addresses")
    @Timed
    public ResponseEntity<List<UKAddressDTO>> getAllUKAddresses(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of UKAddresses");
        Page<UKAddressDTO> page = uKAddressService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/u-k-addresses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /u-k-addresses/:id : get the "id" uKAddress.
     *
     * @param id the id of the uKAddressDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the uKAddressDTO, or with status 404 (Not Found)
     */
    @GetMapping("/u-k-addresses/{id}")
    @Timed
    public ResponseEntity<UKAddressDTO> getUKAddress(@PathVariable String id) {
        log.debug("REST request to get UKAddress : {}", id);
        UKAddressDTO uKAddressDTO = uKAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(uKAddressDTO));
    }

    /**
     * DELETE  /u-k-addresses/:id : delete the "id" uKAddress.
     *
     * @param id the id of the uKAddressDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/u-k-addresses/{id}")
    @Timed
    public ResponseEntity<Void> deleteUKAddress(@PathVariable String id) {
        log.debug("REST request to delete UKAddress : {}", id);
        uKAddressService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
