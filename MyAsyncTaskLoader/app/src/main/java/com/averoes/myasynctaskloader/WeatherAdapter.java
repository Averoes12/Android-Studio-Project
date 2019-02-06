package com.averoes.myasynctaskloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WeatherAdapter extends BaseAdapter {

    private ArrayList<WeatherItems> data =new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;

    public WeatherAdapter(Context context) {
        this.context = context;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData (ArrayList<WeatherItems> items){
        data = items;
        notifyDataSetChanged();
    }

    public void addItem(final WeatherItems items){
        data.add(items);
        notifyDataSetChanged();
    }

    public void clearData(){
        data.clear();
    }

    public int getItemViewType (int position){
        return 0;
    }

    public int getViewTypeCount(){
        return 1;
    }

    @Override
    public int getCount() {
       if (data == null) return 0;

       return data.size();
    }

    @Override
    public WeatherItems getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.weather_items, null);

            holder.namaKota =  convertView.findViewById(R.id.textKota);
            holder.temperatur =  convertView.findViewById(R.id.temperatur);
            holder.description =  convertView.findViewById(R.id.desc);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.namaKota.setText(data.get(position).getNama());
        holder.temperatur.setText(data.get(position).getTemperatur());
        holder.description.setText(data.get(position).getDescription());

        return convertView;
    }

    private static class ViewHolder {
        TextView namaKota;
        TextView temperatur;
        TextView description;
    }
}
