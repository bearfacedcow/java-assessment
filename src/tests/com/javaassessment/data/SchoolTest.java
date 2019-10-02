package com.javaassessment.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchoolTest {
    private School testSchool;
    private String testSchoolName = "Test School";
    private String testSchoolClass = "K0100";

    @BeforeEach
    void setUp() {
        this.testSchool = new School(this.testSchoolName, this.testSchoolClass);
    }

    @Test
    void isValidSchool() {
        assertNotNull(this.testSchool);
    }

    @Test
    void getSchoolName() {
        assertEquals(testSchoolName, testSchool.getName());
    }

    @Test
    void testForSchoolClass() {
        List<SchoolClass> schoolClasses = testSchool.getSchoolClass();

        assertTrue(schoolClasses.size() > 0);
    }

    @Test
    void testGeneratedReport() {
        int testSchoolSize = 100;

        String reportString = String.format("%s\t%d\n", testSchoolName, testSchoolSize);
        String testReport = testSchool.generateReport();

        assertEquals( testReport, reportString );
    }

    @Test
    void testExpectedException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new School(null, this.testSchoolClass);
        });
    }

    @Test
    void testBadClassSize() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new School(testSchoolName, "3Q6QT");
        });
    }
}