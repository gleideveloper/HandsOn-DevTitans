package devtitans.batscanapp.service.network;

import devtitans.batscanapp.models.MacVendorItemAdapterModel;
import devtitans.batscanapp.service.network.response.MacVendorApiService;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface MacVendorApi {
    @GET("/mac-vendor/")
    Call<List<MacVendorApiService>> getAllMacVendors();

    @POST("/mac-vendor/save")
    Call<MacVendorApiService> saveMac(@Body MacVendorApiService macVendorApiService);

}
