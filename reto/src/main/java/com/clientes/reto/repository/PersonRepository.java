package com.clientes.reto.repository;

import com.clientes.reto.domain.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, String> {

}
