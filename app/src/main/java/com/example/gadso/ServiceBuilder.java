package com.example.gadso;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ServiceBuilder {
    private static final String URL = "https://gadsapi.herokuapp.com/api/skilliq";
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();
    public static <S> S buildService(Class<S> serviceType){
        return retrofit.create(serviceType);

    }

}
