package com.RogersCenter.readwritespell.tutorials;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.RogersCenter.readwritespell.R;
import com.RogersCenter.readwritespell.StaticValues;
import com.RogersCenter.readwritespell.letters.Letter_lessons_Activity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TutorialsHomeActivity extends AppCompatActivity {

    @BindView(R.id.tv_tutorial_introduction)
    TextView tvTutorialIntroduction;
    @BindView(R.id.tv_tutorial_history)
    TextView tvTutorialHistory;
    @BindView(R.id.tv_tutorial_vowels)
    TextView tvTutorialVowels;
    @BindView(R.id.iv_tutorial_back)
    ImageView ivTutorialBack;
    ImageView iv_tutorial_introduction, iv_tutorial_history, iv_tutorial_vowels;
    private PopupWindow mPopupWindow;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorials_home);
        iv_tutorial_introduction = findViewById(R.id.iv_tutorial_introduction);
        iv_tutorial_history = findViewById(R.id.iv_tutorial_history);
        iv_tutorial_vowels = findViewById(R.id.iv_tutorial_vowels);

        if (StaticValues.GetPreferences("FLOW", TutorialsHomeActivity.this).equalsIgnoreCase("RECOMMENDED")) {
            String LASTOPENCLASS = StaticValues.GetPreferences("Last_Open_Lesson", TutorialsHomeActivity.this);
            String LASTOPENParent = StaticValues.GetPreferences("Last_Open_ParentLesson", TutorialsHomeActivity.this);
            if (LASTOPENCLASS != null && LASTOPENParent != null) {
                if (LASTOPENCLASS.equalsIgnoreCase("Tutorials_Activity"))
                {
                    int pos = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", TutorialsHomeActivity.this));
                    tutorials(pos);
                }
            }
        }

        ButterKnife.bind(this);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @OnClick({R.id.iv_tutorial_back, R.id.tv_tutorial_introduction, R.id.tv_tutorial_history, R.id.tv_tutorial_vowels})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_tutorial_back:
                ivTutorialBack.startAnimation(AnimationUtils.loadAnimation(TutorialsHomeActivity.this, R.anim.image_click));
                finish();
                break;
            case R.id.tv_tutorial_introduction:
                tvTutorialIntroduction.startAnimation(AnimationUtils.loadAnimation(TutorialsHomeActivity.this, R.anim.image_click));
                tutorials(0);
                break;
            case R.id.tv_tutorial_history:
                tvTutorialHistory.startAnimation(AnimationUtils.loadAnimation(TutorialsHomeActivity.this, R.anim.image_click));
                tutorials(1);
                break;
            case R.id.tv_tutorial_vowels:
                tvTutorialVowels.startAnimation(AnimationUtils.loadAnimation(TutorialsHomeActivity.this, R.anim.image_click));
                tutorials(2);
                break;
        }
    }

    public void tutorials(int position) {
        Intent intent = new Intent(TutorialsHomeActivity.this, Tutorials_Activity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

}