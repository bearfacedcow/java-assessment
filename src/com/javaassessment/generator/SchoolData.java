package com.javaassessment.generator;

import com.javaassessment.data.City;
import com.javaassessment.data.Province;
import com.javaassessment.data.School;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

public class SchoolData {
    private List<Province> provinces;

    public SchoolData() {
        this.provinces = new ArrayList<>();
    }

    public void generate(String filename) {
        String cwd = System.getProperty("user.dir");
        File file = new File(cwd + "/" + filename);
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                parseAndCreateData(input);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Optional<Province> getProvinceByName(String provinceName) {
        Predicate<Province> provinceSearch = province -> province.getName().equals(provinceName);
        return provinces.stream().filter(provinceSearch).findFirst();
    }

    public Province addProvince(Province province) {
        Province foundProvince = getProvinceByName(province.getName()).orElse(province);

        if (foundProvince == province) {
            provinces.add(province);
        }
        
        return foundProvince;
    }

    private void parseAndCreateData(String input) {
        String provinceStr, cityStr, schoolStr, classStr;
        Scanner scanner = new Scanner(input);
        scanner.useDelimiter("\t");

        while (scanner.hasNext()) {
            provinceStr = scanner.next();
            cityStr = scanner.next();
            schoolStr = scanner.next();
            classStr = scanner.next();

            addProvince(new Province(provinceStr))
                    .addCity(new City(cityStr))
                    .addSchool(new School(schoolStr, classStr));
         }

        scanner.close();
    }

    public String report() {
        return provinces.stream().map(Province::generateReport).reduce((rpt, gen) -> rpt + gen).orElse("");
    }
}
