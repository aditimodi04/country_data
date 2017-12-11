package app.aditi.countrylistapp.apiCall;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import app.aditi.countrylistapp.utils.Util;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aditi on 25-11-2017.
 */

public class ApiClient {
    private static final int HTTP_READ_TIMEOUT = 600000;
    private final static int HTTP_REQUEST_TIMEOUT = 60000;

    public static Retrofit getClient(final Context context, String baseUrl) throws Exception {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
              /*  Request original = chain.request();
                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder();
                Request request = requestBuilder.build();
                return chain.proceed(request);*/

                Response originalResponse = chain.proceed(chain.request());
                if (Util.isDeviceOnline(context)) {
                    int maxAge = 60; // read from cache for 1 minute
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                } else {
                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
            }
        });
        //setup cache
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

//add cache to the client
        httpClient.cache(cache);
        OkHttpClient client = httpClient.readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.MILLISECONDS).build();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
