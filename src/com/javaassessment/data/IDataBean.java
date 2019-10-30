package com.javaassessment.data;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface IDataBean {
    public String getName();

    public List<IDataBean> getData();

    default Optional<IDataBean> getDataByName(String criteriaName) {
        Predicate<IDataBean> searchCriteria = dataSearch -> dataSearch.getName().equals(criteriaName);
        return getData().stream().filter(searchCriteria).findFirst();
    }

    default IDataBean addData(IDataBean dataBean) {
        IDataBean result = getDataByName(dataBean.getName()).orElse(dataBean);

        if (result == dataBean) {
            getData().add(dataBean);

            // Only need sort after we add
            Comparator<IDataBean> compareByName = Comparator.comparing(IDataBean::getName);
            getData().sort(compareByName);
        }

        return result;
    }

    default String generateReport() {
        String report = getData().stream().map(IDataBean::generateReport).reduce((rpt, gen) -> rpt + gen).orElse("");

        return report + generatedReportSum();
    }

    default int getTotal() {
        return getData().stream().map(IDataBean::getTotal).reduce((tot, gen) -> tot + gen).orElse(0);
    }

    default String generatedReportSum() {
        return String.format("%s\t%d\n", getName(), getTotal());
    }

}
