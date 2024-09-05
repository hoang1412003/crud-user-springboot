package com.example.user_crud.controllers;

import com.example.user_crud.models.Department;
import com.example.user_crud.models.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentRepo departmentRepo;

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        try {
            Department newDepartment = departmentRepo.save(new Department(department.getNameDepartment(), department.getUsers()));
            return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        try {
            List<Department> departments = new ArrayList<>();
            departmentRepo.findAll().forEach(departments::add);
            if (departments.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable("id") String id) {
        try {
            Optional<Department> department = departmentRepo.findById(id);
            if(department.isPresent()) {
                return new ResponseEntity<>(department.get(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") String id, @RequestBody Department department) {
        try {
            Department existingDepartment = departmentRepo.findById(id).orElse(null);

            if(existingDepartment != null) {
                existingDepartment.setNameDepartment(department.getNameDepartment());
                existingDepartment.setUsers(department.getUsers());
                return new ResponseEntity<>(departmentRepo.save(existingDepartment), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("id") String id) {
        try {
            if(departmentRepo.existsById(id)) {
                departmentRepo.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
