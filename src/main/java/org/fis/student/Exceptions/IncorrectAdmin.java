package org.fis.student.Exceptions;

public class IncorrectAdmin extends Exception {
    public IncorrectAdmin() {
        super("Admin does not exist!");
    }
}
