package com.anthony.project.moodtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private List<Mood> moodList;
    private MoodAdapter moodAdapter;
    private Button historicButton;
    private Button addMoodButton;
    private TextView text_view;
    public static final int[] tableauImg = new int[]{ R.drawable.smiley_sad, R.drawable.smiley_disappointed, R.drawable.smiley_normal, R.drawable.smiley_happy, R.drawable.smiley_super_happy};

    public static final int[] tableauFnd = new int[]{R.color.faded_red, R.color.warm_grey, R.color.cornflower_blue_65, R.color.light_sage, R.color.banana_yellow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.viewPager2);
        historicButton = findViewById(R.id.btnHist);
        addMoodButton = findViewById(R.id.btnComm);


        moodAdapter = new MoodAdapter(this, tableauImg);
        viewPager2.setAdapter(moodAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                viewPager2.setBackgroundColor(getResources().getColor(tableauFnd[position]));
                Log.i("test position", "onPageSelected: position" + position);
            }
        });


        addMoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder moodDialog = new AlertDialog.Builder(MainActivity.this);
                moodDialog.setTitle("Comment for Mood state");
                final EditText commentInput = new EditText(MainActivity.this);
                commentInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
                moodDialog.setView(commentInput);

                moodDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      String   comment = commentInput.getText().toString();
                        //saveData();
                        Toast.makeText(MainActivity.this, comment, Toast.LENGTH_LONG).show();
                    }
                });

                moodDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                moodDialog.show();
                commentInput.requestFocus();
            }
        });

    }


    }

