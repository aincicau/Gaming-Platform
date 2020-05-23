package org.fis.student.Exceptions;

public class GameAlreadyExists extends Exception {
    public GameAlreadyExists(){
        super("Game already exists on platform");
    }
}
