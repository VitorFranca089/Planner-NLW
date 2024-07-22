package com.rocketseat.planner.exception;

public class DateOutOfBoundsException extends RuntimeException{

    public DateOutOfBoundsException(){
        super("Activity date must be between trip start and end dates");
    }

    public DateOutOfBoundsException(String message){
        super(message);
    }

}
