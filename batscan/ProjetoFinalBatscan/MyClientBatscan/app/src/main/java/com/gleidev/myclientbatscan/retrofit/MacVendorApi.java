package com.gleidev.myclientbatscan.retrofit;

import com.gleidev.myclientbatscan.models.MacVendorModel;
import com.gleidev.myclientbatscan.models.MacDetails;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface MacVendorApi {
    @GET("/mac-company/")
    Call<List<MacVendorModel>> getAllMacs();
    @POST("/mac-company/save")
    Call<MacVendorModel> saveMac(@Body MacVendorModel macVendorModel);
    @GET("/v2/macs/{mac}")
    Call<MacDetails> getMacDetails(@Path("mac") String mac);
    @GET("/v1")
    Call<MacDetails> getVendorDetails(@QueryMap Map<String, String> params);
}
