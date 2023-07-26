package com.softdevelop.biomedplus.exception;

public class GenericException extends RuntimeException{

    private static final long serialVersionUID = 1375128773220627189L;

    public GenericException (String errorMessage){
            super(errorMessage);
     }
}

