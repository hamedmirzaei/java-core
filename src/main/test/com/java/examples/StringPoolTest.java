package com.java.examples;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class StringPoolTest {

    @Test
    public void stringValueAndTypeReference1() {
        String one = "hello";
        String two = "hello";

        assertTrue("String are with the same value",one.equals(two));
        assertTrue("String are with the same type and value because of String pool and String Internalization",one == two);
    }

    @Test
    public void stringValueAndTypeReference2() {
        String one = "10";
        String two = new Integer(10).toString();
        String three = new Integer(10).toString().intern();

        assertTrue("String are with the same value", one.equals(two));
        assertTrue("String are with the same value but with different types",one != two);
        assertTrue("String are with the same type and value because of String pool and String Internalization",one == three);
    }

}
