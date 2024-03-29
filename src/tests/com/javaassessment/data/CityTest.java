package com.javaassessment.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {
    private City testCity;
    private String testCityName = "Ontario";
    private String testSchoolName = "Test School";
    private String testSchoolClass = "10200";
    private int testSchoolSize = 200;

    @BeforeEach
    void setUp() {
        this.testCity = new City(this.testCityName);
    }

    @Test
    void getName() {
        assertEquals(testCity.getName(), this.testCityName);
    }

    @Test
    void testBadCity() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new City(null);
        });
    }

    @Test
    void testEmptySchools() {
        assertTrue(this.testCity.getDataByName(this.testSchoolName).isEmpty());
    }

    @Test
    void testCityWithOneSchool() {
        this.testCity = new City(this.testCityName, new School(this.testSchoolName, this.testSchoolClass));

        School testSchool = (School) this.testCity.getDataByName(this.testSchoolName).get();

        assertEquals(testSchool.getName(), testSchoolName);
    }

    @Test
    void testCityForOneSchool() {
        this.testCity = new City(this.testCityName, new School(this.testSchoolName, this.testSchoolClass));
        School testSchoool = new School(this.testSchoolName, "70300");
        School school = testCity.addSchool(testSchoool);

        assertNotSame(school, testSchoool);
    }

    @Test
    void testCityForMultipleSchools() {
        this.testCity = new City(this.testCityName, new School(this.testSchoolName, this.testSchoolClass));
        School testSchoool = new School("A Different School", "70300");
        School school = testCity.addSchool(testSchoool);

        List<IDataBean> schools = testCity.getData();

        assertTrue(schools.size() > 1);
    }

    @Test
    void testGeneratedReport() {
        this.testCity = new City(this.testCityName, new School(this.testSchoolName, this.testSchoolClass));

        String testReport = String.format("%s\t%d\n%s\t%d\n", testSchoolName, testSchoolSize, testCityName, testSchoolSize);

        String report = testCity.generateReport();

        assertEquals( testReport, report );
    }
}
