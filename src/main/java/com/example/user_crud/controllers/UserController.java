package com.example.user_crud.controllers;

import com.example.user_crud.models.User;
import com.example.user_crud.models.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User newUser = userRepo.save(new User(user.getName(), user.getPosition(), user.getAge(),user.getSkills()));
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        try {
            List<User> users = new ArrayList<>();
            userRepo.findAll().forEach(users::add);
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        try {
            // Tìm người dùng theo ID
            User user = userRepo.findById(id).orElse(null);

            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                // Trả về mã trạng thái 404 nếu không tìm thấy người dùng
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Trả về mã trạng thái 500 nếu có lỗi trong quá trình xử lý
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User userDetails) {
        try {
            // Tìm người dùng theo ID
            User existingUser = userRepo.findById(id).orElse(null);

            if (existingUser != null) {
                // Cập nhật thông tin người dùng
                existingUser.setName(userDetails.getName());
                existingUser.setPosition(userDetails.getPosition());
                existingUser.setAge(userDetails.getAge());
                existingUser.setSkills(userDetails.getSkills());

                // Lưu lại thông tin người dùng đã cập nhật
                User updatedUser = userRepo.save(existingUser);
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            } else {
                // Trả về mã trạng thái 404 nếu không tìm thấy người dùng
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Trả về mã trạng thái 500 nếu có lỗi trong quá trình xử lý
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        try {
            // Kiểm tra sự tồn tại của người dùng
            if (userRepo.existsById(id)) {
                // Xóa người dùng
                userRepo.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // Trả về mã trạng thái 404 nếu không tìm thấy người dùng
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Trả về mã trạng thái 500 nếu có lỗi trong quá trình xử lý
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
