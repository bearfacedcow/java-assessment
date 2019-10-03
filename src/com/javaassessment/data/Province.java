package com.javaassessment.data;

public class Province extends DataBean {

    public Province(String provinceName) {
        super();
        if (provinceName == null) {
            throw new IllegalArgumentException("Missing Data for Province");
        }
        
        this.name = provinceName;
    }

    public Province(String provinceName, City city) {
        super();
        if (provinceName == null) {
            throw new IllegalArgumentException("Missing Data for Province");
        }
        this.name = provinceName;

        addCity(city);
    }


    public City addCity(City city) {
        return (City) addData(city);
    }

}
