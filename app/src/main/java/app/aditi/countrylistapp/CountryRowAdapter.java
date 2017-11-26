package app.aditi.countrylistapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CountryRowAdapter extends RecyclerView.Adapter<CountryRowAdapter.ViewHolder> {

    private List<CountryData> countryDataList;

    private Context context;
    private LayoutInflater layoutInflater;

    public CountryRowAdapter(Context context, ArrayList<CountryData> countryDataList) {
        this.context = context;
        this.countryDataList = countryDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.country_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CountryData data = countryDataList.get(position);
        Util.loadImage(context, holder.imvCountryIcon, data.getFlag(), R.mipmap.ic_launcher);
        holder.txtCountryName.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return countryDataList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout llCountryRow;
        private ImageView imvCountryIcon;
        private TextView txtCountryName;

        public ViewHolder(View view) {
            super(view);
            llCountryRow = (LinearLayout) view.findViewById(R.id.llCountryRow);
            imvCountryIcon = (ImageView) view.findViewById(R.id.imvCountryIcon);
            txtCountryName = (TextView) view.findViewById(R.id.txtCountryName);
        }
    }
}
