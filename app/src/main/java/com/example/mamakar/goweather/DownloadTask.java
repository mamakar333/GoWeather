package com.example.mamakar.goweather;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Mamakar on 29/05/17.
 */

public class DownloadTask extends AsyncTask<String,Void,String> {

    String result = "";
    URL url;
    HttpURLConnection urlConnection= null;




    @Override
    protected String doInBackground(String... urls) {


        try {
            url = new URL(urls[0]);

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();

            while (data !=-1)
            {

                char current = (char) data;
                result+=current;
                data= reader.read();
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);


        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject coord = null;
            Double lat=null,lon = null;
            String cityName = null;


            if (jsonObject.has("city")){
                 coord = new JSONObject(jsonObject.getString("city"));
        }

            cityName = coord.getString("name");



            JSONArray forecastArray = jsonObject.getJSONArray("list");
            String description=null , type=null;



            double minTemp = 0, maxTemp=0 ;

            for(int i = 0; i < forecastArray.length(); i++) {
                JSONObject dailyForecast = forecastArray.getJSONObject(i);
                JSONObject tempObject = dailyForecast.getJSONObject("main");
              //  JSONObject weather = dailyForecast.getJSONObject("weather");

                JSONArray weatherArray = dailyForecast.getJSONArray("weather");
                for(int j=0;j<weatherArray.length();j++){
                    JSONObject floyd = weatherArray.getJSONObject(j);
                    description = floyd.getString("description");
                    type=floyd.getString("main");


                }

                minTemp = tempObject.getDouble("temp");

        //        description=weather.getString("description");

            }


            int temperatureInteger = (int) (minTemp - 273.15);
            System.out.println("Fucking Temperature is "+temperatureInteger);

            TodaysWeatherFragment.temp.setText(String.valueOf(temperatureInteger+" \u2103"));
            TodaysWeatherFragment.city.setText(String.valueOf(cityName));
            TodaysWeatherFragment.desp.setText(String.valueOf(description));
            System.out.println("No the condition is here "+type);
            if(type.equals("Clear")){
               TodaysWeatherFragment.imges.setImageResource(R.mipmap.clear_sky);
                System.out.println("Ok u entered here man lets party");
            }
            if(type.equals("Rian")){

                TodaysWeatherFragment.imges.setImageResource(R.mipmap.rain);
                System.out.println("Ok u entered here man lets party");
            }

            if(type.equals("Clouds")){

                TodaysWeatherFragment.imges.setImageResource(R.mipmap.clear);
                System.out.println("Ok u entered here man lets party");
            }
            if(type.equals("Snow")){

                TodaysWeatherFragment.imges.setImageResource(R.mipmap.snow);
                System.out.println("Ok u entered here man lets party");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
