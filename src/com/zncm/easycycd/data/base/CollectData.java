package com.zncm.easycycd.data.base;

import com.j256.ormlite.field.DatabaseField;
import com.zncm.easycycd.data.BaseData;

import java.io.Serializable;

public class CollectData extends BaseData implements Serializable {

    private static final long serialVersionUID = 8838725426885988959L;
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private int cy_id;//成语ID
    @DatabaseField
    private String cy;//成语


    public CollectData() {
    }

    public CollectData(int cy_id, String cy) {

        this.cy_id = cy_id;
        this.cy = cy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCy_id() {
        return cy_id;
    }

    public void setCy_id(int cy_id) {
        this.cy_id = cy_id;
    }

    public String getCy() {
        return cy;
    }

    public void setCy(String cy) {
        this.cy = cy;
    }
}
