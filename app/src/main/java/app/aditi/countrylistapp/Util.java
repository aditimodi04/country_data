package app.aditi.countrylistapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.larvalabs.svgandroid.SVG;

/**
 * Created by Aditi on 25-11-2017.
 */

public class Util {

    private static ProgressDialog progressDialog;

    public static Dialog showProDialog(Context context) {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return progressDialog;
    }

    public static Dialog dismissProDialog() {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            progressDialog = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return progressDialog;
    }

    public static boolean isDeviceOnline(Context context) {
        boolean isDeviceOnLine = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            isDeviceOnLine = netInfo != null && netInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeviceOnLine;
    }


    public static void loadImage(Context context, ImageView imgView, String url, int defaultResource) {
        if (url != null && !url.isEmpty()) {
            try {
                RequestBuilder<SVG> requestBuilder = Glide.with(context)
                        .as(SVG.class)
                        .load(url)
                        .listener(new RequestListener<SVG>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<SVG> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(SVG resource, Object model, Target<SVG> target, DataSource dataSource, boolean isFirstResource) {
                                return false;
                            }
                        });
                requestBuilder.into(imgView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
