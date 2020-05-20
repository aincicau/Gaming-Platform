package org.fis.student.Exceptions;

public class GameAlreadyPurchased extends Exception{
    public GameAlreadyPurchased(){
        super("This game is already in library");
    }
}
