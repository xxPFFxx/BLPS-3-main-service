package com.example.uploadingfiles.controllers;

import com.example.uploadingfiles.exceptions.UserNotFoundException;
import com.example.uploadingfiles.model.User;
import com.example.uploadingfiles.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) throws IOException {
          return userService.addUser(user);
    }

    @PostMapping("/checkAuth")
    public Map<String, String> checkAuth(@RequestBody User user){
        HashMap<String, String> map = new HashMap<>();

        if (userService.checkAuth(user)) map.put("message", "Пользователь с ником " + user.getUsername() + " зарегистрирован");
        else map.put("message", "Пользователь с ником " + user.getUsername() + " не зарегистрирован или пароль неверный");
        return map;
    }

    @GetMapping(value = "/admin/getAllUsers", produces = "application/json")
    public List<User> getUsers() throws IOException {
        return userService.getUsers();
    }

    @PostMapping(value = "/subscribe/{username}")
    public ResponseEntity<?> subscribe(@PathVariable String username, Principal principal) throws UserNotFoundException {
        if (username.equals(principal.getName())){
            return new ResponseEntity<>("Нельзя подписаться на самого себя", HttpStatus.BAD_REQUEST);
        }
        userService.subscribe(username, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/unsubscribe/{username}")
    public ResponseEntity<?> unsubscribe(@PathVariable String username, Principal principal) throws UserNotFoundException {
        if (username.equals(principal.getName())){
            return new ResponseEntity<>("Нельзя отписаться от самого себя", HttpStatus.BAD_REQUEST);
        }
        userService.unsubscribe(username, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}