package com.koddy.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateRequest extends Request{

    @JsonProperty("Checked")
    private Boolean checked;

    public void setChecked(Boolean checked){
        this.checked = checked;
    }
    public Boolean getChecked(){
        return  checked;
    }

}
