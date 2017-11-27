package app.aditi.countrylistapp.models;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Observable;

import app.aditi.countrylistapp.R;
import app.aditi.countrylistapp.apiCall.ApiClient;
import app.aditi.countrylistapp.apiCall.ApiInterface;
import app.aditi.countrylistapp.dao.CountryData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aditi on 25-11-2017.
 */

public class CountryModel extends Observable {

    private String TAG = CountryModel.class.getSimpleName();
    private String COUNTRY_URL = "https://restcountries.eu/";

    public void getAllCountryList(final Context context) {
        try {
            ApiInterface apiCall = ApiClient.getClient(COUNTRY_URL).create(ApiInterface.class);
            Call<ArrayList<CountryData>> countryCall = apiCall.countryList();
            countryCall.enqueue(new Callback<ArrayList<CountryData>>() {
                @Override
                public void onResponse(Call<ArrayList<CountryData>> call, Response<ArrayList<CountryData>> response) {
                    Log.d(TAG, call.request().url().toString());
                    CountryModel.this.setChanged();
                    notifyObservers(response.body());
                }

                @Override
                public void onFailure(Call<ArrayList<CountryData>> call, Throwable t) {
                    CountryModel.this.setChanged();
                    notifyObservers(context.getResources().getString(R.string.somethingWentWrong));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
