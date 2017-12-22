package mx.infotec.dads.kukulkan.assets.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the Asset entity.
 */
public class AssetDTO implements Serializable {

    private String id;

    @NotNull
    private String name;

    @NotNull
    private String author;

    @NotNull
    private String version;

    private String comments;

    @NotNull
    private String problemDescription;

    @NotNull
    private String useInstructions;

    private String location;

    private String implementationLevelId;

    private String granularityId;

    private String problemDomainId;

    private String phaseId;

    private String disciplineId;

    private String stateId;

    private byte[] file;

    private String fileContentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getUseInstructions() {
        return useInstructions;
    }

    public void setUseInstructions(String useInstructions) {
        this.useInstructions = useInstructions;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImplementationLevelId() {
        return implementationLevelId;
    }

    public void setImplementationLevelId(String levelOfImplementationId) {
        this.implementationLevelId = levelOfImplementationId;
    }

    public String getGranularityId() {
        return granularityId;
    }

    public void setGranularityId(String granularityId) {
        this.granularityId = granularityId;
    }

    public String getProblemDomainId() {
        return problemDomainId;
    }

    public void setProblemDomainId(String problemDomainId) {
        this.problemDomainId = problemDomainId;
    }

    public String getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(String phaseId) {
        this.phaseId = phaseId;
    }

    public String getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(String disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AssetDTO assetDTO = (AssetDTO) o;
        if (assetDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), assetDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AssetDTO{" + "id=" + getId() + ", name='" + getName() + "'" + ", author='" + getAuthor() + "'"
                + ", version='" + getVersion() + "'" + ", comments='" + getComments() + "'" + ", problemDescription='"
                + getProblemDescription() + "'" + ", useInstructions='" + getUseInstructions() + "'" + ", location='"
                + getLocation() + "'" + "}";
    }
}
