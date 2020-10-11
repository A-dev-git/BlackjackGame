package com.company.gamelogic;

public class InvalidPlayerListException extends RuntimeException{
    public InvalidPlayerListException(String errorMessage){
        super(errorMessage);
    }
}
