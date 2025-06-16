package com.example.bigcart;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SupabaseClient {
    public interface SBC_Callback{
        public void onFailure(IOException e);
        public void onResponse(String responseBody);
    }
    OkHttpClient client=new OkHttpClient();
    public static String DOMAIN_NAME = "https://simttaxqfqsbjkqhwtre.supabase.co/";
    public static String REST_PATH = "rest/v1/";
    public static String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E";
    public static String BEARER_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E";
    public void fetchAllOrders(final SBC_Callback callback){
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH + "orders?select=*,order_items(*),status(*)")
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", BEARER_TOKEN)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            callback.onFailure(e);
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            if (response.isSuccessful()){
                String responseBody = response.body().string();
                callback.onResponse(responseBody);
            }
            else{
                callback.onFailure(new IOException("Ошибка сервера: " + response));
            }
            }
        });
    }
}
