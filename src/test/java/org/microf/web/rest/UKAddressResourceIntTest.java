package org.microf.web.rest;

import org.microf.AbstractCassandraTest;
import org.microf.MicrofApp;

import org.microf.domain.UKAddress;
import org.microf.repository.UKAddressRepository;
import org.microf.service.UKAddressService;
import org.microf.service.dto.UKAddressDTO;
import org.microf.service.mapper.UKAddressMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UKAddressResource REST controller.
 *
 * @see UKAddressResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MicrofApp.class)
public class UKAddressResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_SUMMARYLINE = "AAAAAAAAAA";
    private static final String UPDATED_SUMMARYLINE = "BBBBBBBBBB";

    private static final String DEFAULT_ORGANISATION = "AAAAAAAAAA";
    private static final String UPDATED_ORGANISATION = "BBBBBBBBBB";

    private static final String DEFAULT_BUILDINGNAME = "AAAAAAAAAA";
    private static final String UPDATED_BUILDINGNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PREMISE = "AAAAAAAAAA";
    private static final String UPDATED_PREMISE = "BBBBBBBBBB";

    private static final String DEFAULT_STREET = "AAAAAAAAAA";
    private static final String UPDATED_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_DEPENDENTLOCALITY = "AAAAAAAAAA";
    private static final String UPDATED_DEPENDENTLOCALITY = "BBBBBBBBBB";

    private static final String DEFAULT_POSTTOWN = "AAAAAAAAAA";
    private static final String UPDATED_POSTTOWN = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTY = "BBBBBBBBBB";

    private static final String DEFAULT_POSTCODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTCODE = "BBBBBBBBBB";

    @Autowired
    private UKAddressRepository uKAddressRepository;

    @Autowired
    private UKAddressMapper uKAddressMapper;

    @Autowired
    private UKAddressService uKAddressService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restUKAddressMockMvc;

    private UKAddress uKAddress;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UKAddressResource uKAddressResource = new UKAddressResource(uKAddressService);
        this.restUKAddressMockMvc = MockMvcBuilders.standaloneSetup(uKAddressResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UKAddress createEntity() {
        UKAddress uKAddress = new UKAddress()
                .summaryline(DEFAULT_SUMMARYLINE)
                .organisation(DEFAULT_ORGANISATION)
                .buildingname(DEFAULT_BUILDINGNAME)
                .premise(DEFAULT_PREMISE)
                .street(DEFAULT_STREET)
                .dependentlocality(DEFAULT_DEPENDENTLOCALITY)
                .posttown(DEFAULT_POSTTOWN)
                .county(DEFAULT_COUNTY)
                .postcode(DEFAULT_POSTCODE);
        return uKAddress;
    }

    @Before
    public void initTest() {
        uKAddressRepository.deleteAll();
        uKAddress = createEntity();
    }

    @Test
    public void createUKAddress() throws Exception {
        int databaseSizeBeforeCreate = uKAddressRepository.findAll().size();

        // Create the UKAddress
        UKAddressDTO uKAddressDTO = uKAddressMapper.uKAddressToUKAddressDTO(uKAddress);

        restUKAddressMockMvc.perform(post("/api/u-k-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uKAddressDTO)))
            .andExpect(status().isCreated());

        // Validate the UKAddress in the database
        List<UKAddress> uKAddressList = uKAddressRepository.findAll();
        assertThat(uKAddressList).hasSize(databaseSizeBeforeCreate + 1);
        UKAddress testUKAddress = uKAddressList.get(uKAddressList.size() - 1);
        assertThat(testUKAddress.getSummaryline()).isEqualTo(DEFAULT_SUMMARYLINE);
        assertThat(testUKAddress.getOrganisation()).isEqualTo(DEFAULT_ORGANISATION);
        assertThat(testUKAddress.getBuildingname()).isEqualTo(DEFAULT_BUILDINGNAME);
        assertThat(testUKAddress.getPremise()).isEqualTo(DEFAULT_PREMISE);
        assertThat(testUKAddress.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testUKAddress.getDependentlocality()).isEqualTo(DEFAULT_DEPENDENTLOCALITY);
        assertThat(testUKAddress.getPosttown()).isEqualTo(DEFAULT_POSTTOWN);
        assertThat(testUKAddress.getCounty()).isEqualTo(DEFAULT_COUNTY);
        assertThat(testUKAddress.getPostcode()).isEqualTo(DEFAULT_POSTCODE);
    }

    @Test
    public void createUKAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uKAddressRepository.findAll().size();

        // Create the UKAddress with an existing ID
        UKAddress existingUKAddress = new UKAddress();
        existingUKAddress.setId(UUID.randomUUID());
        UKAddressDTO existingUKAddressDTO = uKAddressMapper.uKAddressToUKAddressDTO(existingUKAddress);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUKAddressMockMvc.perform(post("/api/u-k-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingUKAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<UKAddress> uKAddressList = uKAddressRepository.findAll();
        assertThat(uKAddressList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllUKAddresses() throws Exception {
        // Initialize the database
        uKAddressRepository.save(uKAddress);

        // Get all the uKAddressList
        restUKAddressMockMvc.perform(get("/api/u-k-addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uKAddress.getId().toString())))
            .andExpect(jsonPath("$.[*].summaryline").value(hasItem(DEFAULT_SUMMARYLINE.toString())))
            .andExpect(jsonPath("$.[*].organisation").value(hasItem(DEFAULT_ORGANISATION.toString())))
            .andExpect(jsonPath("$.[*].buildingname").value(hasItem(DEFAULT_BUILDINGNAME.toString())))
            .andExpect(jsonPath("$.[*].premise").value(hasItem(DEFAULT_PREMISE.toString())))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET.toString())))
            .andExpect(jsonPath("$.[*].dependentlocality").value(hasItem(DEFAULT_DEPENDENTLOCALITY.toString())))
            .andExpect(jsonPath("$.[*].posttown").value(hasItem(DEFAULT_POSTTOWN.toString())))
            .andExpect(jsonPath("$.[*].county").value(hasItem(DEFAULT_COUNTY.toString())))
            .andExpect(jsonPath("$.[*].postcode").value(hasItem(DEFAULT_POSTCODE.toString())));
    }

    @Test
    public void getUKAddress() throws Exception {
        // Initialize the database
        uKAddressRepository.save(uKAddress);

        // Get the uKAddress
        restUKAddressMockMvc.perform(get("/api/u-k-addresses/{id}", uKAddress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(uKAddress.getId().toString()))
            .andExpect(jsonPath("$.summaryline").value(DEFAULT_SUMMARYLINE.toString()))
            .andExpect(jsonPath("$.organisation").value(DEFAULT_ORGANISATION.toString()))
            .andExpect(jsonPath("$.buildingname").value(DEFAULT_BUILDINGNAME.toString()))
            .andExpect(jsonPath("$.premise").value(DEFAULT_PREMISE.toString()))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET.toString()))
            .andExpect(jsonPath("$.dependentlocality").value(DEFAULT_DEPENDENTLOCALITY.toString()))
            .andExpect(jsonPath("$.posttown").value(DEFAULT_POSTTOWN.toString()))
            .andExpect(jsonPath("$.county").value(DEFAULT_COUNTY.toString()))
            .andExpect(jsonPath("$.postcode").value(DEFAULT_POSTCODE.toString()));
    }

    @Test
    public void getNonExistingUKAddress() throws Exception {
        // Get the uKAddress
        restUKAddressMockMvc.perform(get("/api/u-k-addresses/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateUKAddress() throws Exception {
        // Initialize the database
        uKAddressRepository.save(uKAddress);
        int databaseSizeBeforeUpdate = uKAddressRepository.findAll().size();

        // Update the uKAddress
        UKAddress updatedUKAddress = uKAddressRepository.findOne(uKAddress.getId());
        updatedUKAddress
                .summaryline(UPDATED_SUMMARYLINE)
                .organisation(UPDATED_ORGANISATION)
                .buildingname(UPDATED_BUILDINGNAME)
                .premise(UPDATED_PREMISE)
                .street(UPDATED_STREET)
                .dependentlocality(UPDATED_DEPENDENTLOCALITY)
                .posttown(UPDATED_POSTTOWN)
                .county(UPDATED_COUNTY)
                .postcode(UPDATED_POSTCODE);
        UKAddressDTO uKAddressDTO = uKAddressMapper.uKAddressToUKAddressDTO(updatedUKAddress);

        restUKAddressMockMvc.perform(put("/api/u-k-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uKAddressDTO)))
            .andExpect(status().isOk());

        // Validate the UKAddress in the database
        List<UKAddress> uKAddressList = uKAddressRepository.findAll();
        assertThat(uKAddressList).hasSize(databaseSizeBeforeUpdate);
        UKAddress testUKAddress = uKAddressList.get(uKAddressList.size() - 1);
        assertThat(testUKAddress.getSummaryline()).isEqualTo(UPDATED_SUMMARYLINE);
        assertThat(testUKAddress.getOrganisation()).isEqualTo(UPDATED_ORGANISATION);
        assertThat(testUKAddress.getBuildingname()).isEqualTo(UPDATED_BUILDINGNAME);
        assertThat(testUKAddress.getPremise()).isEqualTo(UPDATED_PREMISE);
        assertThat(testUKAddress.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testUKAddress.getDependentlocality()).isEqualTo(UPDATED_DEPENDENTLOCALITY);
        assertThat(testUKAddress.getPosttown()).isEqualTo(UPDATED_POSTTOWN);
        assertThat(testUKAddress.getCounty()).isEqualTo(UPDATED_COUNTY);
        assertThat(testUKAddress.getPostcode()).isEqualTo(UPDATED_POSTCODE);
    }

    @Test
    public void updateNonExistingUKAddress() throws Exception {
        int databaseSizeBeforeUpdate = uKAddressRepository.findAll().size();

        // Create the UKAddress
        UKAddressDTO uKAddressDTO = uKAddressMapper.uKAddressToUKAddressDTO(uKAddress);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUKAddressMockMvc.perform(put("/api/u-k-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uKAddressDTO)))
            .andExpect(status().isCreated());

        // Validate the UKAddress in the database
        List<UKAddress> uKAddressList = uKAddressRepository.findAll();
        assertThat(uKAddressList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteUKAddress() throws Exception {
        // Initialize the database
        uKAddressRepository.save(uKAddress);
        int databaseSizeBeforeDelete = uKAddressRepository.findAll().size();

        // Get the uKAddress
        restUKAddressMockMvc.perform(delete("/api/u-k-addresses/{id}", uKAddress.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UKAddress> uKAddressList = uKAddressRepository.findAll();
        assertThat(uKAddressList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UKAddress.class);
    }
}
