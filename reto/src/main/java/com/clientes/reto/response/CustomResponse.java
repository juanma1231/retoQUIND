package com.clientes.reto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.http.HttpStatus;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CustomResponse extends Throwable {

    private String code;
    private String message;
    private int state;
    private Object responseObject;


    public CustomResponse() {}

    public CustomResponse(String message, HttpStatus state) {
        this.message = message;
        this.state = state.value();
        this.code = state.name();
    }
    public  CustomResponse(String message){
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }
}