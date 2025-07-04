package org.hare.common.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wang cheng
 */
@Getter
@Setter
public class OptionResponse {
    private String label;
    private Object value;
    private Boolean selected;
    private Boolean disabled;

    public OptionResponse() {
    }

    public OptionResponse(String label, Object value) {
        this.label = label;
        this.value = value;
        this.selected = Boolean.FALSE;
        this.disabled = Boolean.FALSE;
    }

    public OptionResponse selected(){
        this.selected = Boolean.TRUE;
        return this;
    }

    public OptionResponse disabled(){
        this.disabled = Boolean.TRUE;
        return this;
    }

}
