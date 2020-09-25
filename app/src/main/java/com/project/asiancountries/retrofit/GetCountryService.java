package com.project.asiancountries.retrofit;

import com.project.asiancountries.util.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetCountryService {
    @GET("asia")
    Call<List<Country>> getAllCountries();
}
