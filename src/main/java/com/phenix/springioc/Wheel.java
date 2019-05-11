package com.phenix.springioc;

public class Wheel {
    private String brand;
    private String specification;

    public RefBean getRefBean() {
        return refBean;
    }

    public void setRefBean(RefBean refBean) {
        this.refBean = refBean;
    }

    private RefBean refBean;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}