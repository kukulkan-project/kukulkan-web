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
${packageImpl}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
${importModel}
<#if importPrimaryKey??>
${importPrimaryKey}
</#if>
${importRepository}
${importService}

/**
 * ${entity}ServiceImpl
 * 
 * @author ${author}
 * @kukulkanGenerated ${aDateTime?iso_utc}
 */
@Service
@Transactional
public class ${entity}ServiceImpl implements ${entity}Service {

    private final Logger log = LoggerFactory.getLogger(${entity}ServiceImpl.class);

    @Autowired
    private ${entity}Repository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<${entity}> findAll(Pageable pageable) {
        log.debug("Request to get all ${entity}");
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public ${entity} findById(${id} id) {
        log.debug("Request to get ${entity} : {}", id);
        return repository.findOne(id);
    }

    @Override
    public ${entity} save(${entity} ${entityCamelCase}) {
        return repository.save(${entityCamelCase});
    }

    @Override
    public boolean exists(${id} id) {
        return repository.exists(id);
    }

    @Override
    public void delete(${id} id) {
        log.debug("Request to delete ${entity} : {}", id);
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        log.debug("Request to delete All ${entity}");
        repository.deleteAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<${entity}> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ${entity} ");
        return repository.findAll(pageable);
    }
}
