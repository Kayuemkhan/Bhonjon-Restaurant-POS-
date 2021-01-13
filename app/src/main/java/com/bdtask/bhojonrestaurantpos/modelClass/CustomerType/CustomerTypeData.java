package com.bdtask.bhojonrestaurantpos.modelClass.CustomerType;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerTypeData {
    @SerializedName("TypeID")
    @Expose
    private String typeID;
    @SerializedName("TypeName")
    @Expose
    private String typeName;

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
