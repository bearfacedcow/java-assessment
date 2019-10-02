package com.javaassessment.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProvinceTest {
    private Province testProvince;
    private String testCityName = "WaterLoo";
    private String testProvinceName = "Ontario";
    private String testSchoolName = "Test School";
    private String testSchoolClass = "10200";
    private int testSchoolSize = 200;

    @BeforeEach
    void setUp() {
        testProvince = new Province(testProvinceName, new City(testCityName));
    }

    @Test
    void getName() {
        assertEquals(testProvince.getName(), this.testProvinceName);
    }

    @Test
    void testForOnlyOneCity() {
        City newCity = new City(testCityName);
        City ourCity = testProvince.addCity(newCity);

        assertNotEquals(newCity, ourCity);
    }

    @Test
    void testBadProvince() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Province(null);
        });
    }

    @Test
    void testForExistingCity() {
        City city = (City) testProvince.getDataByName(testCityName).get(0);

        assertNotNull(city);
    }

    @Test
    void testGeneratedReport() {
        City testCity = (City) testProvince.getDataByName(testCityName).get(0);
        testCity.addSchool(new School(testSchoolName, testSchoolClass));

        String testReport = String.format("%s\t%d\n%s\t%d\n%s\t%d\n", testSchoolName, testSchoolSize, testCityName,
                testSchoolSize, testProvinceName, testSchoolSize);

        String report = testProvince.generateReport();

        assertEquals(testReport, report);
    }
}