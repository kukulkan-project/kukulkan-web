package mx.infotec.dads.kukulkan.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import mx.infotec.dads.kukulkan.domain.enumeration.TableTypes;
import mx.infotec.dads.kukulkan.engine.domain.core.DataStoreType;

/**
 * The Connection Class is used for hold the connection credentials to a
 * datasource
 * 
 * @author Daniel Cortes Pichardo
 *
 */

@Document(collection = "dataStores")
public class DataStore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @DBRef
    private DataStoreType dataStoreType;

    @Field("name")
    private String name;

    // @Pattern(regexp =
    // "(?i)^(?:(?:https?|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,}))\\.?)(?::\\d{2,5})?(?:[/?#]\\S*)?$")
    @Field("url")
    private String url;
    
    @Field("schema")
    private String schema;

    @NotNull
    // @Pattern(regexp = ".*\"[[a-z]*\\.]+[A-Z][a-z]*\".*")
    @Field("driver_class")
    private String driverClass;

    @Field("username")
    private String username;

    @Field("password")
    private String password;

    @Field("table_types")
    private TableTypes tableTypes;

    @Field("script")
    private String script;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public DataStore name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public DataStore url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public DataStore driverClass(String driverClass) {
        this.driverClass = driverClass;
        return this;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUsername() {
        return username;
    }

    public DataStore username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public DataStore password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TableTypes getTableTypes() {
        return tableTypes;
    }

    public DataStore tableTypes(TableTypes tableTypes) {
        this.tableTypes = tableTypes;
        return this;
    }

    public void setTableTypes(TableTypes tableTypes) {
        this.tableTypes = tableTypes;
    }

    public DataStoreType getDataStoreType() {
        return dataStoreType;
    }

    public void setDataStoreType(DataStoreType dataStoreType) {
        this.dataStoreType = dataStoreType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataStore dataStore = (DataStore) o;
        if (dataStore.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dataStore.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DataStore{" + "id=" + getId() + ", name='" + getName() + "'" + ", url='" + getUrl() + "'"
                + ", driverClass='" + getDriverClass() + "'" + ", username='" + getUsername() + "'" + ", tableTypes='"
                + getTableTypes() + "'" + "}";
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
