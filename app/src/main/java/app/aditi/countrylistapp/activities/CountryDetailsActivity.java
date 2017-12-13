package app.aditi.countrylistapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.aditi.countrylistapp.R;
import app.aditi.countrylistapp.dao.CountryData;
import app.aditi.countrylistapp.utils.Util;

/**
 * Created by deepi_000 on 27-11-2017.
 */

public class CountryDetailsActivity extends Activity implements View.OnClickListener {

    private ImageView imvCountryFlag;
    private TextView txtCountryName;
    private TextView txtCapital;
    private TextView txtRegion;
    private TextView txtSubRegion;
    private TextView txtPopulation;
    private TextView txtArea;
    private LinearLayout llBoarders;
    private CountryData countryData;
    private LinearLayout llCurrencies;
    private LinearLayout llLanguages;
    private LinearLayout llTimeZones;
    private ImageView imgBack;
    private LinearLayout llCapital;
    private LinearLayout llRegion;
    private LinearLayout llSubRegion;
    private LinearLayout llPopulation;
    private LinearLayout llArea;
    private LinearLayout llTime;
    private LinearLayout llBoard;
    private LinearLayout llCurrency;
    private LinearLayout llLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_details);

        if (getIntent() != null && getIntent().hasExtra(CountryData.COUNTRY_DATA)) {
            countryData = (CountryData) getIntent().getSerializableExtra(CountryData.COUNTRY_DATA);
        }
        init();
        setUpUI();

    }


    private void init() {
        imgBack = findViewById(R.id.imgBack);
        imvCountryFlag = findViewById(R.id.imvCountryFlag);
        txtCountryName = findViewById(R.id.txtCountryName);
        txtCapital = findViewById(R.id.txtCapital);
        txtRegion = findViewById(R.id.txtRegion);
        txtSubRegion = findViewById(R.id.txtSubRegion);
        txtPopulation = findViewById(R.id.txtPopulation);
        txtArea = findViewById(R.id.txtArea);
        llBoarders = findViewById(R.id.llBoarders);
        llCurrencies = findViewById(R.id.llCurrencies);
        llLanguages = findViewById(R.id.llLanguages);
        llTimeZones = findViewById(R.id.llTimeZones);

        llCapital = findViewById(R.id.llCapital);
        llRegion = findViewById(R.id.llRegion);
        llSubRegion = findViewById(R.id.llSubRegion);
        llPopulation = findViewById(R.id.llPopulation);
        llArea = findViewById(R.id.llArea);
        llTime = findViewById(R.id.llTime);
        llBoard = findViewById(R.id.llBoard);
        llCurrency = findViewById(R.id.llCurrency);
        llLang = findViewById(R.id.llLang);


        imgBack.setOnClickListener(this);
    }

    private void setUpUI() {
        Util.loadImage(CountryDetailsActivity.this, imvCountryFlag, countryData.getFlag(), getResources().getDimensionPixelOffset(R.dimen.dimen_200dp));
        txtCountryName.setText(countryData.getName());

        if (!TextUtils.isEmpty(countryData.getCapital())) {
            llCapital.setVisibility(View.VISIBLE);
            txtCapital.setText(countryData.getCapital());
        } else {
            llCapital.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(countryData.getRegion())) {
            llRegion.setVisibility(View.VISIBLE);
            txtRegion.setText(countryData.getRegion());
        } else {
            llRegion.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(countryData.getSubregion())) {
            llSubRegion.setVisibility(View.VISIBLE);
            txtSubRegion.setText(countryData.getSubregion());
        } else {
            llSubRegion.setVisibility(View.GONE);
        }

        if (countryData.getPopulation() != null && countryData.getPopulation() > 0) {
            llPopulation.setVisibility(View.VISIBLE);
            txtPopulation.setText(countryData.getPopulation() + "");
        } else {
            llPopulation.setVisibility(View.GONE);
        }

        if (countryData.getArea() != null && countryData.getArea() > 0) {
            llArea.setVisibility(View.VISIBLE);
            txtArea.setText(countryData.getArea() + "");
        } else {
            llArea.setVisibility(View.GONE);
        }

        if (countryData.getBorders() != null && countryData.getBorders().length > 0) {
            llBoard.setVisibility(View.VISIBLE);
            setLlBoarders();
        } else {
            llBoard.setVisibility(View.GONE);
        }

        if (!countryData.getCurrencies().isEmpty()) {
            llCurrency.setVisibility(View.VISIBLE);
            setLlCurrencies();
        } else {
            llCurrency.setVisibility(View.GONE);
        }

        if (!countryData.getLanguages().isEmpty()) {
            llLang.setVisibility(View.VISIBLE);
            setLlLanguages();
        } else {
            llLang.setVisibility(View.VISIBLE);
        }

        if (!countryData.getLanguages().isEmpty()) {
            llTime.setVisibility(View.VISIBLE);
            setLlTimeZones();
        } else {
            llTime.setVisibility(View.GONE);
        }
    }

    private void setLlBoarders() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view;
        for (int i = 0; i < countryData.getBorders().length; i++) {
            view = layoutInflater.inflate(R.layout.text_layout, llBoarders, false);
            TextView txtCustom = view.findViewById(R.id.txtCustom);
            txtCustom.setText(countryData.getBorders()[i]);
            llBoarders.addView(txtCustom);
        }
    }

    private void setLlCurrencies() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view;
        for (int i = 0; i < countryData.getCurrencies().size(); i++) {
            view = layoutInflater.inflate(R.layout.text_layout, llCurrencies, false);
            TextView txtCustom = view.findViewById(R.id.txtCustom);
            txtCustom.setText(countryData.getCurrencies().get(i).getCode());
            llCurrencies.addView(txtCustom);
        }
    }

    private void setLlLanguages() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view;
        for (int i = 0; i < countryData.getLanguages().size(); i++) {
            view = layoutInflater.inflate(R.layout.text_layout, llLanguages, false);
            TextView txtCustom = view.findViewById(R.id.txtCustom);
            txtCustom.setText(countryData.getLanguages().get(i).getName());
            llLanguages.addView(txtCustom);
        }
    }

    private void setLlTimeZones() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view;
        for (int i = 0; i < countryData.getTimezones().length; i++) {
            view = layoutInflater.inflate(R.layout.text_layout, llTimeZones, false);
            TextView txtCustom = view.findViewById(R.id.txtCustom);
            txtCustom.setText(countryData.getTimezones()[i]);
            llTimeZones.addView(txtCustom);
        }
    }

    @Override
    public void onClick(View view) {
        int vId = view.getId();
        try {
            switch (vId) {
                case R.id.imgBack:
                    onBackPressed();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
