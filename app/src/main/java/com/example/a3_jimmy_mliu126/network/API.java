package com.example.a3_jimmy_mliu126.network;

import com.example.a3_jimmy_mliu126.models.EquipmentList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    // base url for api
    public final String BASE_URL = "https://botw-compendium.herokuapp.com/api/v2/";

    // endpoints for api
    // https://botw-compendium.herokuapp.com/api/v2/category/equipment

    @GET("category/equipment")
    public Call<EquipmentList> getEquipmentList();
}
