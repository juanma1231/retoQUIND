package com.clientes.reto.controller;
import com.clientes.reto.persistence.entity.TransactionEntity;
import com.clientes.reto.persistence.enums.TransactionsEnum;
import com.clientes.reto.utils.CustomException;
import com.clientes.reto.domain.service.TransactionService;
import com.clientes.reto.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/make")
    public ResponseEntity<Response<TransactionEntity>> crear(@RequestBody TransactionEntity transactionEntity){
        ResponseEntity<Response<TransactionEntity>> responseEntity;
        List<String> messages = new ArrayList<>();
        List<TransactionEntity> data = new ArrayList<>();
        Response<TransactionEntity> response = new Response<>();
        HttpStatus status= HttpStatus.BAD_REQUEST;

        if(transactionEntity.getTransactionType().equals(TransactionsEnum.CONSIGNACION)){
            try {
                data.add(transactionService.consignar(transactionEntity));
                response.setData(data);
                messages.add("Consignacion realizada con exito");
                status = HttpStatus.OK;
            } catch (CustomException e) {
                messages.add(e.getMessage());
            }
            response.setStatus(status);
            response.setMessage(messages);
            responseEntity = new ResponseEntity<>(response,status);
            return responseEntity;

        } else if (transactionEntity.getTransactionType().equals(TransactionsEnum.RETIRO)) {
            try {
                data.add(transactionService.retirar(transactionEntity));
                status = HttpStatus.OK;
                messages.add("Retiro realizado con exito");
            } catch (CustomException e) {
                messages.add(e.getMessage());
            }
            response.setStatus(status);
            response.setMessage(messages);
            responseEntity = new ResponseEntity<>(response,status);
            return responseEntity;

        }
        messages.add("No se pudo realizar la operacion");
        response.setMessage(messages);
        response.setStatus(status);
        responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
    @PostMapping("/transferencia/{accountid}")
    public ResponseEntity<Response<TransactionEntity>> tranferir(@RequestBody TransactionEntity transactionEntity, @PathVariable("accountid") Integer accountId){
        ResponseEntity<Response<TransactionEntity>> responseEntity;
        List<String> messages = new ArrayList<>();
        List<TransactionEntity> data = new ArrayList<>();
        Response<TransactionEntity> response = new Response<>();
        HttpStatus status= HttpStatus.BAD_REQUEST;
        try {
            data.add(transactionService.doATransference(accountId, transactionEntity));
            messages.add("Transferencia exitosa");
            status = HttpStatus.OK;
        } catch (CustomException e) {
            messages.add(e.getMessage());
        }
        response.setStatus(status);
        response.setMessage(messages);
        response.setData(data);
        responseEntity = new ResponseEntity<>(response,status);
        return  responseEntity;
    }
    @GetMapping("/transactions/{id}")
    public ResponseEntity<Response<TransactionEntity>> finById(@PathVariable("id") Integer accountId){
        ResponseEntity<Response<TransactionEntity>> responseEntity;
        List<String> messages = new ArrayList<>();
        Response<TransactionEntity> response = new Response<>();
        HttpStatus status= HttpStatus.BAD_REQUEST;
        try {
            List<TransactionEntity> transactionEntityList = transactionService.findByAccountId(accountId);
            response.setData(transactionEntityList);
            status = HttpStatus.OK;
            messages.add("Lista consultada con exito");
        } catch (Exception e) {
            messages.add(e.getMessage());
        }
        response.setStatus(status);
        response.setMessage(messages);
        responseEntity = new ResponseEntity<>(response,status);
        return responseEntity;
    }
}
