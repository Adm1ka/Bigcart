package com.example.bigcart;

import androidx.annotation.NonNull;

import com.example.bigcart.Models.LoginRequest;
import com.google.gson.Gson;

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
    public static String DOMAIN_NAME = "https://simttaxqfqsbjkqhwtre.supabase.co/";
    public static String REST_PATH = "rest/v1/";
    public static String AUTH_PATH = "auth/v1/";
    public static String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E";
    public static String BEARER_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E";
    OkHttpClient client=new OkHttpClient();
    public void signout(SBC_Callback callBack){
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(DOMAIN_NAME+AUTH_PATH+ "logout")
                .method("POST", body)
                .addHeader("apikey", API_KEY)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer USER_TOKEN")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String responseBody = response.body().string();
                    DataBinding.saveBearerToken("");
                    DataBinding.saveUuidUser("");
                    callBack.onResponse(responseBody);
                } else {
                    callBack.onFailure(new IOException("Server error:" + response));
                }
            }
        });
    }
    public void registerUser(LoginRequest loginRequest, final SBC_Callback callBack){
        MediaType mediaType = MediaType.parse("application/json");
        Gson gson = new Gson();
        String json = gson.toJson(loginRequest);
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + AUTH_PATH + "signup")
                .method("POST", body)
                .addHeader("apikey", API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String responseBody = response.body().string();
                    callBack.onResponse(responseBody);
                } else {
                    callBack.onFailure(new IOException("Server error:" + response));
                }
            }
        });
    }
    public void update(String userId, String fullName, SBC_Callback callBack){
        MediaType mediaType = MediaType.parse("application/json");
        Gson gson = new Gson();
        String json = String.format("{\"fullname\": \"%s\"}", fullName);
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH + "profiles?id=eq." + DataBinding.getUuidUser())
                .method("PATCH", body)
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", DataBinding.getBearerToken())
                .addHeader("Content-Type", "application/json")
                .addHeader("Prefer", "return=minimal")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String responseBody = response.body().string();
                    callBack.onResponse(responseBody);
                } else {
                    callBack.onFailure(new IOException("Server error:" + response));
                }
            }
        });
    }
    public void login(LoginRequest loginRequest, final SBC_Callback callback){
        MediaType mediaType = MediaType.parse("application/json");
        Gson gson = new Gson();
        String json= gson.toJson(loginRequest);
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(DOMAIN_NAME+ AUTH_PATH+ "token?grant_type=password")
                .method("POST", body)
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E")
                .addHeader("Content-Type", "application/json")
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
    public void FetchCurrentUser(final SBC_Callback callBack){
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH + "profiles?select=*id=eq." + DataBinding.getUuidUser())
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", DataBinding.getBearerToken())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callBack.onFailure(e);
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String responseBody = response.body().string();
                    callBack.onResponse(responseBody);
                } else {
                    callBack.onFailure(new IOException("Server error:" + response));
                }
            }
        });
    }
    public void fetchBabycare(final SBC_Callback callback){
    Request request = new Request.Builder()
            .url(DOMAIN_NAME + REST_PATH +"products?select=*&category_id=eq.7")
            .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E")
            .addHeader("Range", "0-9")
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
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
    public void fetchHoushold(final SBC_Callback callback){
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH +"products?select=*&category_id=eq.6")
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E")
                .addHeader("Range", "0-9")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
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
    public void fetchOil(final SBC_Callback callback){
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH +"products?select=*&category_id=eq.5")
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E")
                .addHeader("Range", "0-9")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
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
public void fetchGrocery(final SBC_Callback callback){
    Request request = new Request.Builder()
            .url(DOMAIN_NAME + REST_PATH +"products?select=*&category_id=eq.4")
            .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E")
            .addHeader("Range", "0-9")
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
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
    public  void fetchDrinks(final SBC_Callback callback){
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH +"products?select=*&category_id=eq.3")
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E")
                .addHeader("Range", "0-9")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
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
    public void fetchFruits(final SBC_Callback callback){

        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH +"products?select=*&category_id=eq.2")
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E")
                .addHeader("Range", "0-9")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
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
    public void fetchVegetables(final SBC_Callback callback){
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH +"products?select=*&category_id=eq.1")
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E")
                .addHeader("Range", "0-9")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
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
    public void fetchAllProducts(final SBC_Callback callback){
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH +"products?select=*")
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E")
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
