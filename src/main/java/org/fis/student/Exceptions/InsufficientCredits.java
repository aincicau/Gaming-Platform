package org.fis.student.Exceptions;

public class InsufficientCredits extends Exception {
    public InsufficientCredits() {
        super("Not enough credits!");
    }
}
