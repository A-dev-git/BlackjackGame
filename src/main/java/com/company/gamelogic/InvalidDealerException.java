package com.company.gamelogic;

public class InvalidDealerException extends RuntimeException{
    public InvalidDealerException(String errorMessage){
        super(errorMessage);
    }
}
