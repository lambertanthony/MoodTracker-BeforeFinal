package com.anthony.project.moodtracker;

import org.threeten.bp.LocalDate;

public class Mood {

    /**
     * Mood  en int
     * 0 = triste
     * 1 = déçu
     * 2 = normal
     * 3 = heureux
     * 4 = super heureux
     * -1  = not existe
     */

    private  int moodState;
    private String comment;
    private LocalDate recordDate;
    private final String NOT_EXISTE = "Not Existe";


    public Mood(int moodState, String comment, LocalDate recordDate) {
        this.moodState = moodState;
        this.comment = comment;
        this.recordDate = recordDate;
    }

    public  Mood(LocalDate recordDate) {

        this.moodState =  -1;
        this.comment = NOT_EXISTE;
        this.recordDate = recordDate;

    }


    public boolean isNotExistMood() {
        return  this.moodState == -1;

    }



    public int getMoodState() {
        return moodState;
    }

    public void setMoodState(int moodState) {
        this.moodState = moodState;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    @Override
    public String toString() {
        return "Mood{" +
                "moodState=" + moodState +
                ", comment='" + comment + '\'' +
                ", recordDate=" + recordDate.toString() +
                '}';
    }
}