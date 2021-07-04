package com.cab.cabjava.data.model;

public class Christian {
    private String name;
    private String division;
    private String parish;
    private String area;
    private String tel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getParish() {
        return parish;
    }

    public void setParish(String parish) {
        this.parish = parish;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Christian(String name, String division, String parish, String area, String tel) {
        this.name = name;
        this.division = division;
        this.parish = parish;
        this.area = area;
        this.tel = tel;
    }
}
