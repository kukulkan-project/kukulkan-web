package mx.infotec.dads.kukulkan.web.rest;

import mx.infotec.dads.kukulkan.KukulkancraftsmanApp;
import mx.infotec.dads.kukulkan.reports.domain.ReportElement;
import mx.infotec.dads.kukulkan.reports.repository.ReportElementRepository;
import mx.infotec.dads.kukulkan.reports.service.ReportElementService;
import mx.infotec.dads.kukulkan.reports.web.rest.ReportElementResource;
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
 * Test class for the ReportElementResource REST controller.
 *
 * @see ReportElementResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KukulkancraftsmanApp.class)
public class ReportElementResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_WEIGHT = 1D;
    private static final Double UPDATED_WEIGHT = 2D;

    @Autowired
    private ReportElementRepository reportElementRepository;

    @Autowired
    private ReportElementService reportElementService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restReportElementMockMvc;

    private ReportElement reportElement;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReportElementResource reportElementResource = new ReportElementResource(reportElementService);
        this.restReportElementMockMvc = MockMvcBuilders.standaloneSetup(reportElementResource)
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
    public static ReportElement createEntity() {
        ReportElement reportElement = new ReportElement()
            .nombre(DEFAULT_NOMBRE)
            .description(DEFAULT_DESCRIPTION)
            .weight(DEFAULT_WEIGHT);
        return reportElement;
    }

    @Before
    public void initTest() {
        reportElementRepository.deleteAll();
        reportElement = createEntity();
    }

    @Test
    public void createReportElement() throws Exception {
        int databaseSizeBeforeCreate = reportElementRepository.findAll().size();

        // Create the ReportElement
        restReportElementMockMvc.perform(post("/api/report-elements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportElement)))
            .andExpect(status().isCreated());

        // Validate the ReportElement in the database
        List<ReportElement> reportElementList = reportElementRepository.findAll();
        assertThat(reportElementList).hasSize(databaseSizeBeforeCreate + 1);
        ReportElement testReportElement = reportElementList.get(reportElementList.size() - 1);
        assertThat(testReportElement.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testReportElement.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testReportElement.getWeight()).isEqualTo(DEFAULT_WEIGHT);
    }

    @Test
    public void createReportElementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reportElementRepository.findAll().size();

        // Create the ReportElement with an existing ID
        reportElement.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportElementMockMvc.perform(post("/api/report-elements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportElement)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ReportElement> reportElementList = reportElementRepository.findAll();
        assertThat(reportElementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllReportElements() throws Exception {
        // Initialize the database
        reportElementRepository.save(reportElement);

        // Get all the reportElementList
        restReportElementMockMvc.perform(get("/api/report-elements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reportElement.getId())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())));
    }

    @Test
    public void getReportElement() throws Exception {
        // Initialize the database
        reportElementRepository.save(reportElement);

        // Get the reportElement
        restReportElementMockMvc.perform(get("/api/report-elements/{id}", reportElement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(reportElement.getId()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.doubleValue()));
    }

    @Test
    public void getNonExistingReportElement() throws Exception {
        // Get the reportElement
        restReportElementMockMvc.perform(get("/api/report-elements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateReportElement() throws Exception {
        // Initialize the database
        reportElementService.save(reportElement);

        int databaseSizeBeforeUpdate = reportElementRepository.findAll().size();

        // Update the reportElement
        ReportElement updatedReportElement = reportElementRepository.findOne(reportElement.getId());
        updatedReportElement
            .nombre(UPDATED_NOMBRE)
            .description(UPDATED_DESCRIPTION)
            .weight(UPDATED_WEIGHT);

        restReportElementMockMvc.perform(put("/api/report-elements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedReportElement)))
            .andExpect(status().isOk());

        // Validate the ReportElement in the database
        List<ReportElement> reportElementList = reportElementRepository.findAll();
        assertThat(reportElementList).hasSize(databaseSizeBeforeUpdate);
        ReportElement testReportElement = reportElementList.get(reportElementList.size() - 1);
        assertThat(testReportElement.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testReportElement.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testReportElement.getWeight()).isEqualTo(UPDATED_WEIGHT);
    }

    @Test
    public void updateNonExistingReportElement() throws Exception {
        int databaseSizeBeforeUpdate = reportElementRepository.findAll().size();

        // Create the ReportElement

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restReportElementMockMvc.perform(put("/api/report-elements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportElement)))
            .andExpect(status().isCreated());

        // Validate the ReportElement in the database
        List<ReportElement> reportElementList = reportElementRepository.findAll();
        assertThat(reportElementList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteReportElement() throws Exception {
        // Initialize the database
        reportElementService.save(reportElement);

        int databaseSizeBeforeDelete = reportElementRepository.findAll().size();

        // Get the reportElement
        restReportElementMockMvc.perform(delete("/api/report-elements/{id}", reportElement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ReportElement> reportElementList = reportElementRepository.findAll();
        assertThat(reportElementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportElement.class);
        ReportElement reportElement1 = new ReportElement();
        reportElement1.setId("id1");
        ReportElement reportElement2 = new ReportElement();
        reportElement2.setId(reportElement1.getId());
        assertThat(reportElement1).isEqualTo(reportElement2);
        reportElement2.setId("id2");
        assertThat(reportElement1).isNotEqualTo(reportElement2);
        reportElement1.setId(null);
        assertThat(reportElement1).isNotEqualTo(reportElement2);
    }
}
