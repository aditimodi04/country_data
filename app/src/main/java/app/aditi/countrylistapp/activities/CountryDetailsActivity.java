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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_details);

        if (getIntent().hasExtra("countryData")) {
            countryData = (CountryData) getIntent().getExtras().get(CountryData.COUNTRY_DATA);
        }
        init();
        setUpUI();
        setLlBoarders();
        setLlCurrencies();
        setLlLanguages();
        setLlTimeZones();
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
        imgBack.setOnClickListener(this);
    }

    private void setUpUI() {
        Util.loadImage(CountryDetailsActivity.this, imvCountryFlag, countryData.getFlag());
        txtCountryName.setText(countryData.getName());
        txtCapital.setText(countryData.getCapital());
        txtRegion.setText(countryData.getRegion());
        txtSubRegion.setText(countryData.getSubregion());
        txtPopulation.setText(countryData.getPopulation() + "");
        txtArea.setText(countryData.getArea() + "");
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
