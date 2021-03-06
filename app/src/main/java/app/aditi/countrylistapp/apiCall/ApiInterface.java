package app.aditi.countrylistapp.apiCall;

import java.util.ArrayList;

import app.aditi.countrylistapp.dao.CountryData;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Aditi on 25-11-2017.
 */

public interface ApiInterface {
    @GET("rest/v2/all")
    Call<ArrayList<CountryData>> countryList();
}
