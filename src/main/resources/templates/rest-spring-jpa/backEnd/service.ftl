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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

${importModel}
<#if importPrimaryKey??>
${importPrimaryKey}
</#if>

/**
 * ${entity}Service
 * 
 * @author ${author}
 * @kukulkanGenerated ${aDateTime?iso_utc}
 */
public interface ${entity}Service {

    /**
     * regresa una lista con todos los elementos ${entity}
     * 
     * @return Page<${entity}>
     */
    Page<${entity}> findAll(Pageable pageable);

    /**
     * Consulta un ${entity} por su llave primaria
     * 
     * @param id
     * @return ${entity}
     */
    ${entity} findById(${id} id);

    /**
     * Guarda o actualiza un ${entity}
     * 
     * @param ${entityCamelCase}
     * @return boolean
     */
    ${entity} save(${entity} ${entityCamelCase});

    /**
     * Regresa true o false si existe un ${entity} almacenado
     * 
     * @param id
     * @return boolean
     */
    boolean exists(${id} id);

    /**
     * Borrar un ${entity} por su llave primaria
     * 
     * @param id
     */
    void delete(${id} id);

    /**
     * Borrar todos los elementos ${entity} almacenados
     * 
     * @param id
     */
    void deleteAll();
    
    /**
     * Buscar ${entity} con el correspondiente al query.
     *
     *  @param query El query de la busqueda
     *  
     *  @param pageable la información de paginación
     *  @return Page de todas las entidades
     */
    Page<${entity}> search(String query, Pageable pageable);
}
