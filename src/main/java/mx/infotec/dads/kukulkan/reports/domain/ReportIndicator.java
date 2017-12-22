package mx.infotec.dads.kukulkan.reports.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ReportIndicator.
 */

@Document(collection = "reportIndicators")
public class ReportIndicator implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("nombre")
    private String nombre;

    @NotNull
    @Field("briefDescription")
    private String briefDescription;

    @NotNull
    @Field("description")
    private String description;

    @Field("order")
    private Integer order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public ReportIndicator nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public ReportIndicator briefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
        return this;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public String getDescription() {
        return description;
    }

    public ReportIndicator description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrder() {
        return order;
    }

    public ReportIndicator order(Integer order) {
        this.order = order;
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReportIndicator reportIndicator = (ReportIndicator) o;
        if (reportIndicator.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reportIndicator.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReportIndicator{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", briefDescription='" + getBriefDescription() + "'" +
            ", description='" + getDescription() + "'" +
            ", order='" + getOrder() + "'" +
            "}";
    }
}
