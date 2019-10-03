package com.javaassessment.data;

import java.util.ArrayList;
import java.util.List;

public class School extends DataBean {
    protected List<SchoolClass> data;

    public String getName() {
        return name;
    }

    public School(String schoolName, String schoolClass) {
        data = new ArrayList<>();

        if (schoolName == null || schoolClass == null) {
            throw new IllegalArgumentException("Missing Data for School");
        }

        this.name = schoolName;

        addSchoolClass(new SchoolClass(schoolClass));
    }

    public void addSchoolClass(SchoolClass schoolClass) {
        data.add(schoolClass);
    }

    public String generateReport() {
        return String.format("%s\t%d\n", name, getTotal());
    }

    public int getTotal() {
        return data.stream().map(SchoolClass::getClassSize).reduce((tot, amt) -> tot + amt).orElse(0);
    }

    public List<SchoolClass> getSchoolClass() {
        return data;
    }
}
