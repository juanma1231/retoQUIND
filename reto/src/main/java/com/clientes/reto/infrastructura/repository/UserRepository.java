package com.clientes.reto.infrastructura.repository;

import com.clientes.reto.infrastructura.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,String> {
}
