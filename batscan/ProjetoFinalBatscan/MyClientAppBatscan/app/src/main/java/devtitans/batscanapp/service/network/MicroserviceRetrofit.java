package devtitans.batscanapp.service.network;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MicroserviceRetrofit {
    private static MacVendorApi INSTANCE;

    public static MacVendorApi getInstance() {
        if (INSTANCE == null) {
            String BASE_URL = "https://api-rest-vendors.herokuapp.com";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();
            INSTANCE = retrofit.create(MacVendorApi.class);
        }
        return INSTANCE;
    }
}
