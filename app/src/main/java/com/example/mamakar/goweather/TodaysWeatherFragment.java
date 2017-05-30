package com.example.mamakar.goweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Mamakar on 30/05/17.
 */

public class TodaysWeatherFragment extends Fragment {

    static TextView city;
    static TextView temp;
    static  TextView desp;
    static LinearLayout back_weather;
    static ImageView imges;
    private View mainView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.single_city, null);


        temp = (TextView) mainView.findViewById(R.id.showtemp);
        imges=(ImageView) mainView.findViewById(R.id.imageView);
        city = (TextView) mainView.findViewById(R.id.city);
        desp = (TextView) mainView.findViewById(R.id.description);
        Intent intent = getActivity().getIntent();
        String tv1= intent.getExtras().getString("location");




        DownloadTask task = new DownloadTask();


        task.execute("http://api.openweathermap.org/data/2.5/forecast?q="+String.valueOf(tv1)+",DE&appid=9f54540cbe4e4980ddca6ba8201b8f6e");
        //  String k = " http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&&appid=9f54540cbe4e4980ddca6ba8201b8f6e";


//        String pump = "http://api.openweathermap.org/data/2.5/forecast?q=London,DE&appid=9f54540cbe4e4980ddca6ba8201b8f6e";
        //      task.execute(pump);


        return mainView;
    }
}
