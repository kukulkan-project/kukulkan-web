package mx.infotec.dads.kukulkan.domain;

import java.io.Serializable;
import java.util.Map;

public class Context implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private Map<String, String> parameters;
	
	private Integer lifespan;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public Integer getLifespan() {
		return lifespan;
	}

	public void setLifespan(Integer lifespan) {
		this.lifespan = lifespan;
	}
	
	/**
     * Search the value of given parameter
     * @param parameter
     * @return the value of given parameter or empty String if not exists
     */
    public String getParameter(String parameter) {
    	if (parameter == null) return null;
    	return parameters.get(parameter);
    }
    
    @Override
    public String toString() {
        return "Context{" +
            "name='" + name + '\'' +
            ", parameters='" + parameters + '\'' +
            ", lifespan='" + lifespan + '\'' +
            "}";
    }
	

}
