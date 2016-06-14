//INTERFACE file
package com.ashok.retrofit_restapi_getname_id.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import com.ashok.retrofit_restapi_getname_id.objects.DataToFetch_Pojo;
/**
 * Created by Ashok on 5/21/2016.
 */
public interface Api_Interface {
    @GET("/api/users.json")//Static URL is used.
    Call<List<DataToFetch_Pojo>> getUsersJsonDataFromWebsite();
}
