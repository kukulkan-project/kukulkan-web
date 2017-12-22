package mx.infotec.dads.kukulkan.qa.datapoolgenerator.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DataPool.
 * 
 * @author Roberto Villarejo Martínez
 */
@Document(collection = "data_pool")
public class DataPool implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @Field("name")
    private String name;

    @JsonProperty("sourceData")
    private List<DataColumn> sourceData = new ArrayList<>();

    @JsonProperty(value = "data")
    private List<DataColumn> data = new ArrayList<>();

    @JsonProperty(value = "request")
    private DataPoolRequest request;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public DataPool name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DataColumn> getData() {
        return data;
    }

    public void setData(List<DataColumn> columns) {
        this.data = columns;
    }

    public DataPoolRequest getRequest() {
        return request;
    }

    public void setRequest(DataPoolRequest request) {
        this.request = request;
    }

    public List<DataColumn> getSourceData() {
        return sourceData;
    }

    public void setSourceData(List<DataColumn> sourceData) {
        this.sourceData = sourceData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataPool dataPool = (DataPool) o;
        if (dataPool.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dataPool.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DataPool{" + "id=" + getId() + ", name='" + getName() + "'" + "}";
    }
}
