package com.bdtask.bhojonrestaurantpos.modelClass.tablelist;

import com.google.gson.annotations.SerializedName;

public class DataItem {
    @SerializedName("TableId")
    private String tableId;

    @SerializedName("TableName")
    private String tableName;

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public String toString() {
        return
                "DataItem{" +
                        "tableId = '" + tableId + '\'' +
                        ",tableName = '" + tableName + '\'' +
                        "}";
    }
}
