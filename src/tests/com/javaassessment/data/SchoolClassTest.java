package com.javaassessment.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchoolClassTest {

    @Test
    public void testNewSchoolClass() {
        String validClass = "K2000";
        SchoolClass schoolClass = new SchoolClass(validClass);

        assertEquals("K", schoolClass.getClassGrade());
        assertEquals( 2000, schoolClass.getClassSize());
    }

    @Test
    public void testInvolidSchoolClass() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SchoolClass("BAD2");
        });
    }
}