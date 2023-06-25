package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );

    public static void main(String[] args) {
        List<Customer> newCustomerData = getCustomerData(customerData);
        printGroupedNames(newCustomerData);
        printGroupedAccounts(newCustomerData);
    }

    public static List<Customer> getCustomerData(List<String[]> customerData){

        //initializing storage variables
        List<Customer> newCustomerData = new ArrayList<>();
        List<Integer> customerAdded = new ArrayList<>();

        //iterating the customer string list and populating the customer object list
        for (int i = 0; i < customerData.size(); i++){
            String[] strCustomer = customerData.get(i);

            //creating customer personal and account records
            Customer customer = new Customer();
            AccountRecord accountRecord = new AccountRecord();

            //collecting the customer's info from the String List
            Integer customerID = Integer.parseInt(strCustomer[0]);
            String customerName = strCustomer[1];
            String customerCharge = strCustomer[2];

            //populating the customer object
            customer.setId(customerID);
            customer.setName(customerName);

            //checking for existence of a unique customer object and updating with charges
            if (! customerAdded.contains(customerID)){
                accountRecord.setCharge(Integer.parseInt(customerCharge));

                List<Integer> charge = new ArrayList<>();
                charge.add(Integer.valueOf(accountRecord.getCharge()));
                customer.setCharges(charge);

                newCustomerData.add(customer);
                customerAdded.add(customerID);
            } else {
                for (Customer cust : newCustomerData){
                    if (cust.getId() == customerID){
                        List<Integer> charges = cust.getCharges();
                        charges.add(Integer.valueOf(customerCharge));
                        customer.setCharges(charges);
                    }
                }
            }
        }
        //returning output
        return newCustomerData;
    }

    public static void printGroupedNames(List<Customer> newCustomerData){
        //creating a stream that classifies the input array according to the polarity of the balance and maps them by their name
        Map<Boolean, List<String>> groupedAccountNames = newCustomerData.stream()
                .collect(Collectors.partitioningBy(customer -> customer.getBalance() >= 0,
                        Collectors.mapping(Customer::getName, Collectors.toList())));

        //collecting the positive and negative groups
        List<String> positiveBalanceNames = groupedAccountNames.get(true);
        List<String> negativeBalanceNames = groupedAccountNames.get(false);

        //printing the positive and negative groups by an array of their names
        System.out.println("Positive balance names: " + positiveBalanceNames);
        System.out.println("Negative balance names: " + negativeBalanceNames);
    }

    public static void printGroupedAccounts(List<Customer> newCustomerData){
        //creating a stream that classifies the input array according to the polarity of the balance
        Map<Boolean, List<Customer>> groupedAccounts = newCustomerData.stream()
                .collect(Collectors.partitioningBy(customer -> customer.getBalance() >= 0));

        //collecting the positive and negative groups by the account object
        List<Customer> positiveBalanceAccounts = groupedAccounts.get(true);
        List<Customer> negativeBalanceAccounts = groupedAccounts.get(false);

        //printing out
        System.out.println("Positive balance accounts: " + positiveBalanceAccounts);
        System.out.println("Negative balance accounts: " + negativeBalanceAccounts);
    }
}
