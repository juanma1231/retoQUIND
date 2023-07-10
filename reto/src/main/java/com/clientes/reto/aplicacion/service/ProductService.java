package com.clientes.reto.aplicacion.service;

import com.clientes.reto.domain.dto.PersonDto;
import com.clientes.reto.domain.dto.ProductDto;
import com.clientes.reto.domain.adapter.IPersonRepository;
import com.clientes.reto.domain.adapter.IProdcutDtoRepository;
import com.clientes.reto.aplicacion.usecase.IProductUseCase;
import com.clientes.reto.infrastructura.enums.AccountType;
import com.clientes.reto.infrastructura.enums.State;
import com.clientes.reto.utils.CustomException;
import static com.clientes.reto.utils.NumeroCuentaGenerator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService implements IProductUseCase {
    @Autowired
    @Lazy
    IProdcutDtoRepository productDtoRepository;

    @Autowired
    @Lazy
    IPersonRepository personService;


    public ProductDto finById(String id) {
        return productDtoRepository.finById(id);
    }

    public ProductDto save(ProductDto product){

        return productDtoRepository.save(product);
    }
    public ProductDto create(ProductDto product){
        PersonDto person = personService.finById(product.getIdClient());
        boolean unic= false;
        String accountNumber;
        if(product.getAccountType().equals(AccountType.AHORROS)) {
            while(!unic){
                accountNumber = generarNumeroCuentaAhorro();
                ProductDto productDto11 = productDtoRepository.finById(accountNumber);
                if (productDto11==null){
                    product.setProductNumber(accountNumber);
                    unic = true;
                }
            }
        } else if (product.getAccountType().equals(AccountType.CORRIENTE)) {
            while (!unic){
                accountNumber = generarNumeroCuentaCorriente();
                ProductDto productDto1 = productDtoRepository.finById(accountNumber);
                if (productDto1== null){
                    product.setProductNumber(accountNumber);
                    unic=true;
                }
            }

        }
        if(person!=null){
            product.setDeudas(false);
            product.setState(State.ACTIVO);
            Date currentDatw = new Date();
            product.setCreationDate(currentDatw);
            product.setUpdateDate(currentDatw);
            return  productDtoRepository.save(product);
        }else throw new CustomException("Usuario no existe");




    }
    public  List<ProductDto> finByUser(String email){
        return productDtoRepository.finByUser(email);
    }
    public ProductDto inactive(String accountId){
        ProductDto product = productDtoRepository.finById(accountId);
        product.setState(State.INACTIVO);
        Date currentDate = new Date();
        product.setUpdateDate(currentDate);
        return productDtoRepository.save(product);
    }
    public ProductDto cancelar(String accountId){
        ProductDto product = productDtoRepository.finById(accountId);
        if (product.getBalance() < 1 && Boolean.TRUE.equals(!product.isDeudas())){
            product.setState(State.CANCELADA);
            Date currentDate = new Date();
            product.setUpdateDate(currentDate);
            return productDtoRepository.save(product);
        }else throw new CustomException("No se puede cancelar una cuenta con un saldo superior a 1");

    }
    public List<ProductDto> getALl(){
        return productDtoRepository.getAll();
    }

}
