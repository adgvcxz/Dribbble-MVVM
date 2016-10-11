package com.adgvcxz.adgble.api;

import com.adgvcxz.adgble.BuildConfig;
import com.adgvcxz.adgble.content.Shot;
import com.adgvcxz.adgble.util.RxUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/10.
 */

public class RetrofitSingleton {

    private static ApiService sApiServce = null;
    private static Retrofit sRetrofit = null;
    private static OkHttpClient sOkHttpClient = null;

    private RetrofitSingleton() {
        init();
    }

    private void init() {
        initOkHttp();
        initRetrofit();
        sApiServce = sRetrofit.create(ApiService.class);
    }

    public static RetrofitSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RetrofitSingleton INSTANCE = new RetrofitSingleton();
    }

    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(chain -> {
            Request request = chain.request();
            HttpUrl originUrl = request.url();
            HttpUrl url = originUrl.newBuilder().addQueryParameter("access_token", ApiService.ClientAccessToken).build();
            return chain.proceed(request.newBuilder().url(url).build());
        });
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        builder.connectTimeout(3, TimeUnit.SECONDS);
        builder.readTimeout(3, TimeUnit.SECONDS);
        builder.writeTimeout(3, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(false);

//okHttp 支持vpn
//        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
//                .tlsVersions(TlsVersion.TLS_1_2)
//                .cipherSuites(
//                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
//                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
//                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
//                .build();
//        builder.connectionSpecs(Collections.singletonList(spec));


//        如果使用到HTTPS，我们需要创建SSLSocketFactory，并设置到client
//        SSLSocketFactory sslSocketFactory = null;
//
//        try {
//            // 这里直接创建一个不做证书串验证的TrustManager
//            final TrustManager[] trustAllCerts = new TrustManager[]{
//                    new X509TrustManager() {
//                        @Override
//                        public void checkClientTrusted(X509Certificate[] chain, String authType)
//                                throws CertificateException {
//                        }
//
//                        @Override
//                        public void checkServerTrusted(X509Certificate[] chain, String authType)
//                                throws CertificateException {
//                        }
//
//                        @Override
//                        public X509Certificate[] getAcceptedIssuers() {
//                            return new X509Certificate[]{};
//                        }
//                    }
//            };
//
//            // Install the all-trusting trust manager
//            final SSLContext sslContext = SSLContext.getInstance("SSL");
//            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
//            // Create an ssl socket factory with our all-trusting manager
//            sslSocketFactory = sslContext.getSocketFactory();
//        } catch (Exception e) {
//            Logger.e(TAG, e.getMessage());
//        }
//        builder.sslSocketFactory()


        sOkHttpClient = builder.build();
    }

    private void initRetrofit() {
        sRetrofit = new Retrofit.Builder().baseUrl("https://api.dribbble.com/v1/")
                .client(sOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public Observable<List<Shot>> getShots(int page, String sort) {
        return sApiServce.getShots(page, sort).compose(RxUtil.rxScheduleHelper());
    }
}
