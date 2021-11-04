
package Classes;

import java.io.Serializable;

public class Reader implements Serializable{
    private String firstname;
    private String surename;
    private String phoneNumber;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Reader{" + "firstname=" + firstname + ", surename=" + surename + ", phoneNumber=" + phoneNumber + '}';
    }
}
