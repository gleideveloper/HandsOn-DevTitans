package devtitans.batscanapp.service.network;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceRetrofit {
    private final Retrofit retrofit;
    private final String LOCAL_URL = "http://192.168.15.57:8080";
    private final String BASE_URL = "https://api.maclookup.app";
    public ServiceRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
