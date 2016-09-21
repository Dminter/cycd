package com.zncm.easycycd.data.base;

import com.j256.ormlite.field.DatabaseField;
import com.zncm.easycycd.data.BaseData;

import java.io.Serializable;

public class CyData extends BaseData implements Serializable {

    private static final long serialVersionUID = 8838725426885988959L;
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String py;
    @DatabaseField
    private String js;
    @DatabaseField
    private String cc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getJs() {
        return js;
    }

    public void setJs(String js) {
        this.js = js;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }


    @Override
    public String toString() {
        return "CyData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", py='" + py + '\'' +
                ", js='" + js + '\'' +
                ", cc='" + cc + '\'' +
                '}';
    }
}
