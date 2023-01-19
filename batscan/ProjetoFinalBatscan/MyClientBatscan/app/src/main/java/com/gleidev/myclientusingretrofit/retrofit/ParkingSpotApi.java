package com.gleidev.myclientusingretrofit.retrofit;

import com.gleidev.myclientusingretrofit.models.ParkingSpotModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ParkingSpotApi {
    @GET("/parking-spot/get-all")
    Call<List<ParkingSpotModel>> getAllParkingSpot();
    @POST("/parking-spot/save")
    Call<ParkingSpotModel> saveParkingSpot(@Body ParkingSpotModel parkingSpotModel);
}
