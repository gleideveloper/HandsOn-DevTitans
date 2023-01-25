package devtitans.batscanapp.service.network;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MacVendorMicroserviceRetrofit {
    private static MacVendorApi INSTANCE;
    private static String BASE_URL_DEVTITANS = "http://10.208.1.63:8080";
    private static String BASE_URL = "http://192.168.15.57:8080";

    public static MacVendorApi getInstance() {
        if (INSTANCE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();
            INSTANCE = retrofit.create(MacVendorApi.class);
        }
        return INSTANCE;
    }
}
