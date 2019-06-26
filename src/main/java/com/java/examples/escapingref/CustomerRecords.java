package com.java.examples.escapingref;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomerRecords {

    private Map<String, Customer> records = new HashMap<>();

    public CustomerRecords() {
        this.records.put("Hamed", new Customer("Hamed"));
        this.records.put("Ali", new Customer("Ali"));
    }

    // making it unmodifiable by using Collections.unmodifiableMap
    public Map<String, Customer> getRecords() {
        return Collections.unmodifiableMap(this.records);
    }

    public void setRecords(Map<String, Customer> records) {
        this.records = records;
    }

    // making it unmodifiable by creating this interface
    public CustomerReadOnly getCustomerByName(String name) {
        return this.records.get(name);
    }
}
