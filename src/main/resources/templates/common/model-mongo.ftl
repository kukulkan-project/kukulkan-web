<#assign aDateTime = .now>
/*
 *  
 * The MIT License (MIT)
 * Copyright (c) ${year} ${author}
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
${package}

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

<#if hasConstraints==true>
import javax.validation.constraints.*;
</#if>

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;
/**
 * The ${entity}
 * 
 * @author ${author}
 *
 */
@Document(collection = "${tableName}")
public class ${entity} implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Este campo fue generado automaticamente por ${author} 
     * Este campo corresponde a la llave primaria ${primaryKey.name}
     *
     * @kukulkanGenerated ${aDateTime?iso_utc}
     */
    @Id
    private ${primaryKey.type} ${primaryKey.name};
	<#list properties as property>
	
    /**
     * Este campo fue generado automaticamente por ${author} 
     * Este campo corresponde a la tabla ${tableName}
     *
     * @kukulkanGenerated ${aDateTime?iso_utc}
     */
    <#if property.constraint.nullable==false>
    @NotNull
    </#if>
    <#if property.sizeValidation==true>
	    <#if property.blob==true>
    @Size(<#if property.constraint.minByte??>min = ${property.constraint.minByte}</#if><#if property.constraint.minByte?? && property.constraint.maxByte??>, </#if><#if property.constraint.maxByte??>max = ${property.constraint.maxByte}</#if>)
	    <#else>
    @Size(<#if property.constraint.minLength??>min = ${property.constraint.minLength}</#if><#if property.constraint.minLength?? && property.constraint.maxLength??>, </#if><#if property.constraint.maxLength??>max = ${property.constraint.maxLength}</#if>)	    
	    </#if>
    </#if>
    <#if property.constraint.pattern??>
    @Pattern(regexp = "${property.constraint.pattern}")
    </#if>
    <#if property.number==true>
    	<#if property.long==true || property.integer==true>
		    <#if property.constraint.minNumber??>
    @Min(value = ${property.constraint.minNumber})
		    </#if>
	    	<#if property.constraint.maxNumber??>
    @Max(value = ${property.constraint.maxNumber})
    		</#if>
    	<#else>
    		<#if property.constraint.minNumber??>
    @DecimalMin(value = "${property.constraint.minNumber}")
    		</#if>
    		<#if property.constraint.maxNumber??>
    @DecimalMax(value = "${property.constraint.maxNumber}")
    		</#if>
    	</#if>	
    </#if>
    @Field("${property.columnName}")
    private ${property.type} ${property.name};
	</#list>
	
    /**
     * Este constructor fue generado automáticamente por ${author}
     * 
     */
    public ${entity}() {

    }
	<#if mandatoryProperties?has_content>
	/**
     * Este constructor fue generado automáticamente por ${author}
     * 
     */
    public ${entity}(<#list mandatoryProperties as property>${property.type} ${property.name}<#sep>, </#sep></#list>) {
    <#list mandatoryProperties as property>
        this.${property.name} = ${property.name};
    </#list>
    }
        
	</#if>
    
    /**
     * Este método fue generado automaticamente por ${author} 
     * Este método GETTER fue generado para la llave primaria ${tableName}.${primaryKey.name}
     *
     * @return el valor de ${primaryKey.name}
     *
     * @kukulkanGenerated ${aDateTime?iso_utc}
     */
    public ${primaryKey.type} get${primaryKey.name?cap_first}() {
        return ${primaryKey.name};
    }

    /**
     * Este método fue generado automaticamente por ${author} 
     * Este método SETTER fue generado para la llave primaria. ${tableName}.${primaryKey.name}
     *
     * @return el valor de area_conocimiento.id
     *
     * @kukulkanGenerated ${aDateTime?iso_utc}
     */
    public void set${primaryKey.name?cap_first}(${primaryKey.type} ${primaryKey.name}) {
        this.${primaryKey.name} = ${primaryKey.name};
    }

	<#list properties as property>
    /**
     * Este método fue generado automaticamente por ${author} 
     * Este método GETTER fue generado para la propiedad ${tableName}.${property.columnName}
     *
     * @return el valor de ${property.name}
     *
     * @kukulkanGenerated ${aDateTime?iso_utc}
     */
    public ${property.type} get${property.name?cap_first}() {
        return ${property.name};
    }

    /**
     * Este método fue generado automaticamente por ${author} 
     * Este método SETTER fue generado para la propiedad. ${tableName}.${property.name}
     *
     * @return el valor de ${property.name?cap_first}
     *
     * @kukulkanGenerated ${aDateTime?iso_utc}
     */
    public void set${property.name?cap_first}(${property.type} ${property.name}) {
        this.${property.name} = ${property.name};
    }
    </#list>

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ${entity} entity = (${entity}) o;
        if (entity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
    
    /**
     * Este método fue generado automaticamente por ${author}
     *
     * @return el valor de representado por la entidad ${entity}
     *
     * @kukulkanGenerated ${aDateTime?iso_utc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        <#list properties as property>
        sb.append(", ${property.name}=").append(${property.name});
        </#list>
        sb.append("]");
        return sb.toString();
    }
}
