package mx.infotec.dads.kukulkan.reports.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.DBRef;

import mx.infotec.dads.kukulkan.assets.domain.State;

/**
 * IndicatorStaus class
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class IndicatorStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @DBRef
    private ReportIndicator indicator;
    @DBRef
    private State state;

    public IndicatorStatus() {

    }

    public IndicatorStatus(ReportIndicator indicator, State state) {
        this.indicator = indicator;
        this.state = state;
    }

    public ReportIndicator getIndicator() {
        return indicator;
    }

    public void setIndicator(ReportIndicator indicator) {
        this.indicator = indicator;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
