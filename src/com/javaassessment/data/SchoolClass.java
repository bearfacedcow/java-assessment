package com.javaassessment.data;

public class SchoolClass {
    private final String classGrade;
    private final int classSize;

    public String getClassGrade() {
        return classGrade;
    }

    public int getClassSize() {
        return classSize;
    }

    public SchoolClass(String classStr) {
        int classSize = 0;

        try {
            classSize = Integer.parseInt(classStr.substring(1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Illegal Data for School");
        }

        classGrade = classStr.substring(0,1);
        this.classSize = classSize;
    }
}
