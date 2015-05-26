package com.wtransnet.app.cleancode.domain.entities;

import java.io.Serializable;

/**
 * Bean que encapsula las propiedades del nombre
 */
public class Name implements Serializable {

    private String firstName;
    private String lastName;

    public Name() { }

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
