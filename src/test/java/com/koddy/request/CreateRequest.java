package com.koddy.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateRequest extends Request{

    @JsonProperty("Content")
    private String content;

    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return content;
    }
}
