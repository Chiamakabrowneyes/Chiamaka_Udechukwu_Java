package com.company;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String name;

    private List<Integer> charges = new ArrayList<Integer>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {

        //initialize the balance variable
        int balance = 0;

        //iterate through all charges and update balance
        for (Integer charge: charges )
            balance = balance + charge;
        return balance;
    }


    public List<Integer> getCharges() {
        return charges;
    }

    public void setCharges(List<Integer> charges) {

        this.charges = charges;
    }

    @Override
    public String toString() {
        //using -1 to refer to an object with an uninitialized ID, which will be then set to 0
        if (getId() == -1) {
            setId(0);
        }

        //Return string with spaces for eligibility
        return id +  " " + name + " " + charges + " " + getBalance();
    }
}
