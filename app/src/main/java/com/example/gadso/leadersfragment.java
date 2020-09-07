package com.example.gadso;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class leadersfragment extends Fragment {
    ArrayList<leaders> list;
    private static String  Json_url = "https://gadsapi.herokuapp.com/";
    public static leadersfragment getInstance(){
        leadersfragment leadersfragment= new leadersfragment();
        return leadersfragment;
    }
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_leaderboard,container,false);
     final   RecyclerView recyclerView = view.findViewById(R.id.recycler);


   /*     Volley volley = null;
        RequestQueue queue = volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Json_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                list = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        leaders leaders = new leaders();
                        leaders.setBadgeurl(jsonObject.getString("badgeUrl"));
                        leaders.setCountry(jsonObject.getString("country").toString());
                        leaders.setHours(jsonObject.getString("hours").toString());
                        leaders.setName(jsonObject.getString("name").toString());

                        list.add(leaders);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
                recyclerView.getLayoutManager().setMeasurementCacheEnabled(false);
                leaderadapter leaderadapter = new leaderadapter(getActivity(),list);
                Log.i("our volley error", String.valueOf(list));
                recyclerView.setAdapter(leaderadapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("our volley error","having issuess");
            }
        });
         queue.add(jsonArrayRequest);
*/
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttp = new OkHttpClient.Builder().addInterceptor(logger)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                                 .baseUrl(Json_url)
                                  .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp).build();
        leaderservices leaderservices = retrofit.create(com.example.gadso.leaderservices.class);

        Call<List<leaders>> leaderRequest = leaderservices.getLeaders();
    leaderRequest.enqueue(new Callback<List<leaders>>() {
        @Override
        public void onResponse(Call<List<leaders>> call, retrofit2.Response<List<leaders>> response) {
          /*  recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
            recyclerView.getLayoutManager().setMeasurementCacheEnabled(false);
            leaderadapter leaderadapter = new leaderadapter(getActivity(), (ArrayList<leaders>) response.body());

           */
            Log.i("our volley error", String.valueOf((ArrayList<leaders>) response.body()));
            ArrayList<leaders>  list = (ArrayList<leaders>) response.body();

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
            recyclerView.getLayoutManager().setMeasurementCacheEnabled(false);
            leaderadapter leaderadapter = new leaderadapter(getActivity(),list);
            Log.i("our volley error", String.valueOf(list));
            recyclerView.setAdapter(leaderadapter);
            /*
            recyclerView.setAdapter(leaderadapter);

             */

        }

        @Override
        public void onFailure(Call<List<leaders>> call, Throwable t) {

        }
    });
        return view;
    }

    private void getJsonLeadersData() {
        Volley volley = null;
        RequestQueue queue = volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Json_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        leaders leaders = new leaders();
                        leaders.setBadgeurl(jsonObject.getString("badgeUrl").toString());
                        leaders.setCountry(jsonObject.getString("country").toString());
                        leaders.setHours(jsonObject.getString("hours").toString());
                        leaders.setName(jsonObject.getString("name").toString());
                        list.add(leaders);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("our volley error","having issuess");
            }
        });

    }
}
