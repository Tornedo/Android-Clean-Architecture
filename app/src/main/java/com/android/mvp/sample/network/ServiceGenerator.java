package news.agoda.com.sample.network;


import java.util.concurrent.TimeUnit;

import news.agoda.com.sample.BuildConfig;
import news.agoda.com.sample.utils.GsonUtil;
import news.agoda.com.sample.utils.Logger;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Main service generator, it will create a service and keep in lookup table
 */
public class ServiceGenerator {

    private static String TAG = ServiceGenerator.class.getName();

    private static ServiceGenerator instance = null;

    public ServiceGenerator getInstance() {
        instance = (instance == null) ? new ServiceGenerator() : instance;
        return instance;
    }


    public static final String API_BASE_URL = BuildConfig.NEWS_SERVER_URL;


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    /**
     * Retrofit builder to build service instance
     */
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(GsonUtil.getGson()));


    /**
     * Service generator with basic authentication
     *
     * @param serviceClass
     * @param <S>
     * @return
     */

    private static <S> S createService(Class<S> serviceClass) {

        setHttpClient();
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    /**
     * Check service is in table, if exist then return existing service otherwise generate
     * the service and store in table
     *
     * @param className
     * @param <S>
     * @return Service
     */

    public static <S> S getService(Class<S> className) {

        // create a new instance and keep it in table
        Logger.e("create class", "reached");
        return createService(className);
    }

    /**
     * Generate service, it will not check the lookup table, Always return a new service instance.
     *
     * @param className
     * @param includeAuth if yes, than include basic authorization otherwise create empty header
     * @param <S>
     * @return
     */

    public static <S> S getProxyService(Class<S> className, boolean includeAuth) {
        return getService(className);
    }

    public static void setHttpClient() {
        try {
            httpClient.connectTimeout(200, TimeUnit.SECONDS);
            httpClient.readTimeout(200, TimeUnit.SECONDS);
            httpClient.writeTimeout(200, TimeUnit.SECONDS);
            httpClient.interceptors().clear();
            httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC));

        } catch (Exception e) {
            Logger.e("exception ", e.toString());
        }
    }

}
