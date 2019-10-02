package com.javaassessment.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        List<DataBean> dataBeanList = getDataByName(dataBean.getName());

        if (dataBeanList.size() > 0) {
            return dataBeanList.get(0);
        }

        data.add(dataBean);
        return dataBean;
    }

    public List<DataBean> getDataByName(String criteriaName) {
        Predicate<DataBean> searchCriteria = dataSearch -> dataSearch.getName().equals(criteriaName);
        return data.stream().filter(searchCriteria).collect(Collectors.toList());
    }

    public String generateReport() {
        Optional<String> reportOpt = getData().stream().map(DataBean::generateReport).reduce((rpt, gen) -> rpt + gen);
        String report = reportOpt.orElse("");

        return report + generatedReportSum();
    }

    public int getTotal() {
        Optional<Integer> totalOpt = data.stream().map(DataBean::getTotal).reduce((tot, gen) -> tot + gen);
        return totalOpt.orElse(0);
    }

    private String generatedReportSum() {
        return String.format("%s\t%d\n", name, getTotal());
    }

    public List<DataBean> getData() {
        Comparator<DataBean> compareByName = Comparator.comparing(DataBean::getName);
        data.sort(compareByName);
        return data;
    }
}
