package app.aditi.countrylistapp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PictureDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bumptech.glide.request.target.Target;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;

import app.aditi.countrylistapp.R;
import app.aditi.countrylistapp.svgImage.SvgDecoder;
import app.aditi.countrylistapp.svgImage.SvgDrawableTranscoder;
import app.aditi.countrylistapp.svgImage.SvgSoftwareLayerSetter;

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

    public static int getDeviceWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static void loadImage(Context context, ImageView imgView, String url, int height) {
        if (url != null && !url.isEmpty()) {
            try {
//                new HttpImageRequestTask(url, imgView).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder = Glide.with(context)
                        .using(Glide.buildStreamModelLoader(Uri.class, context), InputStream.class)
                        .from(Uri.class)
                        .as(SVG.class)
                        .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                        .sourceEncoder(new StreamEncoder())
                        .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                        .decoder(new SvgDecoder())
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .animate(android.R.anim.fade_in)
                        .override(200, 200)
                        .listener(new SvgSoftwareLayerSetter<Uri>());
                if (height > 0) {
                    requestBuilder.override(getDeviceWidth(context), Target.SIZE_ORIGINAL);
                }
                Uri uri = Uri.parse(url);
                requestBuilder
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .load(uri)
                        .into(imgView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

  /*  private static class HttpImageRequestTask extends AsyncTask<Void, Void, Drawable> {
        String imageUrl;
        ImageView imageView;

        HttpImageRequestTask(String imageUrl, ImageView imageView) {
            this.imageUrl = imageUrl;
            this.imageView = imageView;
        }

        @Override
        protected Drawable doInBackground(Void... params) {
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                SVG svg = SVGParser.getSVGFromInputStream(inputStream);
                Drawable drawable = svg.createPictureDrawable();
                return drawable;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Drawable drawable) {
            // Update the view
            if (drawable != null) {

                // Try using your library and adding this layer type before switching your SVG parsing
                imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                imageView.setImageDrawable(drawable);
            }
        }
    }*/
}
