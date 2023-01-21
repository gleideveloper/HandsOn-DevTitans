package com.gleidev.myclientbatscan.bufferedreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit2.Response;

public class ResponseBufferedReader<T> {
    private Response<T> response;

    public ResponseBufferedReader(Response<T> response) {
        this.response = response;
    }

    public StringBuilder getResponse(){
        assert response.errorBody() != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }
}
