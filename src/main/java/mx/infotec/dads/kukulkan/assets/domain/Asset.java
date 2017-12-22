package mx.infotec.dads.kukulkan.assets.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Asset.
 */
@Document(collection = "assets")
public class Asset implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @NotNull
    @Field("author")
    private String author;

    @NotNull
    @Field("version")
    private String version;

    @Field("comments")
    private String comments;

    @NotNull
    @Field("problem_description")
    private String problemDescription;

    @NotNull
    @Field("use_instructions")
    private String useInstructions;

    @Field("location")
    private String location;

    @DBRef
    private LevelOfImplementation implementationLevel;

    @DBRef
    private Granularity granularity;

    @DBRef
    private ProblemDomain problemDomain;

    @DBRef
    private Phase phase;

    @DBRef
    private Discipline discipline;

    @DBRef
    private File file;

    @DBRef
    private State state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Asset name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public Asset author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public Asset version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getComments() {
        return comments;
    }

    public Asset comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public Asset problemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
        return this;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getUseInstructions() {
        return useInstructions;
    }

    public Asset useInstructions(String useInstructions) {
        this.useInstructions = useInstructions;
        return this;
    }

    public void setUseInstructions(String useInstructions) {
        this.useInstructions = useInstructions;
    }

    public String getLocation() {
        return location;
    }

    public Asset location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LevelOfImplementation getImplementationLevel() {
        return implementationLevel;
    }

    public Asset implementationLevel(LevelOfImplementation levelOfImplementation) {
        this.implementationLevel = levelOfImplementation;
        return this;
    }

    public void setImplementationLevel(LevelOfImplementation levelOfImplementation) {
        this.implementationLevel = levelOfImplementation;
    }

    public Granularity getGranularity() {
        return granularity;
    }

    public Asset granularity(Granularity granularity) {
        this.granularity = granularity;
        return this;
    }

    public void setGranularity(Granularity granularity) {
        this.granularity = granularity;
    }

    public ProblemDomain getProblemDomain() {
        return problemDomain;
    }

    public Asset problemDomain(ProblemDomain problemDomain) {
        this.problemDomain = problemDomain;
        return this;
    }

    public void setProblemDomain(ProblemDomain problemDomain) {
        this.problemDomain = problemDomain;
    }

    public Phase getPhase() {
        return phase;
    }

    public Asset phase(Phase phase) {
        this.phase = phase;
        return this;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public Asset discipline(Discipline discipline) {
        this.discipline = discipline;
        return this;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public State getState() {
        return state;
    }

    public Asset state(State state) {
        this.state = state;
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Asset asset = (Asset) o;
        if (asset.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), asset.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Asset{" + "id=" + getId() + ", name='" + getName() + "'" + ", author='" + getAuthor() + "'"
                + ", version='" + getVersion() + "'" + ", comments='" + getComments() + "'" + ", problemDescription='"
                + getProblemDescription() + "'" + ", useInstructions='" + getUseInstructions() + "'" + ", location='"
                + getLocation() + "'" + "}";
    }
}
