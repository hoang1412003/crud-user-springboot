package com.example.user_crud.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("department")
public class Department {

    @Id
    private String id;
    private String nameDepartment;
    private List<User> users;

    public Department() {
    }

    public Department(String nameDepartment, List<User> users) {
        this.nameDepartment = nameDepartment;
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
