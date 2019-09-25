package com.example.mvvmrecyclerview.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mvvmrecyclerview.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

public class MainActivityRepository {

    private String url = "http://www.coxtunes.com/client_mobile_app_project/education/cbiu/api/administration.php";
    private Context context;


    private MutableLiveData<List<User>> mutableLiveData;
    private List<User> userList;

    public MainActivityRepository(Context context) {
        this.context = context;
    }

    public MutableLiveData<List<User>> getUserData()
    {
        if (mutableLiveData == null)
        {
            mutableLiveData = new MutableLiveData<>();
        }

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                userList = Arrays.asList(gson.fromJson(response, User[].class));
                mutableLiveData.setValue(userList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(context);
        rQueue.add(request);

        return mutableLiveData;

    }
}
