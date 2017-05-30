package com.example.mamakar.goweather;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeeklyWeather extends Fragment{

    private static  String URL_DATA="";
     private static String  description=null,type=null;
    private static Double temperature = Double.valueOf(0);

    MyAdapter adapter;
    RecyclerView rv;

    private List<ListItem> listItems;

    public WeeklyWeather() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.weekly_main, container, false);

        Intent intent = getActivity().getIntent();
        String tv1= intent.getExtras().getString("location");

        URL_DATA = "http://api.openweathermap.org/data/2.5/forecast?q="+String.valueOf(tv1)+",DE&appid=9f54540cbe4e4980ddca6ba8201b8f6e";

        rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
   //     MyAdapter adapter = new MyAdapter(new String[]{"test one", "test two", "test three", "test four", "test five" , "test six" , "test seven"});
    //    rv.setAdapter(adapter);

        listItems=new ArrayList<>();

        loadRecyclerViewData();

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }

private void loadRecyclerViewData(){

    final ProgressDialog progressDialog = new ProgressDialog(getActivity());
    progressDialog.setMessage("Loading Data......");
    progressDialog.show();

    final StringRequest stringRequest = new StringRequest(Request.Method.GET,
            URL_DATA,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    progressDialog.dismiss();

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                    JSONObject  coord = new JSONObject(jsonObject.getString("city"));

                        JSONArray jsonArray = jsonObject.getJSONArray("list");

                        for (int i=0; i<jsonArray.length();i++){
                            JSONObject weeklyForecast = jsonArray.getJSONObject(i);
                            JSONObject tempObject = weeklyForecast.getJSONObject("main");
                            JSONArray weatherArray = weeklyForecast.getJSONArray("weather");

                            for(int j=0;j<weatherArray.length();j++){
                                JSONObject floyd = weatherArray.getJSONObject(j);
                                description = floyd.getString("description");
                                type=floyd.getString("main");

                                ListItem item = new ListItem(
                                        weeklyForecast.getString("dt_txt"),
                                        coord.getString("name"),
                                        floyd.getString("description"),
                                        floyd.getString("main"),
                                        tempObject.getDouble("temp")


                                );

                                listItems.add(item);
                            }


                            adapter = new MyAdapter(listItems,getActivity().getApplicationContext());
                            rv.setAdapter(adapter);
                            temperature = tempObject.getDouble("temp");

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            });

    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
    requestQueue.add(stringRequest);


}

}