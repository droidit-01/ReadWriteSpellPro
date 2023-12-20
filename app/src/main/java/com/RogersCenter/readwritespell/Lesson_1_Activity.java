package com.RogersCenter.readwritespell;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Lesson_1_Activity extends AppCompatActivity {

    TextView tvChapterTitle;
    ImageView ivChapterBack;
    ImageView ivChapterHelp;
    ImageView ivChapterHome;
    ConstraintLayout cl2;
    RecyclerView rvChapters;
    List<String> CHAPTERS = new ArrayList<>();
    int CHAPTERScount=0;
    String lesson;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_1);

        tvChapterTitle = findViewById(R.id.tv_chapter_title);
        ivChapterBack = findViewById(R.id.iv_chapter_back);
        ivChapterHelp = findViewById(R.id.iv_chapter_help);
        ivChapterHome = findViewById(R.id.iv_chapter_home);
        cl2 = findViewById(R.id.cl2);
        rvChapters = findViewById(R.id.rv_chapters);

        lesson = getIntent().getStringExtra("lesson");

        tvChapterTitle.setText("" + lesson);
        if (lesson.equalsIgnoreCase("LETTER")) {
            tvChapterTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_01_abcblock, 0, 0, 0);
            CHAPTERScount=9;
        } else if (lesson.equalsIgnoreCase("READING")) {
            tvChapterTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_02_openbook, 0, 0, 0);
            CHAPTERScount=13;
        } else if (lesson.equalsIgnoreCase("WRITING")) {
            tvChapterTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_06_pencil, 0, 0, 0);
            CHAPTERScount=14;
        } else if (lesson.equalsIgnoreCase("SPELLING")) {
            tvChapterTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_03_spell_check, 0,0, 0);
            CHAPTERScount=10;
        } else if (lesson.equalsIgnoreCase("LANGUAGE")) {
            tvChapterTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_04_grammar, 0, 0, 0);
            CHAPTERScount=15;
        } else if (lesson.equalsIgnoreCase("DICTIONARY")) {
            tvChapterTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_05_dictionary, 0, 0, 0);
            CHAPTERScount=12;
        }

        ivChapterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ivChapterHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ivChapterHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(Lesson_1_Activity.this, HomeActivity.class);
                startActivity(i2);
                finish();
            }
        });
    }
}