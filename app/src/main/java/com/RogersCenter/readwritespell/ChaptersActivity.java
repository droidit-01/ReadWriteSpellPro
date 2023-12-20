package com.RogersCenter.readwritespell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.RogersCenter.readwritespell.dictionary.Dict_Lessons_Activity;
import com.RogersCenter.readwritespell.langs.Lang_lessons_Activity;
import com.RogersCenter.readwritespell.letters.Letter_lessons_Activity;
import com.RogersCenter.readwritespell.reading.Reading_lessons_Activity;
import com.RogersCenter.readwritespell.spellings.Spelling_lessons_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Lessons_Activity;

public class ChaptersActivity extends AppCompatActivity {

    CardView cvLetter;
    CardView cvRead;
    CardView cvWrit;
    CardView cvSpell;
    CardView cvLang;
    CardView cvDictionary;
    ConstraintLayout cl1;
    ImageView ivLessonBack;
    ImageView ivHelp;
    ImageView ivLessonHome, img_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);

        cvLetter = findViewById(R.id.cv_letter);
        cvRead = findViewById(R.id.cv_read);
        cvWrit = findViewById(R.id.cv_writ);
        cvSpell = findViewById(R.id.cv_spell);
        cvLang = findViewById(R.id.cv_lang);
        cvDictionary = findViewById(R.id.cv_dictionary);
        cl1 = findViewById(R.id.cl1);
        ivLessonBack = findViewById(R.id.iv_l1_back);
        ivHelp = findViewById(R.id.iv_l1_help);
        ivLessonHome = findViewById(R.id.iv_l1_home);
        img_help = findViewById(R.id.img_help);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String FLOW = getIntent().getStringExtra("FLOW");

        if (StaticValues.GetPreferences("FLOW", ChaptersActivity.this).equalsIgnoreCase("RECOMMENDED")) {
            String LASTOPENCLASS = StaticValues.GetPreferences("Last_Open_Lesson", ChaptersActivity.this);
            String LASTOPENParent = StaticValues.GetPreferences("Last_Open_ParentLesson", ChaptersActivity.this);
            if (LASTOPENCLASS != null && LASTOPENParent != null) {
                Intent i;
                if (LASTOPENParent.equalsIgnoreCase("Letters")) {
                    i = new Intent(ChaptersActivity.this, Letter_lessons_Activity.class);
                    startActivity(i);
                } else if (LASTOPENParent.equalsIgnoreCase("Dictionary")) {
                    i = new Intent(ChaptersActivity.this, Dict_Lessons_Activity.class);
                    startActivity(i);
                } else if (LASTOPENParent.equalsIgnoreCase("Langs")) {
                    i = new Intent(ChaptersActivity.this, Lang_lessons_Activity.class);
                    startActivity(i);
                } else if (LASTOPENParent.equalsIgnoreCase("Reading")) {
                    i = new Intent(ChaptersActivity.this, Reading_lessons_Activity.class);
                    startActivity(i);
                } else if (LASTOPENParent.equalsIgnoreCase("Spelling")) {
                    i = new Intent(ChaptersActivity.this, Spelling_lessons_Activity.class);
                    startActivity(i);
                } else if (LASTOPENParent.equalsIgnoreCase("Writing")) {
                    i = new Intent(ChaptersActivity.this, Writing_Lessons_Activity.class);
                    startActivity(i);
                }
            }

        }

        cvLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearPreferences();
                cvLetter.startAnimation(AnimationUtils.loadAnimation(ChaptersActivity.this, R.anim.image_click));
                Intent i1 = new Intent(ChaptersActivity.this, Letter_lessons_Activity.class);
                startActivity(i1);
            }
        });
        cvRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearPreferences();
                cvRead.startAnimation(AnimationUtils.loadAnimation(ChaptersActivity.this, R.anim.image_click));
                Intent i2 = new Intent(ChaptersActivity.this, Reading_lessons_Activity.class);
                startActivity(i2);
            }
        });
        cvWrit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearPreferences();
                cvWrit.startAnimation(AnimationUtils.loadAnimation(ChaptersActivity.this, R.anim.image_click));
                Intent i3 = new Intent(ChaptersActivity.this, Writing_Lessons_Activity.class);
                startActivity(i3);
            }
        });
        cvSpell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearPreferences();
                cvSpell.startAnimation(AnimationUtils.loadAnimation(ChaptersActivity.this, R.anim.image_click));
                Intent i4 = new Intent(ChaptersActivity.this, Spelling_lessons_Activity.class);
                startActivity(i4);
            }
        });
        cvLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearPreferences();
                cvLang.startAnimation(AnimationUtils.loadAnimation(ChaptersActivity.this, R.anim.image_click));
                Intent i5 = new Intent(ChaptersActivity.this, Lang_lessons_Activity.class);
                startActivity(i5);
            }
        });
        cvDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearPreferences();
                cvDictionary.startAnimation(AnimationUtils.loadAnimation(ChaptersActivity.this, R.anim.image_click));
                Intent i6 = new Intent(ChaptersActivity.this, Dict_Lessons_Activity.class);
                startActivity(i6);
            }
        });
        ivLessonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivLessonBack.startAnimation(AnimationUtils.loadAnimation(ChaptersActivity.this, R.anim.image_click));
                finish();
            }
        });
        ivHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_help.setVisibility(View.VISIBLE);
            }
        });
        img_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_help.setVisibility(View.GONE);
            }
        });
        ivLessonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivLessonHome.startAnimation(AnimationUtils.loadAnimation(ChaptersActivity.this, R.anim.image_click));
                Intent intent = new Intent(ChaptersActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void clearPreferences() {
        StaticValues.RemovePreferences("Last_Open_position", ChaptersActivity.this);
        StaticValues.RemovePreferences("Last_Open_Lesson", ChaptersActivity.this);
        StaticValues.RemovePreferences("Last_Open_ParentLesson", ChaptersActivity.this);
    }
}