package com.anthony.project.moodtracker;

import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MoodsDateManipulator {
    public static Mood findMood (List<Mood> moodsList, LocalDate askingDate ) {
        Iterator<Mood> iterator = moodsList.iterator();
        while (iterator.hasNext()) {
            Mood mood = iterator.next();
            if (mood.getRecordDate().isEqual(askingDate)) {
                return mood;
            }
        }
        return null;
    }



    public static long getBetweenDays(LocalDate begin, LocalDate end) {
        return  ChronoUnit.DAYS.between(begin, end);

    }


    public static LocalDate getBeforeWeek() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.minusDays(7);
    }


    public static List<LocalDate> getDatesBetween() {
        List<LocalDate> datesInRange = new ArrayList<>();
        LocalDate currentTime = LocalDate.now();

        for (LocalDate indexDate = currentTime; indexDate.isAfter(getBeforeWeek()); indexDate = indexDate.minusDays(1))
        {
            datesInRange.add(indexDate);
        }

        return datesInRange;
    }




}
































