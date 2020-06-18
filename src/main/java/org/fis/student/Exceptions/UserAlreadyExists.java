package org.fis.student.Exceptions;

public class UserAlreadyExists extends Exception {
    public UserAlreadyExists() {
        super("User already exists!");
    }
}
