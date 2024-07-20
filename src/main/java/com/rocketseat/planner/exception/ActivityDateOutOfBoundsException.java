package com.rocketseat.planner.exception;

public class ActivityDateOutOfBoundsException extends RuntimeException{

    public ActivityDateOutOfBoundsException(){
        super("Activity date must be between trip start and end dates");
    }

    public ActivityDateOutOfBoundsException(String message){
        super(message);
    }

}
