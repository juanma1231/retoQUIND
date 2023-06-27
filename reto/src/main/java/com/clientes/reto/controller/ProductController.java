package com.clientes.reto.controller;

import com.clientes.reto.domain.entity.ProductEntity;
import com.clientes.reto.response.CustomResponse;
import com.clientes.reto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody ProductEntity product){
        ResponseEntity<Object> response;
        try {
            productService.create(product);
            CustomResponse customResponse = new CustomResponse("Cuenta creada con exito", HttpStatus.CREATED);
            customResponse.setResponseObject(product);
            response= new ResponseEntity<>(customResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            response = new ResponseEntity<>("hubo un error tratando de crear el producto", HttpStatus.BAD_REQUEST);
        }
        return response;
    }
    @GetMapping("/{email}")
    public ResponseEntity<Object> getByUser(@PathVariable("email") String email){
        ResponseEntity<Object> response;
        try {
            List<ProductEntity> productEntities = productService.finByUser(email);
            CustomResponse customResponse = new CustomResponse("Se encontraron estos productos", HttpStatus.ACCEPTED);
            customResponse.setResponseObject(productEntities);
            response = new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>("hubo un error tratando de encontrar los productos", HttpStatus.BAD_REQUEST);
        }
        return response;
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
