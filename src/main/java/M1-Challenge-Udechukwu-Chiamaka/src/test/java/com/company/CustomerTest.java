package com.company;

import com.company.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class CustomerTest {
    private List<Integer> charges;
    private Customer customer;

    @BeforeEach
    void setUp() {
        charges = new ArrayList<>();
        charges.add(100);
        charges.add(950);
        charges.add(200);
        charges.add(2300);
        charges.add(-2900);

        customer = new Customer();

        customer.setId(23);
        customer.setName("Ifunanya Ike");
        customer.setCharges(charges);
    }

    @Test
    void testGetBalance() {
        int expectedBalance = 0;
        for (Integer charge : charges) {
            expectedBalance += charge;
        }

        int actualBalance = customer.getBalance();

        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void testEmptyBalance(){
        charges.clear();

        int expectedBalance = 0;
        int actualBalance = customer.getBalance();

        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void testNegativeBalance(){
        charges.clear();
        charges.add(-1000);

        int expectedBalance = -1000;
        int actualBalance = customer.getBalance();

        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void testPositiveBalance(){
        charges.clear();
        charges.add(1000);

        int expectedBalance = 1000;
        int actualBalance = customer.getBalance();

        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void testToString() {
        String expectedString = "23 Ifunanya Ike [100, 950, 200, 2300, -2900] 650";
        String actualString = customer.toString();

        assertEquals(expectedString, actualString);
    }

    @Test
    void testToStringNoName() {
        customer.setName(null);
        String expectedString = "23 null [100, 950, 200, 2300, -2900] 650";
        String actualString = customer.toString();

        assertEquals(expectedString, actualString);
    }

    @Test
    void testToStringNoId() {

        customer.setId(-1);

        String expectedString = "0 Ifunanya Ike [100, 950, 200, 2300, -2900] 650";
        String actualString = customer.toString();

        assertEquals(expectedString, actualString);
    }

}
