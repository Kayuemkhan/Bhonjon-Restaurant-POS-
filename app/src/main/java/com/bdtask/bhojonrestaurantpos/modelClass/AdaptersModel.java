package com.bdtask.bhojonrestaurantpos.modelClass;

public class AdaptersModel {
    private int position;
    private String AdaptersData;

    public AdaptersModel(int position, String adaptersData) {
        this.position = position;
        this.AdaptersData = adaptersData;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getAdaptersData() {
        return AdaptersData;
    }

    public void setAdaptersData(String adaptersData) {
        AdaptersData = adaptersData;
    }
}
