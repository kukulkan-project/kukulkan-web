package mx.infotec.dads.kukulkan.web.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.metamodel.DataContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import mx.infotec.dads.kukulkan.KukulkanConfigurationProperties;
import mx.infotec.dads.kukulkan.assets.service.mapper.ProjectMapper;
import mx.infotec.dads.kukulkan.domain.Project;
import mx.infotec.dads.kukulkan.domain.enumeration.ArchetypeType;
import mx.infotec.dads.kukulkan.engine.domain.core.DataContextContainer;
import mx.infotec.dads.kukulkan.engine.domain.core.DataContextType;
import mx.infotec.dads.kukulkan.engine.domain.core.DomainModel;
import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelGroup;
import mx.infotec.dads.kukulkan.engine.domain.core.DataStoreType;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratorContext;
import mx.infotec.dads.kukulkan.engine.domain.core.JavaDomainModel;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.engine.factories.LayerTaskFactory;
import mx.infotec.dads.kukulkan.engine.service.GenerationService;
import mx.infotec.dads.kukulkan.service.DataStoreService;
import mx.infotec.dads.kukulkan.service.ProjectService;
import mx.infotec.dads.kukulkan.util.DataBaseMapping;
import mx.infotec.dads.kukulkan.util.FileUtil;
import mx.infotec.dads.kukulkan.web.rest.util.HeaderUtil;
import mx.infotec.dads.kukulkan.web.rest.util.PaginationUtil;

/**
 * REST controller for managing Project.
 */
@RestController
@RequestMapping("/api")
public class ProjectResource {

    private final Logger log = LoggerFactory.getLogger(ProjectResource.class);

    private static final String ENTITY_NAME = "project";

    private final ProjectService projectService;

    @Autowired
    private DataStoreService dataStoreService;

    @Autowired
    private GenerationService generationService;

    @Autowired
    private KukulkanConfigurationProperties prop;

    @Autowired
    private LayerTaskFactory layerTaskFactory;

    public ProjectResource(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * POST /projects : Create a new project.
     *
     * @param project
     *            the project to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     *         new project, or with status 400 (Bad Request) if the project has
     *         already an ID
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PostMapping("/projects")
    @Timed
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project project) throws URISyntaxException {
        log.debug("REST request to save Project : {}", project);
        if (project.getId() != null) {
            return ResponseEntity.badRequest().headers(
                    HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new project cannot already have an ID"))
                    .body(null);
        }
        Project result = projectService.save(project);
        return ResponseEntity.created(new URI("/api/projects/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * PUT /projects : Updates an existing project.
     *
     * @param project
     *            the project to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     *         project, or with status 400 (Bad Request) if the project is not
     *         valid, or with status 500 (Internal Server Error) if the project
     *         couldnt be updated
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PutMapping("/projects")
    @Timed
    public ResponseEntity<Project> updateProject(@Valid @RequestBody Project project) throws URISyntaxException {
        log.debug("REST request to update Project : {}", project);
        if (project.getId() == null) {
            return createProject(project);
        }
        Project result = projectService.save(project);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, project.getId().toString()))
                .body(result);
    }

    /**
     * GET /projects : get all the projects.
     *
     * @param pageable
     *            the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of projects
     *         in body
     */
    @GetMapping("/projects")
    @Timed
    public ResponseEntity<List<Project>> getAllProjects(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Projects");
        Page<Project> page = projectService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/projects");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET /projects/:id : get the "id" project.
     *
     * @param id
     *            the id of the project to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the
     *         project, or with status 404 (Not Found)
     */
    @GetMapping("/projects/{id}")
    @Timed
    public ResponseEntity<Project> getProject(@PathVariable String id) {
        log.debug("REST request to get Project : {}", id);
        Project project = projectService.findOne(id);
        log.info("Proyecto info {}", project.getDataStore().getName());
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(project));
    }

    /**
     * DELETE /projects/:id : delete the "id" project.
     *
     * @param id
     *            the id of the project to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projects/{id}")
    @Timed
    public ResponseEntity<Void> deleteProject(@PathVariable String id) {
        log.debug("REST request to delete Project : {}", id);
        projectService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }

    /**
     * GET /projects/:id : get the "id" project.
     *
     * @param id
     *            the id of the project to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the
     *         project, or with status 404 (Not Found)
     */
    @PostMapping("/projects/generate")
    @Timed
    public ResponseEntity<Project> generateProject(@Valid @RequestBody Project project) throws URISyntaxException {
        log.debug("REST request to get Project : {}", project.getId());
        ProjectConfiguration pConf = ProjectMapper.toProjectConfiguration(project);
        DomainModel dataModel = new JavaDomainModel();
        DataStoreType dst = new DataStoreType();
        dst.setName("jdbc");
        project.getDataStore().setDataStoreType(dst);
        DataContextContainer<?> dataContext = dataStoreService.createDataContext(project.getDataStore());

        DataContext dataContextDb = (DataContext) dataContext.getDataContext();

        if (dataContext.getDataContextType() == DataContextType.RELATIONAL_DATA_BASE) {
            dataContextDb = (DataContext) dataContext.getDataContext();
        }
        // Tables to process
        List<String> tablesToProcess = new ArrayList<>();
        // Mapping DataContext into DataModel
        List<DomainModelGroup> dmgList = DataBaseMapping
                .createSingleDataModelGroupList(dataContextDb.getDefaultSchema().getTables(), tablesToProcess);
        dataModel.setDomainModelGroup(dmgList);
        // Create GeneratorContext
        GeneratorContext genCtx = new GeneratorContext(dataModel, pConf);
        // Process Activities
        generationService.process(genCtx, layerTaskFactory.getLayerTaskSet(ArchetypeType.ANGULAR_SPRING));
        FileUtil.saveToFile(genCtx);
        try {
            FileUtil.createZip(Paths.get(prop.getConfig().getOutputdir() + "/" + pConf.getId()), "compressedFile");
        } catch (IOException e) {
            log.error("generateProject: ", e);
        }
        Path fileLocation = Paths.get(prop.getConfig().getOutputdir() + "/compressedFile.zip");
        try {
            byte[] data = Files.readAllBytes(fileLocation);
            project.setFile(data);
            project.setFileContentType("application/zip");
        } catch (IOException e) {
            log.error("generateProject: ", e);
        }

        return ResponseEntity.created(new URI("/api/projects/generate/" + project.getAppName()))
                .headers(HeaderUtil.generateSuccessStatus(ENTITY_NAME, "ok")).body(project);
    }
}
