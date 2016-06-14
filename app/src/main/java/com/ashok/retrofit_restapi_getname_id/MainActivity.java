/*Ref: http://codeentries.com/libraries/how-to-use-retrofit-2-in-android-the-example.html
This example fetches JSON-data (ID-string and Name-string) from a website
       http://codeEntries.com/api/users.jason
  That's it.
  To fetch above JSON data, this app uses Retrofit-2 Library and thus this App
  do NOT have to use (i) Http calls (ii) Async Task
  AK- I made 3 changes to this prog:
       (1) Changed 'dependency' from Retrofit-Beta to Retrofit 2.0.2
       (ii) becasue of above change parameters also changed for onResponse() and
            onFailure() methods.
------------------------------------------------------------------------
The size of JSON data is only 2 (2 names and 2 IDs)
This is the actual-website that contain the JSON data to fetch.
       http://codeentries.com/api/users.json
This is the actual-JSON data on at above website (that need to be fetched.)
             [ {"id":"1","name":"Andrew"},
               {"id":"2","name":"Nick"}
             ]
 */
package com.ashok.retrofit_restapi_getname_id;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//import retrofit2.Retrofit;

import com.ashok.retrofit_restapi_getname_id.api.ApiClient;
import com.ashok.retrofit_restapi_getname_id.objects.DataToFetch_Pojo;
//--------------------------------------------------------
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ak";
    TextView mTxt_JsonDataLine_1, mTxt_JsonDataLine_2,
                mTxt_JsonDataHeaderLine;
    //---------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mTxt_JsonDataHeaderLine = (TextView) findViewById(R.id.txt_dataReceivedHeader);
        mTxt_JsonDataLine_1 = (TextView) findViewById(R.id.txt_JsonDataLine_1);
        mTxt_JsonDataLine_2 = (TextView) findViewById(R.id.txt_JsonDataLine_2);
    }
    //------------------------------------------
    public void getUsersJsonDataFromWebsite(View v)
    {
        Log.i(TAG,"MainActivity():getUsersJsonDataFromWebsite();");
        Call<List<DataToFetch_Pojo>> call = ApiClient.get().getUsersJsonDataFromWebsite();
        //********************************************
        //anonymous class
        call.enqueue(new Callback<List<DataToFetch_Pojo>>() {
            //---------------------------------------------
            @Override
            public void onResponse(Call<List<DataToFetch_Pojo>> callNotUsed,
                    Response<List<DataToFetch_Pojo>> responseData) {
                List<DataToFetch_Pojo> usersInList = responseData.body();
                int i = 1;
                mTxt_JsonDataHeaderLine.setText("JSON Data Received");
                for (DataToFetch_Pojo user : usersInList) {
                    if (i == 1)
                        mTxt_JsonDataLine_1.setText("user-id: " + user.getId() +
                                                   "   name: " + user.getName() );
                    else if (i == 2)
                        mTxt_JsonDataLine_2.setText("user-id: " + user.getId() +
                                                   "   name: " + user.getName() );
                    i++;
                }
            }
            //-----------------------------------------
            @Override
            public void onFailure(Call<List<DataToFetch_Pojo>> callNOTUSed, Throwable t) {
                Log.d(TAG, "Error: onFailure():" + t.getMessage());
            }
            //-------------------------------------------
        });
        //*************************
    }
}
