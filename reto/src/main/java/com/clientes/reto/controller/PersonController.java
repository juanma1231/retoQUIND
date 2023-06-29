package com.clientes.reto.controller;

import com.clientes.reto.domain.entity.PersonEntity;
import com.clientes.reto.response.CustomException;
import com.clientes.reto.response.CustomResponse;
import com.clientes.reto.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping()
    public ResponseEntity<Object> getAllUsers(){
        ResponseEntity<Object> response;
        try {
            Iterable<PersonEntity> users = personService.getAllUsers();
            CustomResponse response1 = new CustomResponse("Consulta Exitosa", HttpStatus.OK);
            response1.setResponseObject(users);
            response = new ResponseEntity<>(response1, HttpStatus.OK);

        }catch (Exception e){
            response = new ResponseEntity<>("Eror al consultar", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @PostMapping()
    public ResponseEntity<Object> createUser(@RequestBody PersonEntity personEntity) {
        ResponseEntity<Object> response;
        try {
            personService.save(personEntity);
            CustomResponse customResponse = new CustomResponse("El usuario fué creado", HttpStatus.OK);
            customResponse.setResponseObject(personEntity);
            response = new ResponseEntity<>(customResponse, HttpStatus.OK);


        } catch (CustomException e) {
            response = new ResponseEntity<>("Tenemos un error tratando de crear el usaurio", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping("/{mail}")
    public ResponseEntity<Object> deleteUser(@PathVariable("mail") String mail){
        ResponseEntity<Object> response;
        try {
            personService.delete(mail);
            CustomResponse customResponse = new CustomResponse("Usuario eliminado con exito", HttpStatus.OK);
            customResponse.setResponseObject(mail);
            response = new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (CustomException e) {
            response = new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
        return response;
    }
    @PatchMapping("/user/{email}")
    public ResponseEntity<Object> updateUser(@PathVariable("email") String email, @RequestBody PersonEntity personEntity){
        ResponseEntity<Object> response;
        try {
            PersonEntity person = personService.patch(email, personEntity);
            CustomResponse customResponse = new CustomResponse("Usuario actulizado con exito", HttpStatus.OK);
            customResponse.setResponseObject(person);
            response = new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (CustomException e) {
            response = new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
