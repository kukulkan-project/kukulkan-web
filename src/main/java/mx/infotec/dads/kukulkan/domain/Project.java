package mx.infotec.dads.kukulkan.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import mx.infotec.dads.kukulkan.domain.enumeration.ArchetypeType;

/**
 * A Project.
 */

@Document(collection = "project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(min = 3)
    @Field("project_id")
    private String projectId;

    @NotNull
    @Size(min = 3)
    @Field("app_name")
    private String appName;

    @NotNull
    @Size(min = 3)
    @Field("author")
    private String author;

    @NotNull
    @Size(min = 3)
    @Field("version")
    private String version;

    @NotNull
    @Size(min = 3)
    @Pattern(regexp = "^[0-9]+")
    @Field("year")
    private String year;

    @NotNull
    @Field("group_id")
    private String groupId;

    @NotNull
    @Field("packaging")
    private String packaging;

    @NotNull
    @Field("data_store")
    @DBRef
    private DataStore dataStore;

    @NotNull
    @Size(min = 3)
    @Field("dao_layer_name")
    private String daoLayerName;

    @NotNull
    @Size(min = 3)
    @Field("domain_layer_name")
    private String domainLayerName;

    @NotNull
    @Size(min = 3)
    @Field("service_layer_name")
    private String serviceLayerName;

    @NotNull
    @Size(min = 3)
    @Field("exception_layer_name")
    private String exceptionLayerName;

    @NotNull
    @Field("web_layer_name")
    private String webLayerName;

    @Field("file")
    private byte[] file;

    @Field("content_type")
    private String fileContentType;

    @NotNull
    @Field("archetype")
    private ArchetypeType archetype;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public Project projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getAppName() {
        return appName;
    }

    public Project appName(String appName) {
        this.appName = appName;
        return this;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAuthor() {
        return author;
    }

    public Project author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public Project version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getYear() {
        return year;
    }

    public Project year(String year) {
        this.year = year;
        return this;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGroupId() {
        return groupId;
    }

    public Project groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPackaging() {
        return packaging;
    }

    public Project packaging(String packaging) {
        this.packaging = packaging;
        return this;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public DataStore getDataStore() {
        return dataStore;
    }

    public Project dataStore(DataStore dataStore) {
        this.dataStore = dataStore;
        return this;
    }

    public void setDataStor(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public String getDaoLayerName() {
        return daoLayerName;
    }

    public Project daoLayerName(String daoLayerName) {
        this.daoLayerName = daoLayerName;
        return this;
    }

    public void setDaoLayerName(String daoLayerName) {
        this.daoLayerName = daoLayerName;
    }

    public String getDomainLayerName() {
        return domainLayerName;
    }

    public Project domainLayerName(String domainLayerName) {
        this.domainLayerName = domainLayerName;
        return this;
    }

    public void setDomainLayerName(String domainLayerName) {
        this.domainLayerName = domainLayerName;
    }

    public String getServiceLayerName() {
        return serviceLayerName;
    }

    public Project serviceLayerName(String serviceLayerName) {
        this.serviceLayerName = serviceLayerName;
        return this;
    }

    public void setServiceLayerName(String serviceLayerName) {
        this.serviceLayerName = serviceLayerName;
    }

    public String getExceptionLayerName() {
        return exceptionLayerName;
    }

    public Project exceptionLayerName(String exceptionLayerName) {
        this.exceptionLayerName = exceptionLayerName;
        return this;
    }

    public void setExceptionLayerName(String exceptionLayerName) {
        this.exceptionLayerName = exceptionLayerName;
    }

    public String getWebLayerName() {
        return webLayerName;
    }

    public Project webLayerName(String webLayerName) {
        this.webLayerName = webLayerName;
        return this;
    }

    public void setWebLayerName(String webLayerName) {
        this.webLayerName = webLayerName;
    }

    public ArchetypeType getArchetype() {
        return archetype;
    }

    public Project archetypeType(ArchetypeType archetype) {
        this.archetype = archetype;
        return this;
    }

    public void setArchetypeType(ArchetypeType archetype) {
        this.archetype = archetype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Project project = (Project) o;
        if (project.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), project.getId());
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + getId() + ", projectId='" + getProjectId() + "'" + ", appName='" + getAppName()
                + "'" + ", author='" + getAuthor() + "'" + ", version='" + getVersion() + "'" + ", year='" + getYear()
                + "'" + ", groupId='" + getGroupId() + "'" + ", packaging='" + getPackaging() + "'" + ", dataStor='"
                + getDataStore() + "'" + ", daoLayerName='" + getDaoLayerName() + "'" + ", domainLayerName='"
                + getDomainLayerName() + "'" + ", serviceLayerName='" + getServiceLayerName() + "'"
                + ", exceptionLayerName='" + getExceptionLayerName() + "'" + ", webLayerName='" + getWebLayerName()
                + "'" + ", archetype='" + getArchetype() + "'" + "}";
    }
}
