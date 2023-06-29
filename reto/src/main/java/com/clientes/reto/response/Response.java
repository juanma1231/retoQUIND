package com.clientes.reto.response;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class Response<D> {
    private List<D> data;
    private List<String> message;

    private HttpStatus status;

    public Response(List<D> data, List<String> message, HttpStatus status) {
        super();
        setData(data);
        setMessage(message);
        setStatus(status);
    }
    public Response() {
        super();
        setData( new ArrayList<>());
        setMessage( new ArrayList<>());
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<D> getData() {
        return data;
    }
    public void setData(List<D> data) {
        this.data = UtilObject.getUtilObject().getDefault(data, new ArrayList<>());
    }
    public List<String> getMessage() {
        return message;
    }
    public void setMessage(List<String> message) {
        this.message = UtilObject.getUtilObject().getDefault(message, new ArrayList<>());
    }

}