package org.microf.web.rest;

import org.microf.AbstractCassandraTest;
import org.microf.MicrofApp;

import org.microf.domain.IEAddress;
import org.microf.repository.IEAddressRepository;
import org.microf.service.IEAddressService;
import org.microf.service.dto.IEAddressDTO;
import org.microf.service.mapper.IEAddressMapper;

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
 * Test class for the IEAddressResource REST controller.
 *
 * @see IEAddressResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MicrofApp.class)
public class IEAddressResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_ADDRESSLINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSLINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESSLINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSLINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SUMMARYLINE = "AAAAAAAAAA";
    private static final String UPDATED_SUMMARYLINE = "BBBBBBBBBB";

    private static final String DEFAULT_ORGANISATION = "AAAAAAAAAA";
    private static final String UPDATED_ORGANISATION = "BBBBBBBBBB";

    private static final String DEFAULT_STREET = "AAAAAAAAAA";
    private static final String UPDATED_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_POSTTOWN = "AAAAAAAAAA";
    private static final String UPDATED_POSTTOWN = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTY = "BBBBBBBBBB";

    private static final String DEFAULT_POSTCODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTCODE = "BBBBBBBBBB";

    @Autowired
    private IEAddressRepository iEAddressRepository;

    @Autowired
    private IEAddressMapper iEAddressMapper;

    @Autowired
    private IEAddressService iEAddressService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restIEAddressMockMvc;

    private IEAddress iEAddress;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        IEAddressResource iEAddressResource = new IEAddressResource(iEAddressService);
        this.restIEAddressMockMvc = MockMvcBuilders.standaloneSetup(iEAddressResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IEAddress createEntity() {
        IEAddress iEAddress = new IEAddress()
                .addressline1(DEFAULT_ADDRESSLINE_1)
                .addressline2(DEFAULT_ADDRESSLINE_2)
                .summaryline(DEFAULT_SUMMARYLINE)
                .organisation(DEFAULT_ORGANISATION)
                .street(DEFAULT_STREET)
                .posttown(DEFAULT_POSTTOWN)
                .county(DEFAULT_COUNTY)
                .postcode(DEFAULT_POSTCODE);
        return iEAddress;
    }

    @Before
    public void initTest() {
        iEAddressRepository.deleteAll();
        iEAddress = createEntity();
    }

    @Test
    public void createIEAddress() throws Exception {
        int databaseSizeBeforeCreate = iEAddressRepository.findAll().size();

        // Create the IEAddress
        IEAddressDTO iEAddressDTO = iEAddressMapper.iEAddressToIEAddressDTO(iEAddress);

        restIEAddressMockMvc.perform(post("/api/i-e-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(iEAddressDTO)))
            .andExpect(status().isCreated());

        // Validate the IEAddress in the database
        List<IEAddress> iEAddressList = iEAddressRepository.findAll();
        assertThat(iEAddressList).hasSize(databaseSizeBeforeCreate + 1);
        IEAddress testIEAddress = iEAddressList.get(iEAddressList.size() - 1);
        assertThat(testIEAddress.getAddressline1()).isEqualTo(DEFAULT_ADDRESSLINE_1);
        assertThat(testIEAddress.getAddressline2()).isEqualTo(DEFAULT_ADDRESSLINE_2);
        assertThat(testIEAddress.getSummaryline()).isEqualTo(DEFAULT_SUMMARYLINE);
        assertThat(testIEAddress.getOrganisation()).isEqualTo(DEFAULT_ORGANISATION);
        assertThat(testIEAddress.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testIEAddress.getPosttown()).isEqualTo(DEFAULT_POSTTOWN);
        assertThat(testIEAddress.getCounty()).isEqualTo(DEFAULT_COUNTY);
        assertThat(testIEAddress.getPostcode()).isEqualTo(DEFAULT_POSTCODE);
    }

    @Test
    public void createIEAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = iEAddressRepository.findAll().size();

        // Create the IEAddress with an existing ID
        IEAddress existingIEAddress = new IEAddress();
        existingIEAddress.setId(UUID.randomUUID());
        IEAddressDTO existingIEAddressDTO = iEAddressMapper.iEAddressToIEAddressDTO(existingIEAddress);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIEAddressMockMvc.perform(post("/api/i-e-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingIEAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<IEAddress> iEAddressList = iEAddressRepository.findAll();
        assertThat(iEAddressList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllIEAddresses() throws Exception {
        // Initialize the database
        iEAddressRepository.save(iEAddress);

        // Get all the iEAddressList
        restIEAddressMockMvc.perform(get("/api/i-e-addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(iEAddress.getId().toString())))
            .andExpect(jsonPath("$.[*].addressline1").value(hasItem(DEFAULT_ADDRESSLINE_1.toString())))
            .andExpect(jsonPath("$.[*].addressline2").value(hasItem(DEFAULT_ADDRESSLINE_2.toString())))
            .andExpect(jsonPath("$.[*].summaryline").value(hasItem(DEFAULT_SUMMARYLINE.toString())))
            .andExpect(jsonPath("$.[*].organisation").value(hasItem(DEFAULT_ORGANISATION.toString())))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET.toString())))
            .andExpect(jsonPath("$.[*].posttown").value(hasItem(DEFAULT_POSTTOWN.toString())))
            .andExpect(jsonPath("$.[*].county").value(hasItem(DEFAULT_COUNTY.toString())))
            .andExpect(jsonPath("$.[*].postcode").value(hasItem(DEFAULT_POSTCODE.toString())));
    }

    @Test
    public void getIEAddress() throws Exception {
        // Initialize the database
        iEAddressRepository.save(iEAddress);

        // Get the iEAddress
        restIEAddressMockMvc.perform(get("/api/i-e-addresses/{id}", iEAddress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(iEAddress.getId().toString()))
            .andExpect(jsonPath("$.addressline1").value(DEFAULT_ADDRESSLINE_1.toString()))
            .andExpect(jsonPath("$.addressline2").value(DEFAULT_ADDRESSLINE_2.toString()))
            .andExpect(jsonPath("$.summaryline").value(DEFAULT_SUMMARYLINE.toString()))
            .andExpect(jsonPath("$.organisation").value(DEFAULT_ORGANISATION.toString()))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET.toString()))
            .andExpect(jsonPath("$.posttown").value(DEFAULT_POSTTOWN.toString()))
            .andExpect(jsonPath("$.county").value(DEFAULT_COUNTY.toString()))
            .andExpect(jsonPath("$.postcode").value(DEFAULT_POSTCODE.toString()));
    }

    @Test
    public void getNonExistingIEAddress() throws Exception {
        // Get the iEAddress
        restIEAddressMockMvc.perform(get("/api/i-e-addresses/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateIEAddress() throws Exception {
        // Initialize the database
        iEAddressRepository.save(iEAddress);
        int databaseSizeBeforeUpdate = iEAddressRepository.findAll().size();

        // Update the iEAddress
        IEAddress updatedIEAddress = iEAddressRepository.findOne(iEAddress.getId());
        updatedIEAddress
                .addressline1(UPDATED_ADDRESSLINE_1)
                .addressline2(UPDATED_ADDRESSLINE_2)
                .summaryline(UPDATED_SUMMARYLINE)
                .organisation(UPDATED_ORGANISATION)
                .street(UPDATED_STREET)
                .posttown(UPDATED_POSTTOWN)
                .county(UPDATED_COUNTY)
                .postcode(UPDATED_POSTCODE);
        IEAddressDTO iEAddressDTO = iEAddressMapper.iEAddressToIEAddressDTO(updatedIEAddress);

        restIEAddressMockMvc.perform(put("/api/i-e-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(iEAddressDTO)))
            .andExpect(status().isOk());

        // Validate the IEAddress in the database
        List<IEAddress> iEAddressList = iEAddressRepository.findAll();
        assertThat(iEAddressList).hasSize(databaseSizeBeforeUpdate);
        IEAddress testIEAddress = iEAddressList.get(iEAddressList.size() - 1);
        assertThat(testIEAddress.getAddressline1()).isEqualTo(UPDATED_ADDRESSLINE_1);
        assertThat(testIEAddress.getAddressline2()).isEqualTo(UPDATED_ADDRESSLINE_2);
        assertThat(testIEAddress.getSummaryline()).isEqualTo(UPDATED_SUMMARYLINE);
        assertThat(testIEAddress.getOrganisation()).isEqualTo(UPDATED_ORGANISATION);
        assertThat(testIEAddress.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testIEAddress.getPosttown()).isEqualTo(UPDATED_POSTTOWN);
        assertThat(testIEAddress.getCounty()).isEqualTo(UPDATED_COUNTY);
        assertThat(testIEAddress.getPostcode()).isEqualTo(UPDATED_POSTCODE);
    }

    @Test
    public void updateNonExistingIEAddress() throws Exception {
        int databaseSizeBeforeUpdate = iEAddressRepository.findAll().size();

        // Create the IEAddress
        IEAddressDTO iEAddressDTO = iEAddressMapper.iEAddressToIEAddressDTO(iEAddress);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restIEAddressMockMvc.perform(put("/api/i-e-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(iEAddressDTO)))
            .andExpect(status().isCreated());

        // Validate the IEAddress in the database
        List<IEAddress> iEAddressList = iEAddressRepository.findAll();
        assertThat(iEAddressList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteIEAddress() throws Exception {
        // Initialize the database
        iEAddressRepository.save(iEAddress);
        int databaseSizeBeforeDelete = iEAddressRepository.findAll().size();

        // Get the iEAddress
        restIEAddressMockMvc.perform(delete("/api/i-e-addresses/{id}", iEAddress.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<IEAddress> iEAddressList = iEAddressRepository.findAll();
        assertThat(iEAddressList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IEAddress.class);
    }
}
