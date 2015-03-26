package jp.co.mti.mixjuke.dom;


import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnore;
public abstract class AbstractDomain {

    public AbstractDomain() {
    }
    @Id
	@JsonIgnore
    protected String id;

    public abstract String getId();

    public void setId(String id) {
        this.id = id;
    }
}
