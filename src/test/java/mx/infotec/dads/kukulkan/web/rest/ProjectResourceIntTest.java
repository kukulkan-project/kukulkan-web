package mx.infotec.dads.kukulkan.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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

import mx.infotec.dads.kukulkan.KukulkancraftsmanApp;
import mx.infotec.dads.kukulkan.domain.DataStore;
import mx.infotec.dads.kukulkan.domain.Project;
import mx.infotec.dads.kukulkan.domain.enumeration.ArchetypeType;
import mx.infotec.dads.kukulkan.repository.ProjectRepository;
import mx.infotec.dads.kukulkan.service.ProjectService;
import mx.infotec.dads.kukulkan.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the ProjectResource REST controller.
 *
 * @see ProjectResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KukulkancraftsmanApp.class)
public class ProjectResourceIntTest {

    private static final String DEFAULT_PROJECT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_APP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_APP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_AUTHOR = "AAAAAAAAAA";
    private static final String UPDATED_AUTHOR = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_YEAR = "5";
    private static final String UPDATED_YEAR = "9";

    private static final String DEFAULT_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PACKAGING = "AAAAAAAAAA";
    private static final String UPDATED_PACKAGING = "BBBBBBBBBB";

    private static final DataStore DEFAULT_DATA_STOR = new DataStore();
    private static final DataStore UPDATED_DATA_STOR = new DataStore();

    private static final String DEFAULT_DAO_LAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DAO_LAYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DOMAIN_LAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOMAIN_LAYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_LAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_LAYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EXCEPTION_LAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EXCEPTION_LAYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WEB_LAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WEB_LAYER_NAME = "BBBBBBBBBB";

    private static final ArchetypeType DEFAULT_ARCHETYPE = ArchetypeType.REST_SPRING_JPA;
    private static final ArchetypeType UPDATED_ARCHETYPE = ArchetypeType.PRIMEFACES_SPRING_MYBATIS;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectMockMvc;

    private Project project;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectResource projectResource = new ProjectResource(projectService);
        this.restProjectMockMvc = MockMvcBuilders.standaloneSetup(projectResource)
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
    public static Project createEntity() {
        Project project = new Project()
            .projectId(DEFAULT_PROJECT_ID)
            .appName(DEFAULT_APP_NAME)
            .author(DEFAULT_AUTHOR)
            .version(DEFAULT_VERSION)
            .year(DEFAULT_YEAR)
            .groupId(DEFAULT_GROUP_ID)
            .packaging(DEFAULT_PACKAGING)
            .dataStore(DEFAULT_DATA_STOR)
            .daoLayerName(DEFAULT_DAO_LAYER_NAME)
            .domainLayerName(DEFAULT_DOMAIN_LAYER_NAME)
            .serviceLayerName(DEFAULT_SERVICE_LAYER_NAME)
            .exceptionLayerName(DEFAULT_EXCEPTION_LAYER_NAME)
            .webLayerName(DEFAULT_WEB_LAYER_NAME)
            .archetypeType(DEFAULT_ARCHETYPE);
        return project;
    }

    @Before
    public void initTest() {
        projectRepository.deleteAll();
        project = createEntity();
    }

    @Test
    public void createProject() throws Exception {
        int databaseSizeBeforeCreate = projectRepository.findAll().size();

        // Create the Project
        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isCreated());

        // Validate the Project in the database
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeCreate + 1);
        Project testProject = projectList.get(projectList.size() - 1);
        assertThat(testProject.getProjectId()).isEqualTo(DEFAULT_PROJECT_ID);
        assertThat(testProject.getAppName()).isEqualTo(DEFAULT_APP_NAME);
        assertThat(testProject.getAuthor()).isEqualTo(DEFAULT_AUTHOR);
        assertThat(testProject.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testProject.getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testProject.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testProject.getPackaging()).isEqualTo(DEFAULT_PACKAGING);
        assertThat(testProject.getDataStore()).isEqualTo(DEFAULT_DATA_STOR);
        assertThat(testProject.getDaoLayerName()).isEqualTo(DEFAULT_DAO_LAYER_NAME);
        assertThat(testProject.getDomainLayerName()).isEqualTo(DEFAULT_DOMAIN_LAYER_NAME);
        assertThat(testProject.getServiceLayerName()).isEqualTo(DEFAULT_SERVICE_LAYER_NAME);
        assertThat(testProject.getExceptionLayerName()).isEqualTo(DEFAULT_EXCEPTION_LAYER_NAME);
        assertThat(testProject.getWebLayerName()).isEqualTo(DEFAULT_WEB_LAYER_NAME);
        assertThat(testProject.getArchetype()).isEqualTo(DEFAULT_ARCHETYPE);
    }

    @Test
    public void createProjectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectRepository.findAll().size();

        // Create the Project with an existing ID
        project.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkProjectIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setProjectId(null);

        // Create the Project, which fails.

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAppNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setAppName(null);

        // Create the Project, which fails.

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAuthorIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setAuthor(null);

        // Create the Project, which fails.

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setVersion(null);

        // Create the Project, which fails.

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setYear(null);

        // Create the Project, which fails.

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setGroupId(null);

        // Create the Project, which fails.

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkPackagingIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setPackaging(null);

        // Create the Project, which fails.

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDataStorIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setDataStor(null);

        // Create the Project, which fails.

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDaoLayerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setDaoLayerName(null);

        // Create the Project, which fails.

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDomainLayerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setDomainLayerName(null);

        // Create the Project, which fails.

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkServiceLayerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setServiceLayerName(null);

        // Create the Project, which fails.

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkExceptionLayerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setExceptionLayerName(null);

        // Create the Project, which fails.

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkWebLayerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setWebLayerName(null);

        // Create the Project, which fails.

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkArchetypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setArchetypeType(null);

        // Create the Project, which fails.

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProjects() throws Exception {
        // Initialize the database
        projectRepository.save(project);

        // Get all the projectList
        restProjectMockMvc.perform(get("/api/projects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(project.getId())))
            .andExpect(jsonPath("$.[*].projectId").value(hasItem(DEFAULT_PROJECT_ID.toString())))
            .andExpect(jsonPath("$.[*].appName").value(hasItem(DEFAULT_APP_NAME.toString())))
            .andExpect(jsonPath("$.[*].author").value(hasItem(DEFAULT_AUTHOR.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.toString())))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.toString())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID.toString())))
            .andExpect(jsonPath("$.[*].packaging").value(hasItem(DEFAULT_PACKAGING.toString())))
            .andExpect(jsonPath("$.[*].dataStor").value(hasItem(DEFAULT_DATA_STOR.toString())))
            .andExpect(jsonPath("$.[*].daoLayerName").value(hasItem(DEFAULT_DAO_LAYER_NAME.toString())))
            .andExpect(jsonPath("$.[*].domainLayerName").value(hasItem(DEFAULT_DOMAIN_LAYER_NAME.toString())))
            .andExpect(jsonPath("$.[*].serviceLayerName").value(hasItem(DEFAULT_SERVICE_LAYER_NAME.toString())))
            .andExpect(jsonPath("$.[*].exceptionLayerName").value(hasItem(DEFAULT_EXCEPTION_LAYER_NAME.toString())))
            .andExpect(jsonPath("$.[*].webLayerName").value(hasItem(DEFAULT_WEB_LAYER_NAME.toString())))
            .andExpect(jsonPath("$.[*].archetype").value(hasItem(DEFAULT_ARCHETYPE.toString())));
    }

    @Test
    public void getProject() throws Exception {
        // Initialize the database
        projectRepository.save(project);

        // Get the project
        restProjectMockMvc.perform(get("/api/projects/{id}", project.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(project.getId()))
            .andExpect(jsonPath("$.projectId").value(DEFAULT_PROJECT_ID.toString()))
            .andExpect(jsonPath("$.appName").value(DEFAULT_APP_NAME.toString()))
            .andExpect(jsonPath("$.author").value(DEFAULT_AUTHOR.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION.toString()))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.toString()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID.toString()))
            .andExpect(jsonPath("$.packaging").value(DEFAULT_PACKAGING.toString()))
            .andExpect(jsonPath("$.dataStor").value(DEFAULT_DATA_STOR.toString()))
            .andExpect(jsonPath("$.daoLayerName").value(DEFAULT_DAO_LAYER_NAME.toString()))
            .andExpect(jsonPath("$.domainLayerName").value(DEFAULT_DOMAIN_LAYER_NAME.toString()))
            .andExpect(jsonPath("$.serviceLayerName").value(DEFAULT_SERVICE_LAYER_NAME.toString()))
            .andExpect(jsonPath("$.exceptionLayerName").value(DEFAULT_EXCEPTION_LAYER_NAME.toString()))
            .andExpect(jsonPath("$.webLayerName").value(DEFAULT_WEB_LAYER_NAME.toString()))
            .andExpect(jsonPath("$.archetype").value(DEFAULT_ARCHETYPE.toString()));
    }

    @Test
    public void getNonExistingProject() throws Exception {
        // Get the project
        restProjectMockMvc.perform(get("/api/projects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProject() throws Exception {
        // Initialize the database
        projectService.save(project);

        int databaseSizeBeforeUpdate = projectRepository.findAll().size();

        // Update the project
        Project updatedProject = projectRepository.findOne(project.getId());
        updatedProject
            .projectId(UPDATED_PROJECT_ID)
            .appName(UPDATED_APP_NAME)
            .author(UPDATED_AUTHOR)
            .version(UPDATED_VERSION)
            .year(UPDATED_YEAR)
            .groupId(UPDATED_GROUP_ID)
            .packaging(UPDATED_PACKAGING)
            .dataStore(UPDATED_DATA_STOR)
            .daoLayerName(UPDATED_DAO_LAYER_NAME)
            .domainLayerName(UPDATED_DOMAIN_LAYER_NAME)
            .serviceLayerName(UPDATED_SERVICE_LAYER_NAME)
            .exceptionLayerName(UPDATED_EXCEPTION_LAYER_NAME)
            .webLayerName(UPDATED_WEB_LAYER_NAME)
            .archetypeType(UPDATED_ARCHETYPE);

        restProjectMockMvc.perform(put("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProject)))
            .andExpect(status().isOk());

        // Validate the Project in the database
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeUpdate);
        Project testProject = projectList.get(projectList.size() - 1);
        assertThat(testProject.getProjectId()).isEqualTo(UPDATED_PROJECT_ID);
        assertThat(testProject.getAppName()).isEqualTo(UPDATED_APP_NAME);
        assertThat(testProject.getAuthor()).isEqualTo(UPDATED_AUTHOR);
        assertThat(testProject.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testProject.getYear()).isEqualTo(UPDATED_YEAR);
        assertThat(testProject.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testProject.getPackaging()).isEqualTo(UPDATED_PACKAGING);
        assertThat(testProject.getDataStore()).isEqualTo(UPDATED_DATA_STOR);
        assertThat(testProject.getDaoLayerName()).isEqualTo(UPDATED_DAO_LAYER_NAME);
        assertThat(testProject.getDomainLayerName()).isEqualTo(UPDATED_DOMAIN_LAYER_NAME);
        assertThat(testProject.getServiceLayerName()).isEqualTo(UPDATED_SERVICE_LAYER_NAME);
        assertThat(testProject.getExceptionLayerName()).isEqualTo(UPDATED_EXCEPTION_LAYER_NAME);
        assertThat(testProject.getWebLayerName()).isEqualTo(UPDATED_WEB_LAYER_NAME);
        assertThat(testProject.getArchetype()).isEqualTo(UPDATED_ARCHETYPE);
    }

    @Test
    public void updateNonExistingProject() throws Exception {
        int databaseSizeBeforeUpdate = projectRepository.findAll().size();

        // Create the Project

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectMockMvc.perform(put("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isCreated());

        // Validate the Project in the database
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProject() throws Exception {
        // Initialize the database
        projectService.save(project);

        int databaseSizeBeforeDelete = projectRepository.findAll().size();

        // Get the project
        restProjectMockMvc.perform(delete("/api/projects/{id}", project.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Project.class);
        Project project1 = new Project();
        project1.setId("id1");
        Project project2 = new Project();
        project2.setId(project1.getId());
        assertThat(project1).isEqualTo(project2);
        project2.setId("id2");
        assertThat(project1).isNotEqualTo(project2);
        project1.setId(null);
        assertThat(project1).isNotEqualTo(project2);
    }
}
