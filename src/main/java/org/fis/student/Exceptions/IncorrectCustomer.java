package org.fis.student.Exceptions;

public class IncorrectCustomer extends Exception{
    public IncorrectCustomer() {
        super("Customer does not exist!");
    }
}
