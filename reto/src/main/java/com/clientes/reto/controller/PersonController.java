package com.clientes.reto.controller;

import com.clientes.reto.domain.dto.PersonDto;
import com.clientes.reto.persistence.entity.PersonEntity;
import com.clientes.reto.utils.CustomException;
import com.clientes.reto.utils.CustomResponse;
import com.clientes.reto.utils.Response;
import com.clientes.reto.domain.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/client")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/{mail}")
    public PersonDto getByUserId(@PathVariable("mail") String mail){
        return personService.findById(mail);
    }

    @GetMapping()
    public ResponseEntity<Response<PersonDto>> getAllUsers(){
        ResponseEntity<Response<PersonDto>> response;
        List<String> messages = new ArrayList<>();
        Response<PersonDto> response1 = new Response<>();
        HttpStatus status= HttpStatus.BAD_REQUEST;
        try {
            List<PersonDto> users = StreamSupport.stream(personService.getAllUsers().spliterator(), false)
                    .collect(Collectors.toList());
            response1.setData(users);
            messages.add("OK");
            status = HttpStatus.OK;

        }catch (Exception e){
            messages.add(e.getMessage());
        }
        response1.setMessage(messages);
        response1.setStatus(status);
        response = new ResponseEntity<>(response1,status);
        return response;
    }

    @PostMapping()
    public ResponseEntity<Response<PersonDto>> createUser(@RequestBody PersonDto personDto) {
        ResponseEntity<Response<PersonDto>> responseEntity;
        List<String> messages = new ArrayList<>();
        Response<PersonDto> response = new Response<>();
        List<PersonDto> data= new ArrayList<>();
        HttpStatus status= HttpStatus.BAD_REQUEST;
        try {
            data.add(personService.create(personDto));
            response.setData(data);
            messages.add("Usuario creado con exito");
            status = HttpStatus.OK;
        } catch (CustomException e) {
            messages.add(e.getMessage());
        }
        response.setStatus(status);
        response.setMessage(messages);
        responseEntity = new ResponseEntity<>(response,status);
        return responseEntity;
    }

    @DeleteMapping("/{mail}")
    public ResponseEntity<Response<PersonDto>> deleteUser(@PathVariable("mail") String mail){
        ResponseEntity<Response<PersonDto>> responseEntity;
        List<String> messages = new ArrayList<>();
        Response<PersonDto> response = new Response<>();
        HttpStatus status= HttpStatus.BAD_REQUEST;
        try {
            personService.delete(mail);
            messages.add("usuario eliminado con exito");
            status = HttpStatus.OK;
        } catch (CustomException e) {
            messages.add(e.getMessage());
        }
        response.setStatus(status);
        response.setMessage(messages);
        responseEntity = new ResponseEntity<>(response,status);
        return responseEntity;
    }
    @PatchMapping("/user/{email}")
    public ResponseEntity<Object> updateUser(@PathVariable("email") String email, @RequestBody PersonEntity personEntity){
        ResponseEntity<Object> response;
        try {
            PersonDto person = personService.patch(email, personEntity);
            CustomResponse customResponse = new CustomResponse("Usuario actulizado con exito", HttpStatus.OK);
            customResponse.setResponseObject(person);
            response = new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (CustomException e) {
            response = new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
