package com.jamieelliott.reccit.exceptions;

public class ReccitException extends RuntimeException {

    public ReccitException(String exMessage) {
        super(exMessage);
    }

    public ReccitException(String exMessage, Exception exception){
        super(exMessage, exception);
    }

}
