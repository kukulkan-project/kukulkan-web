package mx.infotec.dads.kukulkan.reports.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A ReportElement.
 */

@Document(collection = "reportElements")
public class ReportElement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

    @Field("description")
    private String description;

    @Field("weight")
    private Double weight;
    
    @Field("indicatorStatus")
    private List<IndicatorStatus> indicatorStatus;

    public List<IndicatorStatus> getIndicatorStatus() {
        return indicatorStatus;
    }

    public void setIndicatorStatus(List<IndicatorStatus> indicatorStatus) {
        this.indicatorStatus = indicatorStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public ReportElement nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public ReportElement description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public ReportElement weight(Double weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReportElement reportElement = (ReportElement) o;
        if (reportElement.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reportElement.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReportElement{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", description='" + getDescription() + "'" +
            ", weight='" + getWeight() + "'" +
            "}";
    }
}
