package com.clientes.reto.controller;

import com.clientes.reto.domain.dto.PersonDto;
import com.clientes.reto.domain.dto.ProductDto;
import com.clientes.reto.domain.usecase.IProductUseCase;
import com.clientes.reto.persistence.entity.PersonEntity;
import com.clientes.reto.persistence.entity.ProductEntity;
import com.clientes.reto.utils.CustomException;

import com.clientes.reto.utils.Response;
import com.clientes.reto.domain.service.PersonService;
import com.clientes.reto.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    IProductUseCase iProductUseCase;


    @PostMapping("/create")
    public ResponseEntity<Response<ProductDto>> create(@RequestBody ProductDto product){
        ResponseEntity<Response<ProductDto>> responseEntity;
        List<String> messages = new ArrayList<>();
        List<ProductDto> data = new ArrayList<>();
        Response<ProductDto> response = new Response<>();
        HttpStatus status= HttpStatus.BAD_REQUEST;
        try {
            data.add(iProductUseCase.create(product));
            response.setData(data);
            messages.add("Producto creado con exito");
            status = HttpStatus.OK;

        } catch (CustomException e) {
            messages.add(e.getMessage());
        }
        response.setMessage(messages);
        response.setStatus(status);
        responseEntity = new ResponseEntity<>(response,status);
        return responseEntity;
    }
    @GetMapping("/{email}")
    public ResponseEntity<Response<ProductDto>> getByUser(@PathVariable("email") String email){
        ResponseEntity<Response<ProductDto>> responseEntity;
        List<String> messages = new ArrayList<>();
        List<ProductDto> data = new ArrayList<>();
        Response<ProductDto> response = new Response<>();
        HttpStatus status= HttpStatus.BAD_REQUEST;
        try {
            List<ProductDto> productEntities = iProductUseCase.finByUser(email);
            response.setData(productEntities);
            messages.add("Se han encontrado los productos con exito");
            status = HttpStatus.OK;
        } catch (Exception e) {
            messages.add(e.getMessage());
        }
        response.setMessage(messages);
        response.setStatus(status);
        responseEntity = new ResponseEntity<>(response,status);
        return responseEntity;
    }
    @PatchMapping("/inactive/{id}")
    public ResponseEntity<Response<ProductDto>> inactive(@PathVariable("id") String accountId){
        ResponseEntity<Response<ProductDto>> responseEntity;
        List<String> messages = new ArrayList<>();
        List<ProductDto> data = new ArrayList<>();
        Response<ProductDto> response = new Response<>();
        HttpStatus status= HttpStatus.BAD_REQUEST;
        try {
            data.add(iProductUseCase.inactive(accountId));
            messages.add("Cuenta inactivada con exito");
            status = HttpStatus.OK;
        } catch (Exception e) {
            messages.add(e.getMessage());
        }
        response.setMessage(messages);
        response.setStatus(status);
        responseEntity = new ResponseEntity<>(response,status);
        return  responseEntity;
    }
    @PatchMapping("/cancel/{id}")
    public ResponseEntity<Response<ProductDto>> cancel(@PathVariable("id") String accountId){
        ResponseEntity<Response<ProductDto>> responseEntity;
        List<String> messages = new ArrayList<>();
        List<ProductDto> data = new ArrayList<>();
        Response<ProductDto> response = new Response<>();
        HttpStatus status= HttpStatus.BAD_REQUEST;
        try {
            data.add(iProductUseCase.cancelar(accountId));
            response.setData(data);
            status = HttpStatus.OK;
            messages.add("Cuenta cancelada con exito");

        } catch (Exception e) {
            messages.add(e.getMessage());
        }
        response.setMessage(messages);
        response.setStatus(status);
        responseEntity = new ResponseEntity<>(response,status);
        return responseEntity;
    }
    @GetMapping("/all")
    public Iterable<ProductDto> getAll(){
        return iProductUseCase.getALl();
    }

}
