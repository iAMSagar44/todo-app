package org.projects.sagar.todoapp.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "timestamp",
        "code",
        "message",
        "errors"
})
public class ToDoError {

    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("code")
    private int code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("errors")
    private Map<String, String> errors;

    public ToDoError(String timestamp, int code, String message) {
        this.timestamp = timestamp;
        this.code = code;
        this.message = message;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("code")
    public int getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(int code) {
        this.code = code;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("errors")
    public Map<String, String> getErrors() {
        return errors;
    }

    @JsonProperty("errors")
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public void addMap(String key, String value){
        if (this.errors == null){
            this.errors = new HashMap<>();
        }
        this.errors.put(key, value);
    }

}
