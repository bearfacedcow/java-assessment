package com.javaassessment.data;

public class City extends DataBean {
    public City(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Missing Data for Province");
        }

        setName(name);
    }

    public City(String name, School school) {
        if (name == null) {
            throw new IllegalArgumentException("Missing Data for Province");
        }
        setName(name);

        addSchool(school);
    }

    public School addSchool(School school) {
        School foundSchool = (School) addData(school);

        if (foundSchool != school) {
            school.getSchoolClass().forEach(foundSchool::addSchoolClass);
        }

        return foundSchool;
    }
}
