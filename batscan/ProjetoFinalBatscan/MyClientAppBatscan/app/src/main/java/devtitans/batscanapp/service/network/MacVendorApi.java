package devtitans.batscanapp.service.network;

import java.util.List;

import devtitans.batscanapp.service.network.response.MacVendorMicroservice;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MacVendorApi {
    @GET("/mac-vendor/")
    Call<List<MacVendorMicroservice>> getAllMacVendors();

    @POST("/mac-vendor/save")
    Call<MacVendorMicroservice> saveMac(@Body MacVendorMicroservice macVendorMicroservice);

    @DELETE("/mac-vendor/{id}")
    Call<ResponseBody> deleteMac(@Path("id") String macPrefix);
}
