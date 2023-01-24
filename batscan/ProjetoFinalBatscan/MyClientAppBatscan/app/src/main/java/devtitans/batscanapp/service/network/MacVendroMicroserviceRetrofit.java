package devtitans.batscanapp.service.network;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MacVendroMicroserviceRetrofit {
    private static MacVendorApi INSTANCE;

    public static MacVendorApi getInstance() {
        if (INSTANCE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.15.57:8080")
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();
            INSTANCE = retrofit.create(MacVendorApi.class);
        }
        return INSTANCE;
    }
}
