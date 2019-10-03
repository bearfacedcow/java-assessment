package com.javaassessment.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DataBean {
    protected String name;
    protected List<DataBean> data;

    public DataBean() {
        data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) { this.name = name; }

    public DataBean addData(DataBean dataBean) {
        Optional<DataBean> dataBeanOptional = getDataByName(dataBean.getName());

        DataBean result = dataBeanOptional.orElse(dataBean);

        if (result == dataBean) {
            data.add(dataBean);

            // Only need sort after we add
            Comparator<DataBean> compareByName = Comparator.comparing(DataBean::getName);
            data.sort(compareByName);
        }
        
        return result;
    }

    public Optional<DataBean> getDataByName(String criteriaName) {
        Predicate<DataBean> searchCriteria = dataSearch -> dataSearch.getName().equals(criteriaName);
        return data.stream().filter(searchCriteria).findFirst();
    }

    public String generateReport() {
        String report = getData().stream().map(DataBean::generateReport).reduce((rpt, gen) -> rpt + gen).orElse("");

        return report + generatedReportSum();
    }

    public int getTotal() {
        return data.stream().map(DataBean::getTotal).reduce((tot, gen) -> tot + gen).orElse(0);
    }

    private String generatedReportSum() {
        return String.format("%s\t%d\n", name, getTotal());
    }

    public List<DataBean> getData() {
        return data;
    }
}
