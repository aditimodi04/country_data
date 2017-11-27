package app.aditi.countrylistapp.apiCall;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aditi on 25-11-2017.
 */

public class ApiClient {
    private static final int HTTP_READ_TIMEOUT = 600000;
    private final static int HTTP_REQUEST_TIMEOUT = 60000;

    public static Retrofit getClient(String baseUrl) throws Exception {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder();
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient.readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.MILLISECONDS).build();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
