package com.java.examples.escapingref;

import org.junit.Before;
import org.junit.Test;

public class EscapingReferenceTest {

    private CustomerRecords customerRecords;

    @Before
    public void before() {
        System.out.println("This test is going to test escaping reference");
        customerRecords = new CustomerRecords();
    }


    @Test(expected = UnsupportedOperationException.class)
    public void exceptionOnChangingUnmodifiableMap() {
        customerRecords.getRecords().put("Reza", new Customer("Reza"));
    }

}
