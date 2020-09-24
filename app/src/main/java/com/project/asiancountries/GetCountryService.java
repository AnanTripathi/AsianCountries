package com.project.asiancountries;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetCountryService {
    @GET("asia")
    Call<List<Country>> getAllCountries(
            @Query("page") int page,
            @Query("limit") int limit);
}
