package com.clientes.reto.service;

import com.clientes.reto.domain.entity.PersonEntity;
import com.clientes.reto.domain.entity.ProductEntity;
import com.clientes.reto.domain.enums.State;
import com.clientes.reto.repository.ProductRepository;
import com.clientes.reto.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    PersonService personService;

    public ProductEntity finById(Integer id) {
        return productRepository.findById(id).orElseThrow(()-> new CustomException("Producto no encontrado"));
    }

    public ProductEntity save(ProductEntity product){

        return productRepository.save(product);
    }
    public ProductEntity create(ProductEntity product){
        PersonEntity person = personService.findById(product.getIdClient());
        if(person!=null){
            product.setDeaudas(false);
            product.setState(State.ACTIVO);
            Date currentDatw = new Date();
            product.setCreationDate(currentDatw);
            product.setUpdateDate(currentDatw);
            product.setTransactions(new ArrayList<>());
            return  productRepository.save(product);
        }else throw new CustomException("Usuario no existe");


    }
    public  List<ProductEntity> finByUser(String email){
        return productRepository.finByUser(email);
    }
    public ProductEntity inactive(Integer accountId){
        ProductEntity product = productRepository.finById(accountId);
        product.setState(State.INACTIVO);
        Date currentDate = new Date();
        product.setUpdateDate(currentDate);
        return productRepository.save(product);
    }
    public ProductEntity cancelar(Integer accountId){
        ProductEntity product = productRepository.finById(accountId);
        if (product.getBalance() < 1 && Boolean.TRUE.equals(!product.getDeaudas())){
            product.setState(State.CANCELADA);
            Date currentDate = new Date();
            product.setUpdateDate(currentDate);
            return productRepository.save(product);
        }else throw new CustomException("No se puede cancelar una cuenta con un saldo superior a 1");

    }
    public Iterable<ProductEntity> getALl(){
        return productRepository.findAll();
    }

}
