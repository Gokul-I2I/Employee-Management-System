package com.ideas2it.ems.exception;

/**
 * This class handle the custom exception 
 * @author Gokul
 */
public class MyException extends Exception {
  
    public MyException(String message, Throwable error) {  
        super(message, error);  
    }  
}