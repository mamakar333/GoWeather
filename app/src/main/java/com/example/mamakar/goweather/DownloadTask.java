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

            /*if(coord.has("lon")) {
                 lon = Double.parseDouble(coord.getString("lon"));
            }
            if(coord.has("lat")) {
                 lat = Double.parseDouble(coord.getString("lat"));
            }
 System.out.println("Ths longitude is "+lon+ "the latitutde is "+lat);
           */





         //   JSONObject list = new JSONObject((jsonObject.getString("list")));


            JSONArray forecastArray = jsonObject.getJSONArray("list");
            String description=null;



            double minTemp = 0, maxTemp=0 ;

            for(int i = 0; i < forecastArray.length(); i++) {
                JSONObject dailyForecast = forecastArray.getJSONObject(i);
                JSONObject tempObject = dailyForecast.getJSONObject("main");
              //  JSONObject weather = dailyForecast.getJSONObject("weather");

                JSONArray weatherArray = dailyForecast.getJSONArray("weather");
                for(int j=0;j<weatherArray.length();j++){
                    JSONObject floyd = weatherArray.getJSONObject(j);
                    description = floyd.getString("description");


                }

                minTemp = tempObject.getDouble("temp");

        //        description=weather.getString("description");


            //    maxTemp = tempObject.getDouble("max");
                //add these minTemp and maxTemp to array or the
                //way you want to use

        /*        JSONArray weatherArray = forecastArray.getJSONArray(Integer.parseInt("weather"));
                for(int j=0;j<weatherArray.length();j++){
                    JSONObject floyd = weatherArray.getJSONObject(j);
                    description = floyd.getString("description");


                }

                */
            }

          //  JSONObject weatherData = new JSONObject(jsonObject.getString("list.main"));

          //  Double temperature = Double.parseDouble(weatherData.getString("temp"));
            System.out.println("This is the Tribute "+description.replace(" ", ""));
            int temperatureInteger = (int) (minTemp - 273.15);
       //     String placeName = jsonObject.getString("name");
            System.out.println("Fucking Temperature is "+temperatureInteger);

            MainActivity.temp.setText(String.valueOf(temperatureInteger+" \u2103"));
            MainActivity.city.setText(String.valueOf(cityName));
            MainActivity.desp.setText(String.valueOf(description));

            if(description.trim()=="clearsky"){
               MainActivity.imges.setImageResource(R.mipmap.clear);
                System.out.println("Ok u entered here man lets party");
            }
            if(description.trim()=="lightrain"){

                MainActivity.imges.setImageResource(R.mipmap.rain);
                System.out.println("Ok u entered here man lets party");
            }

            if(description.replace(" ","")=="scatteredclouds"){

                MainActivity.imges.setImageResource(R.mipmap.rain);
                System.out.println("Ok u entered here man lets party");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
