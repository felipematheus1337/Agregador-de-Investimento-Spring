package com.aggr.invest.controller;


import com.aggr.invest.entity.User;
import com.aggr.invest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDTO dto) {
        var userId = userService.createUser(dto);

        return ResponseEntity.created(URI.create("/v1/users/ " +userId.toString())).build();
    }

    @GetMapping("/userId")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {

        var user = userService.getUserById(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {

        var users = userService.listUsers();

        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deletar(@PathVariable String userId) {

        userService.deleteById(userId);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable String userId, @RequestBody UpdateUserDTO dto) {

         userService.updateUserById(userId, dto);

         return ResponseEntity.noContent().build();
    }
}
