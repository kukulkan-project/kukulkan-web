package mx.infotec.dads.kukulkan.reports.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.Document;

import mx.infotec.dads.kukulkan.domain.enumeration.ReportType;
import mx.infotec.dads.kukulkan.reports.domain.ReportElement;
import mx.infotec.dads.kukulkan.reports.domain.ReportIndicator;

/**
 * A Report.
 */

@Document(collection = "report")
public class ReportDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String nombre;

    private String briefDescription;

    private String description;

    private ReportType reportType;

    private List<ReportIndicator> indicators;

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

    public ReportDTO nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public ReportDTO briefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
        return this;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public String getDescription() {
        return description;
    }

    public ReportDTO description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public ReportDTO reportType(ReportType reportType) {
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
        ReportDTO report = (ReportDTO) o;
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
