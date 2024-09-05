package com.example.user_crud.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentRepo extends MongoRepository<Department, String> {

}
