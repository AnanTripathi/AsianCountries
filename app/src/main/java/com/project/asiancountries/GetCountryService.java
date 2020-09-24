package com.project.asiancountries;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetCountryService {
    @GET("asia")
    Call<List<Country>> getAllCountries();
}
