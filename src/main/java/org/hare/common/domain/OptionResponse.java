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
    public OptionResponse(String value, String label) {
        this.value = value;
        this.label = label;
        this.selected = Boolean.FALSE;
        this.disabled = Boolean.FALSE;
    }
}
