package com.clientes.reto.aplicacion.service.usecase;

import com.clientes.reto.domain.dto.PersonDto;

public interface IPersonUseCase {
    Iterable<PersonDto> getAllUsers();
    PersonDto create(PersonDto personDto);
    void  delete(String email);
    PersonDto findById(String email);
    PersonDto patch(String email, PersonDto personDto);
    PersonDto save(PersonDto personDto);

}
