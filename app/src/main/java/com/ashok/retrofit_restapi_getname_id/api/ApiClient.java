package com.ashok.retrofit_restapi_getname_id.api;
import android.util.Log;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
/**
 * Created by Ashok on 5/21/2016.
 */
public class ApiClient {
    private static Api_Interface m_Api_Interface;
    private static final String BaseURL_ofJsonDataToFetch = "http://codeentries.com";
    private static final String TAG = "ak";

    //Initialization-block; It is called only-once when class loaded by VM; only once
    static {
        Log.i(TAG, "ApiClient:This 'initialization-Block' called only once when Class loaded");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseURL_ofJsonDataToFetch)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        m_Api_Interface = retrofit.create(Api_Interface.class);
    }
    //--------------------------
    public static Api_Interface get() {
        Log.i(TAG, "ApiClient:get();");
        return m_Api_Interface;
    }
    //-------------------------------------
}
