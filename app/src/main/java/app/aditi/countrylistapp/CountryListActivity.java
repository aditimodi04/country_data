package app.aditi.countrylistapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

public class CountryListActivity extends Activity implements Observer {

    private SwipeRefreshLayout svRefreshCountries;
    private RecyclerView rvCountries;
    private TextView txtEmptyView;
    private CountryModel countryModel = new CountryModel();
    private CountryRowAdapter countryRowAdapter;
    private ArrayList<CountryData> countryData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_list);
        countryModel.addObserver(this);
        svRefreshCountries = findViewById(R.id.svRefreshCountries);
        rvCountries = findViewById(R.id.rvCountries);
        txtEmptyView = findViewById(R.id.txtEmptyView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvCountries.setLayoutManager(layoutManager);
        countryRowAdapter = new CountryRowAdapter(this, countryData);
        rvCountries.setAdapter(countryRowAdapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvCountries.addItemDecoration(itemDecoration);
        if (Util.isDeviceOnline(this)) {
            Util.showProDialog(this);
            countryModel.getAllCountryList(this);
        } else {
            txtEmptyView.setText(getResources().getString(R.string.InternetNotAvailable));
            txtEmptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void update(Observable observable, Object result) {
        Util.dismissProDialog();
        if (result instanceof ArrayList) {
            countryData.clear();
            countryData.addAll((Collection<? extends CountryData>) result);
            countryRowAdapter.notifyDataSetChanged();
            if (countryData.isEmpty()) {
                txtEmptyView.setText(getResources().getString(R.string.noCountryDataFound));
                txtEmptyView.setVisibility(View.VISIBLE);
            } else {
                txtEmptyView.setVisibility(View.GONE);
            }
        } else {
            txtEmptyView.setText(((String) result));
            txtEmptyView.setVisibility(View.VISIBLE);
        }
    }
}
