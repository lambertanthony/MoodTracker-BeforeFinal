package com.anthony.project.moodtracker;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.anthony.project.moodtracker.MoodsDateManipulator.getDatesBetween;

public class SingletonMoodsData {

    private static SingletonMoodsData singletonMoodsData;
    private ArrayList<Mood> moodsData = null;


    public static synchronized SingletonMoodsData getInstance() {
        if (singletonMoodsData == null)
            singletonMoodsData = new SingletonMoodsData();

        return singletonMoodsData;
    }

    private SingletonMoodsData() {
        moodsData = new ArrayList<Mood>();
    }

    // retrieve array from anywhere
    public ArrayList<Mood> getArray() {
        return this.moodsData;
    }

    public void setArray(ArrayList<Mood> moods) {
        this.moodsData = moods;
    }


    public void addToArray(Mood mood) {

        LocalDate moodRecordDate = mood.getRecordDate();
        int size = moodsData.size();
        for (int i = 0; i < size; i++) {
            if (moodsData.get(i).getRecordDate().isEqual(moodRecordDate)) {
                moodsData.remove(i);
                moodsData.add(i, mood);
            }
        }
        moodsData.add(mood);


    }

    public void getWeeklyMood() {

        ArrayList<Mood> tempMood = new ArrayList<>();
        List<LocalDate> listOfDate = getDatesBetween();
        Mood mood;

        for (int i = 0; i < listOfDate.size(); i++) {
            mood = findMood(moodsData, listOfDate.get(i));

            if (mood == null) {
                tempMood.add(new Mood(listOfDate.get(i)));
            } else {
                tempMood.add(mood);
            }
        }

        this.moodsData = tempMood;

    }

    private static Mood findMood(List<Mood> moodsList, LocalDate askingDate) {
        Iterator<Mood> iterator = moodsList.iterator();
        while (iterator.hasNext()) {
            Mood mood = iterator.next();
            if (mood.getRecordDate().isEqual(askingDate)) {
                return mood;
            }
        }
        return null;
    }
}