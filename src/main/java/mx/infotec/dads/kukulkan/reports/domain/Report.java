package mx.infotec.dads.kukulkan.reports.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import mx.infotec.dads.kukulkan.domain.enumeration.ReportType;

/**
 * A Report.
 */

@Document(collection = "report")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("nombre")
    private String nombre;

    @NotNull
    @Field("briefDescription")
    private String briefDescription;

    @Field("description")
    private String description;

    @Field("reportType")
    private ReportType reportType;

    @DBRef
    private List<ReportIndicator> indicators;

    @DBRef
    private List<ReportElement> elements;

    public List<ReportIndicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<ReportIndicator> indicators) {
        this.indicators = indicators;
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

    public Report nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public Report briefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
        return this;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public String getDescription() {
        return description;
    }

    public Report description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public Report reportType(ReportType reportType) {
        this.reportType = reportType;
        return this;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public List<ReportElement> getElements() {
        return elements;
    }

    public void setElements(List<ReportElement> elements) {
        this.elements = elements;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Report report = (Report) o;
        if (report.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), report.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Report{" + "id=" + getId() + ", nombre='" + getNombre() + "'" + ", briefDescription='"
                + getBriefDescription() + "'" + ", description='" + getDescription() + "'" + ", reportType='"
                + getReportType() + "'" + "}";
    }
}
