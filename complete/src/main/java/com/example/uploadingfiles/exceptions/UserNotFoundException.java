package com.example.uploadingfiles.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="User Not Found") //404
public class UserNotFoundException extends Exception{
    public UserNotFoundException(String username){
        super("UserNotFoundException with link="+username);
    }
}
