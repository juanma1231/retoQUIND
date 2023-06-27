package com.clientes.reto.controller;

import com.clientes.reto.domain.entity.TransactionEntity;
import com.clientes.reto.domain.enums.TransactionsEnum;
import com.clientes.reto.response.CustomException;
import com.clientes.reto.response.CustomResponse;
import com.clientes.reto.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/make")
    public ResponseEntity<Object> crear(@RequestBody TransactionEntity transactionEntity){
        ResponseEntity<Object> response;

        if(transactionEntity.getTransactionType().equals(TransactionsEnum.CONSIGNACION)){
            try {
                TransactionEntity transaction = transactionService.consignar(transactionEntity);
                CustomResponse customResponse = new CustomResponse("Consifacion realizada con exito", HttpStatus.OK);
                customResponse.setResponseObject(transaction);
                response = new ResponseEntity<>(customResponse, HttpStatus.OK);
            } catch (CustomException e) {
                response = new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
            }
            return response;

        } else if (transactionEntity.getTransactionType().equals(TransactionsEnum.RETIRO)) {
            try {
                TransactionEntity transaction = transactionService.retirar(transactionEntity);
                CustomResponse customResponse = new CustomResponse("Consifacion realizada con exito", HttpStatus.OK);
                customResponse.setResponseObject(transaction);
                response = new ResponseEntity<>(customResponse, HttpStatus.OK);
            } catch (CustomException e) {
                response = new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
            }
            return response;

        }
        response = new ResponseEntity<>("No se encontrè el tipo de transaccion", HttpStatus.BAD_REQUEST);

        return response;
    }
    @PostMapping("/transferencia/{accountid}")
    public ResponseEntity<Object> tranferir(@RequestBody TransactionEntity transactionEntity, @PathVariable("accountid") Integer accountId){
        ResponseEntity<Object> response;
        try {
            TransactionEntity transaction = transactionService.hacerTransferencia(accountId, transactionEntity);
            CustomResponse customResponse = new CustomResponse("Transferencia exitosa", HttpStatus.OK);
            customResponse.setResponseObject(transaction);
            response = new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (CustomException e) {
            response = new ResponseEntity<>(e, HttpStatus.OK);
        }
        return response;
    }
    @GetMapping("/transactions/{id}")
    public ResponseEntity<Object> finById(@PathVariable("id") Integer accountId){
        ResponseEntity<Object> response;
        try {
            List<TransactionEntity> transactionEntityList = transactionService.findByAccountId(accountId);
            CustomResponse customResponse = new CustomResponse("Las transacciones han sido encontradsas");
            customResponse.setResponseObject(transactionEntityList);
            response = new ResponseEntity<>(customResponse,HttpStatus.OK);
        } catch (Exception e) {
            response= new ResponseEntity<>("Hay un error tratando de econtrar las transacciones", HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
