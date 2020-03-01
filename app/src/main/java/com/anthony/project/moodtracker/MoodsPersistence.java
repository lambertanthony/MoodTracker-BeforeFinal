package com.anthony.project.moodtracker;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.threeten.bp.LocalDate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.anthony.project.moodtracker.MoodsDateManipulator.getDatesBetween;

public class MoodsPersistence {
    private static final String FILE_NAME = "moods.xml";
    private static final String MOODS_DATA = "moods_data";

    public MoodsPersistence() {
        super();
    }

    public void saveMoodsData(Context context, ArrayList<Mood> moodsData) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        Gson gson = new Gson();
        Type moodsListType = new TypeToken<ArrayList<Mood>>() {
        }.getType();
        String moods_data = gson.toJson(moodsData, moodsListType);
        editor.putString(MOODS_DATA, moods_data);
        editor.apply();
    }


    public ArrayList<Mood> getMoodsData(Context context) {
        SharedPreferences settings;
        ArrayList<Mood> moodsData;
        settings = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if (settings.contains(MOODS_DATA)) {
            String jsonMoodsData = settings.getString(MOODS_DATA, null);
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            builder.disableHtmlEscaping();
            Gson gson = builder.create();
            Type moodsListType = new TypeToken<ArrayList<Mood>>() {
            }.getType();
            moodsData = gson.fromJson(jsonMoodsData, moodsListType);
        } else {
            return createEmptyMoodsData();
        }
        return moodsData;
    }

    private ArrayList<Mood> createEmptyMoodsData() {
        ArrayList<Mood> tempMood = new ArrayList<>();
        List<LocalDate> listOfDate = getDatesBetween();
        for (int i = 0; i < listOfDate.size(); i++) {
            tempMood.add(new Mood(listOfDate.get(i)));

        }

        return tempMood;

    }
}





















