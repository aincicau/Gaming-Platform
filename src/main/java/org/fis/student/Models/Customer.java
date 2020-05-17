package org.fis.student.Models;

public class Customer {

    private String ID;
    private String password;

    public Customer(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    public Customer() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        if(!ID.equals(customer.ID))
            return false;
        return password.equals(customer.password);
    }

    @Override
    public int hashCode() {
        int result=ID.hashCode();
        return 31*result + password.hashCode();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID='" + ID + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
