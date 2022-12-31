package com.example.a3_jimmy_mliu126.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EquipmentList {
    @SerializedName("data")
    private List<Equipment> equipments;

    public EquipmentList(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }
}
