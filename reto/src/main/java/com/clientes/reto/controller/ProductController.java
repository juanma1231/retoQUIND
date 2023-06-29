package com.clientes.reto.controller;

import com.clientes.reto.domain.entity.PersonEntity;
import com.clientes.reto.domain.entity.ProductEntity;
import com.clientes.reto.utils.CustomException;
import com.clientes.reto.utils.CustomResponse;
import com.clientes.reto.utils.Response;
import com.clientes.reto.service.PersonService;
import com.clientes.reto.service.ProductService;
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
    ProductService productService;

    @Autowired
    PersonService personService;

    @PostMapping("/create")
    public ResponseEntity<Response<ProductEntity>> create(@RequestBody ProductEntity product){
        ResponseEntity<Response<ProductEntity>> responseEntity;
        List<String> messages = new ArrayList<>();
        List<ProductEntity> data = new ArrayList<>();
        Response<ProductEntity> response = new Response<>();
        HttpStatus status= HttpStatus.BAD_REQUEST;
        try {
            PersonEntity personEntity = personService.findById(product.getIdClient());
            product.setClient(personEntity);
            List<ProductEntity> productEntities = personEntity.getProducts();
            productEntities.add(product);
            personEntity.setProducts(productEntities);

            data.add(productService.create(product));
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
    public ResponseEntity<Response<ProductEntity>> getByUser(@PathVariable("email") String email){
        ResponseEntity<Response<ProductEntity>> responseEntity;
        List<String> messages = new ArrayList<>();
        List<ProductEntity> data = new ArrayList<>();
        Response<ProductEntity> response = new Response<>();
        HttpStatus status= HttpStatus.BAD_REQUEST;
        try {
            List<ProductEntity> productEntities = productService.finByUser(email);
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
    public ResponseEntity<Object> inactive(@PathVariable("id") Integer accountId){
        ResponseEntity<Object> response;
        try {
            productService.inactive(accountId);
            CustomResponse customResponse = new CustomResponse("se cambiò el estado de el producto con exito", HttpStatus.ACCEPTED);
            response = new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>("hubo un error tratando de encontrar el producto", HttpStatus.BAD_REQUEST);
        }
        return response;
    }
    @PatchMapping("/cancel/{id}")
    public ResponseEntity<Object> cancel(@PathVariable("id") Integer accountId){
        ResponseEntity<Object> response;
        try {
            productService.cancelar(accountId);
            CustomResponse customResponse = new CustomResponse("se cambiò el estado de el producto con exito", HttpStatus.ACCEPTED);
            response = new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>("hubo un error tratando de encontrar el producto", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

}
