package app.aditi.countrylistapp.activities;

import android.app.Activity;
import android.content.Intent;
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

import app.aditi.countrylistapp.R;
import app.aditi.countrylistapp.adapter.CountryRowAdapter;
import app.aditi.countrylistapp.dao.CountryData;
import app.aditi.countrylistapp.models.CountryModel;
import app.aditi.countrylistapp.utils.Util;

public class CountryListActivity extends Activity implements Observer, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

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
        Util.showProDialog(this);
        countryModel.getAllCountryList(this);
        svRefreshCountries.setOnRefreshListener(this);
        svRefreshCountries.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary, R.color.colorAccent);

    }

    @Override
    public void update(Observable observable, Object result) {
        Util.dismissProDialog();
        if (result instanceof ArrayList) {
            countryData.clear();
            countryData.addAll((Collection<? extends CountryData>) result);
            countryRowAdapter.notifyDataSetChanged();
            if (countryData.isEmpty()) {
                txtEmptyView.setText((Util.isDeviceOnline(this)) ? getResources().getString(R.string.noCountryDataFound) : getResources().getString(R.string.InternetNotAvailable));
                txtEmptyView.setVisibility(View.VISIBLE);
            } else {
                txtEmptyView.setVisibility(View.GONE);
            }
        } else {
            txtEmptyView.setText((Util.isDeviceOnline(this)) ? (String) result : getResources().getString(R.string.InternetNotAvailable));
            txtEmptyView.setVisibility(View.VISIBLE);
        }
        if (svRefreshCountries.isRefreshing()) {
            svRefreshCountries.setRefreshing(false);
        }

    }

    @Override
    public void onClick(View view) {
        int vId = view.getId();
        try {
            switch (vId) {
                case R.id.txtCountryName:
                    CountryData countryData = (CountryData) view.getTag();
                    if (countryData != null) {
                        Intent intent = new Intent(CountryListActivity.this, CountryDetailsActivity.class);
                        intent.putExtra(CountryData.COUNTRY_DATA, countryData);
                        startActivity(intent);
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRefresh() {
        if (Util.isDeviceOnline(this)) {
            txtEmptyView.setVisibility(View.GONE);
            countryModel.getAllCountryList(this);
        } else {
            countryData.clear();
            countryRowAdapter.notifyDataSetChanged();
            txtEmptyView.setText(getResources().getString(R.string.InternetNotAvailable));
            txtEmptyView.setVisibility(View.VISIBLE);
            if (svRefreshCountries.isRefreshing()) {
                svRefreshCountries.setRefreshing(false);
            }
        }
    }
}
