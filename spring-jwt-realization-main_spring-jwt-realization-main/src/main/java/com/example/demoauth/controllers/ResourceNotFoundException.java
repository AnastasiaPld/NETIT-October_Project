package com.example.demoauth.controllers;

public class ResourceNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
 
    public ResourceNotFoundException() {}
 
    public ResourceNotFoundException(String message)
    {
       
        this.message = "Not found";
    }
}


	

