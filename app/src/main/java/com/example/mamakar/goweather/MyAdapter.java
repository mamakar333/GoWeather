package com.example.mamakar.goweather;

import android.content.Context;
import  android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] mDataset;

    private List<ListItem> listItems;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView mTextView;
        public  TextView description;
        public  TextView temperature;
        public ImageView imageView;
        public MyViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.tv_text);
            description = (TextView) v.findViewById(R.id.tv_blah);
            temperature = (TextView) v.findViewById(R.id.temp);
            imageView=(ImageView) v.findViewById(R.id.iv_image);
        }
    }



    public  MyAdapter(List<ListItem> listItems, Context context){

        this.listItems=listItems;
        this.context=context;
    }

    /*
    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

*/
    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_weekly, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      //  holder.mTextView.setText(mDataset[position]);

        ListItem listItem = listItems.get(position);

        String floyd;

        int tempo= (int) (listItem.getTemp() - 273.15);

     //   System.out.println("The value of temperature is "+ tempo);
     //   System.out.println("The value of temperature is "+ listItem.getTemp());
   //  floyd=   String.format("Value of tempo: %.2f",tempo);

        floyd = Double.toString(tempo);

        String type = listItem.getType();

        if(type != null && type.equals("Clear")){

            holder.imageView.setImageResource(R.mipmap.clear_sky);
        }
        if(type != null && type.equals("Cloudy")){

            holder.imageView.setImageResource(R.mipmap.clear);
        }
        if(type != null && type.equals("Rain")){

            holder.imageView.setImageResource(R.mipmap.rain);
        }
        if(type != null && type.equals("Snow")){

            holder.imageView.setImageResource(R.mipmap.snow);
        }

        holder.mTextView.setText(listItem.getDate());
        holder.description.setText(listItem.getDescription());
        holder.temperature.setText(floyd+" \u2103");
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}