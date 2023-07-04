package com.clientes.reto.domain.repository;

import com.clientes.reto.domain.dto.PersonDto;

public interface IPersonRepository {

    Iterable<PersonDto> getAllUsers();
    void delete(String mail);
    PersonDto finById(String mail);
    PersonDto save(PersonDto personDto);

}
