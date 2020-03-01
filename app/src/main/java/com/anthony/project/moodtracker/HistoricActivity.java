package com.anthony.project.moodtracker;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
public class HistoricActivity extends AppCompatActivity implements View.OnClickListener{
    private Context context;

    ArrayList<Mood> arrayHistoric;
    private String[] textForTextView = {"Il y'a 7 jours",
            "Il y'a 6 jours",
            "Il y'a 5 jours",
            "Il y'a 4 jours",
            "Il y'a 3 jours",
            "Avant-hier",
            "Hier"
    };
    //  private Mood[] arrayHistoric = new Mood[7];
    ViewGroup.LayoutParams layoutParams;
    private Button commentButton;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private int width;
    private TextView text;
    SingletonMoodsData  singletonMoodsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        singletonMoodsData = SingletonMoodsData.getInstance();
        arrayHistoric= singletonMoodsData.getArray();
        setContentView(R.layout.activity_historic);

        context = this;


        this.displayHistoric();
        this.linkTheWidgets();
        this.setTheOnClickListener();



    }


    private void displayHistoric() {


        for (int i = 0; i < arrayHistoric.size(); i++) {
            int textViewId = getResources().getIdentifier("text" +(i+1), "id", getPackageName());
            text = findViewById(textViewId);
            int layoutId = getResources().getIdentifier("relativeLayout"+(i+1), "id", getPackageName());
            RelativeLayout relativeLayout = findViewById(layoutId);
            int buttonId = getResources().getIdentifier("button" +(i+1), "id", getPackageName());
            commentButton = findViewById(buttonId);

            if (arrayHistoric.get(i) != null) {
                int mood = arrayHistoric.get(i).getMoodState();
                String comment = arrayHistoric.get(i).getComment();
                enableCommentButton(comment);
                text.setText(textForTextView[i]);
                commentButton.setTag(comment);
                width = this.getWidth();

                switch (mood) {
                    case 0:
                        setParamsForNoMood(relativeLayout);
                        break;
                    case 1:
                        setParamsForSuperHappyMood(relativeLayout);
                        break;
                    case 2:
                        setParamsForHappyMood(relativeLayout);
                        break;
                    case 3:
                        setParamsForNormalMood(relativeLayout);
                        break;
                    case 4:
                        setParamsForDisappointedMood(relativeLayout);
                        break;
                    case 5:
                        setParamsForSadMood(relativeLayout);
                }
            } else {
                setParamsForNoHistoric(relativeLayout);
            }
        }
    }



    private void enableCommentButton(String comment) {
        if (comment.equals("Not Existe")) {
            commentButton.setVisibility(View.INVISIBLE);
        } else {
            commentButton.setVisibility(View.VISIBLE);
        }
    }



    private void setParamsForSuperHappyMood(RelativeLayout relativeLayout) {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
    }

    private void setParamsForHappyMood(RelativeLayout relativeLayout) {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.light_sage));
        layoutParams = relativeLayout.getLayoutParams();
        layoutParams.width = 4 * width / 5;
        relativeLayout.setLayoutParams(layoutParams);
    }

    private void setParamsForNormalMood(RelativeLayout relativeLayout) {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65));
        layoutParams = relativeLayout.getLayoutParams();
        layoutParams.width = 3 * width / 5;
        relativeLayout.setLayoutParams(layoutParams);
    }

    private void setParamsForDisappointedMood(RelativeLayout relativeLayout) {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.warm_grey));
        layoutParams = relativeLayout.getLayoutParams();
        layoutParams.width = 2 * width / 5;
        relativeLayout.setLayoutParams(layoutParams);
    }

    private void setParamsForSadMood(RelativeLayout relativeLayout) {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.faded_red));
        layoutParams = relativeLayout.getLayoutParams();
        layoutParams.width = width / 5;
        relativeLayout.setLayoutParams(layoutParams);
    }

    private void setParamsForNoHistoric(RelativeLayout relativeLayout) {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.faded_red));
        text.setText("pas historique");
        commentButton.setVisibility(View.INVISIBLE);
    }

    private void setParamsForNoMood(RelativeLayout relativeLayout) {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        text.setText("pas d'emotion");
    }

    /**
     *
     * @return the width of the window
     */
    private int getWidth() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        return width;
    }


    @Override
    public void onClick(View v) {
        String comment = v.getTag().toString();
        Toast.makeText(this, comment, Toast.LENGTH_SHORT).show();
    }

    private void linkTheWidgets() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);

    }

    private void setTheOnClickListener() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
    }
}
