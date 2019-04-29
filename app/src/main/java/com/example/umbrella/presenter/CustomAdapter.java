package com.example.umbrella.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.umbrella.R;
import com.example.umbrella.model.ForecastList;
import com.squareup.picasso.Picasso;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

    private final String TAG = CustomAdapter.class.getSimpleName();
    private ForecastList dataSet;
    private String meridian = " AM";
    private int time;
    private static String IMAGE_URL = "http://openweathermap.org/img/w/"; //10d.png

    public CustomAdapter(ForecastList dataSet){
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CustomViewHolder(LayoutInflater.from(viewGroup.getContext())
                                    .inflate(R.layout.item_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder customViewHolder, int i) {
        //Log.d(TAG, "onBindViewHolder: " + dataSet.getList().size() + " " + dataSet.getCity().getName());

        //customViewHolder.tvTemp1.setText("" + Math.round(Presenter.temp) + "°");
        String dateTime = Presenter.lists[i].get(i).getDateTime();
        if(i == 0){
            customViewHolder.tvDay.setText("Today");
        }else if(i == 1){
            customViewHolder.tvDay.setText("Tomorrow");
        }else{
            customViewHolder.tvDay.setText(dateTime.substring(0, dateTime.indexOf(' ')));
        }

        Log.d(TAG, "onBindViewHolder: " + Presenter.lists[4].get(0).getDateTime() + " " + i);

        for(int j = 0; j < Presenter.lists[i].size(); j++){
            /*String fullTime = Presenter.lists[i].get(j).getDateTime();
            fullTime = fullTime.substring(fullTime.indexOf(" ") + 1);
            if(fullTime.charAt(0) != '0'){
                time = Integer.parseInt(fullTime.substring(0, fullTime.indexOf(':')));

                if(time >= 12){
                    meridian = " PM";
                }
            }*/


        }

        if(i == 0){
            //String tvTime = "tvTime" + 1;

            for(int j = 0; j < Presenter.lists[0].size(); j++){
                customViewHolder.timeTextViews[j].setText(getCleanTime(Presenter.lists[0].get(j).getDateTime()));
                customViewHolder.tempTextViews[j].setText("" + Math.round(Presenter.lists[0].get(j).getMain().getTemp()) + "°");
                String img = Presenter.lists[i].get(j).getWeather().get(0).getIcon();
                Picasso.get().load(IMAGE_URL + img + ".png").into(customViewHolder.imageViews[j]);
            }

        }else if(i == 1){
            for(int j = 0; j < Presenter.lists[1].size(); j++){
                customViewHolder.timeTextViews[j].setText(getCleanTime(Presenter.lists[1].get(j).getDateTime()));
                customViewHolder.tempTextViews[j].setText("" + Math.round(Presenter.lists[1].get(j).getMain().getTemp()) + "°");
                String img = Presenter.lists[i].get(j).getWeather().get(0).getIcon();
                Picasso.get().load(IMAGE_URL + img + ".png").into(customViewHolder.imageViews[j]);
            }
        }else if(i == 2){
            for(int j = 0; j < Presenter.lists[i].size(); j++){
                customViewHolder.timeTextViews[j].setText(getCleanTime(Presenter.lists[2].get(j).getDateTime()));
                customViewHolder.tempTextViews[j].setText("" + Math.round(Presenter.lists[2].get(j).getMain().getTemp()) + "°");
                String img = Presenter.lists[i].get(j).getWeather().get(0).getIcon();
                Picasso.get().load(IMAGE_URL + img + ".png").into(customViewHolder.imageViews[j]);
            }
        }else if(i == 3){
            for(int j = 0; j < Presenter.lists[i].size(); j++){
                customViewHolder.timeTextViews[j].setText(getCleanTime(Presenter.lists[3].get(j).getDateTime()));
                customViewHolder.tempTextViews[j].setText("" + Math.round(Presenter.lists[3].get(j).getMain().getTemp()) + "°");
                String img = Presenter.lists[i].get(j).getWeather().get(0).getIcon();
                Picasso.get().load(IMAGE_URL + img + ".png").into(customViewHolder.imageViews[j]);
            }
        }else if(i == 4){
            for(int j = 0; j < Presenter.lists[i].size(); j++){
                customViewHolder.timeTextViews[j].setText(getCleanTime(Presenter.lists[4].get(j).getDateTime()));
                customViewHolder.tempTextViews[j].setText("" + Math.round(Presenter.lists[4].get(j).getMain().getTemp()) + "°");
                String img = Presenter.lists[i].get(j).getWeather().get(0).getIcon();
                Picasso.get().load(IMAGE_URL + img + ".png").into(customViewHolder.imageViews[j]);
            }
        }else{}

    }

    @Override
    public int getItemCount() {
        return 5; //(dataSet == null ? 0 : dataSet.getList().size());
    }

    public String getCleanTime(String s){
        String fullTime = s;
        fullTime = fullTime.substring(fullTime.indexOf(" ") + 1);
        if(fullTime.charAt(0) != '0'){
            time = Integer.parseInt(fullTime.substring(0, fullTime.indexOf(':')));

            if(time >= 12){
                meridian = " PM";
            } else{
                meridian = " AM";
            }
        }else{meridian = " AM";}

        return fullTime.substring(0, fullTime.lastIndexOf(':')) + meridian;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        public TextView[] timeTextViews, tempTextViews;
        public ImageView[] imageViews;
        CardView cardView;
        TextView tvDay, tvTime1, tvTemp1, tvTime2, tvTemp2, tvTime3, tvTemp3, tvTime4,
                tvTemp4, tvTime5, tvTemp5, tvTime6, tvTemp6, tvTime7, tvTemp7,
                tvTime8, tvTemp8, tvCity, tvMain;
        ImageView ivImage1, ivImage2, ivImage3, ivImage4, ivImage5, ivImage6, ivImage7,
                    ivImage8;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_view);

            tvDay = itemView.findViewById(R.id.tv_day);

            /*tvCity = itemView.findViewById(R.id.tv_city);
            Log.d(TAG, "CustomViewHolder: " + (tvCity.getText().toString() == null));
            tvMain = itemView.findViewById(R.id.tv_condition);*/

            timeTextViews = new TextView[8];
            tvTime1 = itemView.findViewById(R.id.tv_time_1);
            timeTextViews[0] = tvTime1;
            tvTime2 = itemView.findViewById(R.id.tv_time_2);
            timeTextViews[1] = tvTime2;
            tvTime3 = itemView.findViewById(R.id.tv_time_3);
            timeTextViews[2] = tvTime3;
            tvTime4 = itemView.findViewById(R.id.tv_time_4);
            timeTextViews[3] = tvTime4;
            tvTime5 = itemView.findViewById(R.id.tv_time_5);
            timeTextViews[4] = tvTime5;
            tvTime6 = itemView.findViewById(R.id.tv_time_6);
            timeTextViews[5] = tvTime6;
            tvTime7 = itemView.findViewById(R.id.tv_time_7);
            timeTextViews[6] = tvTime7;
            tvTime8 = itemView.findViewById(R.id.tv_time_8);
            timeTextViews[7] = tvTime8;

            tempTextViews = new TextView[8];
            tvTemp1 = itemView.findViewById(R.id.tv_temp_1);
            tempTextViews[0] = tvTemp1;
            tvTemp2 = itemView.findViewById(R.id.tv_temp_2);
            tempTextViews[1] = tvTemp2;
            tvTemp3 = itemView.findViewById(R.id.tv_temp_3);
            tempTextViews[2] = tvTemp3;
            tvTemp4 = itemView.findViewById(R.id.tv_temp_4);
            tempTextViews[3] = tvTemp4;
            tvTemp5 = itemView.findViewById(R.id.tv_temp_5);
            tempTextViews[4] = tvTemp5;
            tvTemp6 = itemView.findViewById(R.id.tv_temp_6);
            tempTextViews[5] = tvTemp6;
            tvTemp7 = itemView.findViewById(R.id.tv_temp_7);
            tempTextViews[6] = tvTemp7;
            tvTemp8 = itemView.findViewById(R.id.tv_temp_8);
            tempTextViews[7] = tvTemp8;

            imageViews = new ImageView[8];
            ivImage1 = itemView.findViewById(R.id.iv_image_1);
            imageViews[0] = ivImage1;
            ivImage2 = itemView.findViewById(R.id.iv_image_2);
            imageViews[1] = ivImage2;
            ivImage3 = itemView.findViewById(R.id.iv_image_3);
            imageViews[2] = ivImage3;
            ivImage4 = itemView.findViewById(R.id.iv_image_4);
            imageViews[3] = ivImage4;
            ivImage5 = itemView.findViewById(R.id.iv_image_5);
            imageViews[4] = ivImage5;
            ivImage6 = itemView.findViewById(R.id.iv_image_6);
            imageViews[5] = ivImage6;
            ivImage7 = itemView.findViewById(R.id.iv_image_7);
            imageViews[6] = ivImage7;
            ivImage8 = itemView.findViewById(R.id.iv_image_8);
            imageViews[7] = ivImage8;

        }

    }
}
