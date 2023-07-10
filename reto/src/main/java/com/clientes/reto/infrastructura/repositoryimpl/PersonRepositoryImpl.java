package com.clientes.reto.infrastructura.repositoryimpl;

import com.clientes.reto.domain.dto.PersonDto;
import com.clientes.reto.infrastructura.adapter.IPersonRepository;
import com.clientes.reto.infrastructura.entity.PersonEntity;
import com.clientes.reto.infrastructura.mappers.PersonMapper;
import com.clientes.reto.infrastructura.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PersonRepositoryImpl implements IPersonRepository {

    @Autowired
    @Lazy
    PersonRepository personRepository;

    @Autowired
    PersonMapper mapper;
    @Override
    public Iterable<PersonDto> getAllUsers() {
        return mapper.toPeople((List<PersonEntity>)personRepository.findAll());
    }


    @Override
    public void delete(String mail) {
        personRepository.deleteById(mail);
    }

    @Override
    public PersonDto finById(String mail) {
        return mapper.toPersonDto(personRepository.findOneById(mail));
    }


    @Override
    public PersonDto save(PersonDto personDto) {
        PersonEntity person =  mapper.toPersonEntity(personDto);
        return mapper.toPersonDto(personRepository.save(person));
    }
}
