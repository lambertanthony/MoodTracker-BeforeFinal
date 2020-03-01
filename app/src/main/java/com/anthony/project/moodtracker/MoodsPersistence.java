package com.anthony.project.moodtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

    private static MoodsPersistence moodsPersistenceInstance;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor sharedPreferencesEditor;

    private MoodsPersistence(Context context) {
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(context.getPackageName()+"."+FILE_NAME, Context.MODE_PRIVATE);
        }
    }

    public static synchronized MoodsPersistence getInstance(Context context) {
        if(moodsPersistenceInstance == null) moodsPersistenceInstance = new MoodsPersistence(context);
        return moodsPersistenceInstance;
    }

    public void saveMoodsData(ArrayList<Mood> moodsData) {
        sharedPreferencesEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        Type moodsListType = new TypeToken<ArrayList<Mood>>() {}.getType();
        String moods_data = gson.toJson(moodsData, moodsListType);
        sharedPreferencesEditor.putString(MOODS_DATA, moods_data);
        sharedPreferencesEditor.apply();
    }
    public static void removeAll() {
        try {
            if(sharedPreferences != null) {

                sharedPreferencesEditor = sharedPreferences.edit();
                sharedPreferencesEditor.clear();
                sharedPreferencesEditor.apply();
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public ArrayList<Mood> getMoodsData() {

        ArrayList<Mood> moodsData;
        String jsonMoodsData = sharedPreferences.getString(MOODS_DATA,null);
        Log.i("json string " ,"json " +jsonMoodsData );
        if (jsonMoodsData == null) {
            return createEmptyMoodsData();

        } else {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            builder.disableHtmlEscaping();
            Gson gson = builder.create();
            Type moodsListType = new TypeToken<ArrayList<Mood>>() {}.getType();
            moodsData = gson.fromJson(jsonMoodsData, moodsListType);
            return moodsData;


        }
    }

        private ArrayList<Mood> createEmptyMoodsData () {
            ArrayList<Mood> tempMood = new ArrayList<>();
            List<LocalDate> listOfDate = getDatesBetween();
            for (int i = 0; i < listOfDate.size(); i++) {
                tempMood.add(new Mood(listOfDate.get(i)));

            }

            return tempMood;

        }
    }






















