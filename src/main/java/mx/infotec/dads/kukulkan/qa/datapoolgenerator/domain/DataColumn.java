package mx.infotec.dads.kukulkan.qa.datapoolgenerator.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * A generic DataColumn, useful for several formats
 * 
 * @author Roberto Villarejo Martínez
 *
 */
@JsonPropertyOrder({ "header", "type", "data" })
public class DataColumn {

    @JsonProperty(value = "header")
    private String header;

    private List<String> data = new ArrayList<>();

    @JsonProperty(value = "type")
    private DataType type;

    public DataColumn() {

    }

    public DataColumn(String header, DataType dataType, List<String> data) {
        this.header = header;
        this.type = dataType;
        this.data = data;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType dataType) {
        this.type = dataType;
    }

}
