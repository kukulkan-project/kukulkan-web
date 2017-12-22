package mx.infotec.dads.kukulkan.web.rest;

import mx.infotec.dads.kukulkan.KukulkancraftsmanApp;
import mx.infotec.dads.kukulkan.reports.domain.ReportIndicator;
import mx.infotec.dads.kukulkan.reports.repository.ReportIndicatorRepository;
import mx.infotec.dads.kukulkan.reports.service.ReportIndicatorService;
import mx.infotec.dads.kukulkan.reports.web.rest.ReportIndicatorResource;
import mx.infotec.dads.kukulkan.web.rest.errors.ExceptionTranslator;

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
import org.springframework.util.Base64Utils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ReportIndicatorResource REST controller.
 *
 * @see ReportIndicatorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KukulkancraftsmanApp.class)
public class ReportIndicatorResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_BRIEF_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_BRIEF_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDER = 1;
    private static final Integer UPDATED_ORDER = 2;

    @Autowired
    private ReportIndicatorRepository reportIndicatorRepository;

    @Autowired
    private ReportIndicatorService reportIndicatorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restReportIndicatorMockMvc;

    private ReportIndicator reportIndicator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReportIndicatorResource reportIndicatorResource = new ReportIndicatorResource(reportIndicatorService);
        this.restReportIndicatorMockMvc = MockMvcBuilders.standaloneSetup(reportIndicatorResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportIndicator createEntity() {
        ReportIndicator reportIndicator = new ReportIndicator()
            .nombre(DEFAULT_NOMBRE)
            .briefDescription(DEFAULT_BRIEF_DESCRIPTION)
            .description(DEFAULT_DESCRIPTION)
            .order(DEFAULT_ORDER);
        return reportIndicator;
    }

    @Before
    public void initTest() {
        reportIndicatorRepository.deleteAll();
        reportIndicator = createEntity();
    }

    @Test
    public void createReportIndicator() throws Exception {
        int databaseSizeBeforeCreate = reportIndicatorRepository.findAll().size();

        // Create the ReportIndicator
        restReportIndicatorMockMvc.perform(post("/api/report-indicators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportIndicator)))
            .andExpect(status().isCreated());

        // Validate the ReportIndicator in the database
        List<ReportIndicator> reportIndicatorList = reportIndicatorRepository.findAll();
        assertThat(reportIndicatorList).hasSize(databaseSizeBeforeCreate + 1);
        ReportIndicator testReportIndicator = reportIndicatorList.get(reportIndicatorList.size() - 1);
        assertThat(testReportIndicator.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testReportIndicator.getBriefDescription()).isEqualTo(DEFAULT_BRIEF_DESCRIPTION);
        assertThat(testReportIndicator.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testReportIndicator.getOrder()).isEqualTo(DEFAULT_ORDER);
    }

    @Test
    public void createReportIndicatorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reportIndicatorRepository.findAll().size();

        // Create the ReportIndicator with an existing ID
        reportIndicator.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportIndicatorMockMvc.perform(post("/api/report-indicators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportIndicator)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ReportIndicator> reportIndicatorList = reportIndicatorRepository.findAll();
        assertThat(reportIndicatorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportIndicatorRepository.findAll().size();
        // set the field null
        reportIndicator.setNombre(null);

        // Create the ReportIndicator, which fails.

        restReportIndicatorMockMvc.perform(post("/api/report-indicators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportIndicator)))
            .andExpect(status().isBadRequest());

        List<ReportIndicator> reportIndicatorList = reportIndicatorRepository.findAll();
        assertThat(reportIndicatorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkBriefDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportIndicatorRepository.findAll().size();
        // set the field null
        reportIndicator.setBriefDescription(null);

        // Create the ReportIndicator, which fails.

        restReportIndicatorMockMvc.perform(post("/api/report-indicators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportIndicator)))
            .andExpect(status().isBadRequest());

        List<ReportIndicator> reportIndicatorList = reportIndicatorRepository.findAll();
        assertThat(reportIndicatorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportIndicatorRepository.findAll().size();
        // set the field null
        reportIndicator.setDescription(null);

        // Create the ReportIndicator, which fails.

        restReportIndicatorMockMvc.perform(post("/api/report-indicators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportIndicator)))
            .andExpect(status().isBadRequest());

        List<ReportIndicator> reportIndicatorList = reportIndicatorRepository.findAll();
        assertThat(reportIndicatorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllReportIndicators() throws Exception {
        // Initialize the database
        reportIndicatorRepository.save(reportIndicator);

        // Get all the reportIndicatorList
        restReportIndicatorMockMvc.perform(get("/api/report-indicators?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reportIndicator.getId())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].briefDescription").value(hasItem(DEFAULT_BRIEF_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].order").value(hasItem(DEFAULT_ORDER)));
    }

    @Test
    public void getReportIndicator() throws Exception {
        // Initialize the database
        reportIndicatorRepository.save(reportIndicator);

        // Get the reportIndicator
        restReportIndicatorMockMvc.perform(get("/api/report-indicators/{id}", reportIndicator.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(reportIndicator.getId()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.briefDescription").value(DEFAULT_BRIEF_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.order").value(DEFAULT_ORDER));
    }

    @Test
    public void getNonExistingReportIndicator() throws Exception {
        // Get the reportIndicator
        restReportIndicatorMockMvc.perform(get("/api/report-indicators/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateReportIndicator() throws Exception {
        // Initialize the database
        reportIndicatorService.save(reportIndicator);

        int databaseSizeBeforeUpdate = reportIndicatorRepository.findAll().size();

        // Update the reportIndicator
        ReportIndicator updatedReportIndicator = reportIndicatorRepository.findOne(reportIndicator.getId());
        updatedReportIndicator
            .nombre(UPDATED_NOMBRE)
            .briefDescription(UPDATED_BRIEF_DESCRIPTION)
            .description(UPDATED_DESCRIPTION)
            .order(UPDATED_ORDER);

        restReportIndicatorMockMvc.perform(put("/api/report-indicators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedReportIndicator)))
            .andExpect(status().isOk());

        // Validate the ReportIndicator in the database
        List<ReportIndicator> reportIndicatorList = reportIndicatorRepository.findAll();
        assertThat(reportIndicatorList).hasSize(databaseSizeBeforeUpdate);
        ReportIndicator testReportIndicator = reportIndicatorList.get(reportIndicatorList.size() - 1);
        assertThat(testReportIndicator.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testReportIndicator.getBriefDescription()).isEqualTo(UPDATED_BRIEF_DESCRIPTION);
        assertThat(testReportIndicator.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testReportIndicator.getOrder()).isEqualTo(UPDATED_ORDER);
    }

    @Test
    public void updateNonExistingReportIndicator() throws Exception {
        int databaseSizeBeforeUpdate = reportIndicatorRepository.findAll().size();

        // Create the ReportIndicator

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restReportIndicatorMockMvc.perform(put("/api/report-indicators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportIndicator)))
            .andExpect(status().isCreated());

        // Validate the ReportIndicator in the database
        List<ReportIndicator> reportIndicatorList = reportIndicatorRepository.findAll();
        assertThat(reportIndicatorList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteReportIndicator() throws Exception {
        // Initialize the database
        reportIndicatorService.save(reportIndicator);

        int databaseSizeBeforeDelete = reportIndicatorRepository.findAll().size();

        // Get the reportIndicator
        restReportIndicatorMockMvc.perform(delete("/api/report-indicators/{id}", reportIndicator.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ReportIndicator> reportIndicatorList = reportIndicatorRepository.findAll();
        assertThat(reportIndicatorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportIndicator.class);
        ReportIndicator reportIndicator1 = new ReportIndicator();
        reportIndicator1.setId("id1");
        ReportIndicator reportIndicator2 = new ReportIndicator();
        reportIndicator2.setId(reportIndicator1.getId());
        assertThat(reportIndicator1).isEqualTo(reportIndicator2);
        reportIndicator2.setId("id2");
        assertThat(reportIndicator1).isNotEqualTo(reportIndicator2);
        reportIndicator1.setId(null);
        assertThat(reportIndicator1).isNotEqualTo(reportIndicator2);
    }
}
