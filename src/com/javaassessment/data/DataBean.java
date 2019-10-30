package com.javaassessment.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DataBean implements IDataBean {
    protected String name;
    protected List<IDataBean> data;

    public DataBean() {
        data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) { this.name = name; }

    public List<IDataBean> getData() {
        return data;
    }
}
