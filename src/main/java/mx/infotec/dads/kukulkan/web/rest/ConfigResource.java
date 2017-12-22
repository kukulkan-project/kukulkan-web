package mx.infotec.dads.kukulkan.web.rest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import mx.infotec.dads.kukulkan.KukulkanConfigurationProperties;
import mx.infotec.dads.kukulkan.KukulkanConfigurationProperties.Config.ExternalApps;

/**
 * REST controller for managing DataStore.
 */
@RestController
@RequestMapping("/api")
public class ConfigResource {

    private final Logger log = LoggerFactory.getLogger(ConfigResource.class);

    private KukulkanConfigurationProperties prop;

    public ConfigResource(KukulkanConfigurationProperties prop) {
        this.prop = prop;
    }

    /**
     * GET /data-stores : get all the dataStores.
     *
     * @param pageable
     *            the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of
     *         dataStores in body
     */
    @GetMapping("/config/externalApps")
    @Timed
    public ResponseEntity<ExternalApps> getExternalApps(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of DataStores");
        ExternalApps externalApps = prop.getConfig().getExternalApps();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(externalApps));
    }
}
