package com.example.user_crud.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User {
    @Id
    private String id;
    private String name;
    private int age;
    private String position;
    private String[] skills;

    public User() {
    }

    public User(String name, String position, int age, String[] skills) {
        this.name = name;
        this.position = position;
        this.age = age;
        this.skills = skills;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }
}
