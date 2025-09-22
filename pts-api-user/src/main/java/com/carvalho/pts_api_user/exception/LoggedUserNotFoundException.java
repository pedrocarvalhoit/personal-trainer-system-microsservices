package com.carvalho.pts_api_user.exception;

public class LoggedUserNotFoundException extends RuntimeException{

    public LoggedUserNotFoundException(String message) {
        super(message);
    }

    public static LoggedUserNotFoundException forUsername(String username){
        return new LoggedUserNotFoundException("User with id: " + username + " not found");
    }
}
