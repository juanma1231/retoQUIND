package com.clientes.reto.repository;

import com.clientes.reto.domain.entity.PersonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM person p WHERE p.email =?1")
    PersonEntity findOneById(String email);

}
