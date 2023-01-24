package devtitans.batscanapp.service.network;

import devtitans.batscanapp.service.network.response.MacVendorMicroserviceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MacVendorApi {
    @GET("/mac-vendor/")
    Call<List<MacVendorMicroserviceResponse>> getAllMacVendors();

    @POST("/mac-vendor/save")
    Call<MacVendorMicroserviceResponse> saveMac(@Body MacVendorMicroserviceResponse macVendorMicroserviceResponse);

}
