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
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class skillfragment extends Fragment {
    ArrayList<skill> list;
    private static String  Json_url = "https://gadsapi.herokuapp.com/";
    public static skillfragment getInstance(){
        skillfragment skillfragment= new skillfragment();
        return skillfragment;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skill,container,false);
        final   RecyclerView recyclerView = view.findViewById(R.id.recycler);

        /*
        Volley volley = null;
        RequestQueue queue = volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Json_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                list = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        skill skill = new skill();
                        skill.setBadgeurl(jsonObject.getString("badgeUrl"));
                        skill.setCountry(jsonObject.getString("country").toString());
                        skill.setHours(jsonObject.getString("score").toString());
                        skill.setName(jsonObject.getString("name").toString());

                        list.add(skill);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
                recyclerView.getLayoutManager().setMeasurementCacheEnabled(false);
                skilladapter leaderadapter = new skilladapter(getActivity(),list);
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
                .client(okHttp)
                .build();
        skillservices skillservices = retrofit.create(com.example.gadso.skillservices.class);


        Call<List<skill>> leaderRequest = skillservices.getSkill();
       leaderRequest.enqueue(new Callback<List<skill>>() {
           @Override
           public void onResponse(Call<List<skill>> call, Response<List<skill>> response) {
               Log.i("our volley error", String.valueOf((ArrayList<skill>) response.body()));
               ArrayList<skill>  list = (ArrayList<skill>) response.body();

               recyclerView.setHasFixedSize(true);
               recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
               recyclerView.getLayoutManager().setMeasurementCacheEnabled(false);
               skilladapter skilladapter= new skilladapter(getActivity(),list);
               Log.i("our volley error", String.valueOf(list));
               recyclerView.setAdapter(skilladapter);
           }

           @Override
           public void onFailure(Call<List<skill>> call, Throwable t) {

           }
       });

        return view;
    }
}
