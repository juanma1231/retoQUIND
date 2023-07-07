package com.clientes.reto.controller;
import com.clientes.reto.domain.dto.TransactionDto;
import com.clientes.reto.domain.usecase.ITransactionUseCase;
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
    ITransactionUseCase iTransactionUseCase;

    @PostMapping("/make")
    public ResponseEntity<Response<TransactionDto>> crear(@RequestBody TransactionDto transactionEntity){
        ResponseEntity<Response<TransactionDto>> responseEntity;
        List<String> messages = new ArrayList<>();
        List<TransactionDto> data = new ArrayList<>();
        Response<TransactionDto> response = new Response<>();
        HttpStatus status= HttpStatus.BAD_REQUEST;

        if(transactionEntity.getTransaccionType().equals(TransactionsEnum.CONSIGNACION)){
            try {
                data.add(iTransactionUseCase.consignar(transactionEntity));
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

        } else if (transactionEntity.getTransaccionType().equals(TransactionsEnum.RETIRO)) {
            try {
                data.add(iTransactionUseCase.retirar(transactionEntity));
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
    public ResponseEntity<Response<TransactionDto>> tranferir(@RequestBody TransactionDto transactionDto, @PathVariable("accountid") String accountId){
        ResponseEntity<Response<TransactionDto>> responseEntity;
        List<String> messages = new ArrayList<>();
        List<TransactionDto> data = new ArrayList<>();
        Response<TransactionDto> response = new Response<>();
        HttpStatus status= HttpStatus.BAD_REQUEST;
        try {
            data.add(iTransactionUseCase.doATransference(accountId, transactionDto));
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
    public ResponseEntity<Response<TransactionDto>> finById(@PathVariable("id") String accountId){
        ResponseEntity<Response<TransactionDto>> responseEntity;
        List<String> messages = new ArrayList<>();
        Response<TransactionDto> response = new Response<>();
        HttpStatus status= HttpStatus.BAD_REQUEST;
        try {
            List<TransactionDto> transactionEntityList = iTransactionUseCase.findByAccountId(accountId);
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
