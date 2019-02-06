package com.averoes.myasynctaskloader;

import org.json.JSONObject;

import java.text.DecimalFormat;

public class WeatherItems {
    private int id;
    private String nama, currentWeather, description, temperature;

    public WeatherItems (JSONObject object){

        try {
            int id = object.getInt("id");
            String name = object.getString("name");
           String nowWeather = object.getJSONArray("weather").getJSONObject(0).getString("main");
           String description = object.getJSONArray("weather").getJSONObject(0).getString("main");
           double tempInKelvin = object.getJSONObject("main").getDouble("temp");
           double tempInCelcius = tempInKelvin-273;

           String temperatur = new DecimalFormat("##.##").format(tempInCelcius);
           this.id = id;
           this.nama = name;
           this.currentWeather = nowWeather;
           this.description = description;
           this.temperature = temperatur;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(String currentWeather) {
        this.currentWeather = currentWeather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperatur() {
        return temperature;
    }

    public void setTemperatur(String temeperature) {
        this.temperature = temeperature;
    }
}
