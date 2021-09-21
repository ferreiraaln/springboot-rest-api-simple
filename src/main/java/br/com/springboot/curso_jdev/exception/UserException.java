package br.com.springboot.curso_jdev.exception;

public class UserException extends RuntimeException{
	
    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }
}
