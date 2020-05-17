package org.fis.student.Exceptions;

public class Incorrect extends Exception{
    public Incorrect() {
        super("Customer does not exist!");
    }
}
