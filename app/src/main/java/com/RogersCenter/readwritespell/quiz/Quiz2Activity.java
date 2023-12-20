package com.RogersCenter.readwritespell.quiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.RogersCenter.readwritespell.HomeActivity;
import com.RogersCenter.readwritespell.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Quiz2Activity extends AppCompatActivity {

    @BindView(R.id.iv_quiz2_back)
    ImageView ivQuiz2Back;
    @BindView(R.id.iv_quiz2_help)
    ImageView ivQuiz2Help;
    @BindView(R.id.iv_quiz2_home)
    ImageView ivQuiz2Home;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.tv3_quiz2)
    TextView tv3Quiz2;
    @BindView(R.id.cv3_quiz2)
    CardView cv3Quiz2;
    @BindView(R.id.tv1_quiz2)
    TextView tv1Quiz2;
    @BindView(R.id.cv1_quiz2)
    CardView cv1Quiz2;
    @BindView(R.id.tv2_quiz2)
    TextView tv2Quiz2;
    @BindView(R.id.cv2_quiz2)
    CardView cv2Quiz2;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.cv1_quiz2_sel)
    CardView cv1Quiz2Sel;
    @BindView(R.id.imageView8)
    ImageView imageView8;
    @BindView(R.id.cv2_quiz2_sel)
    CardView cv2Quiz2Sel;
    @BindView(R.id.imageView9)
    ImageView imageView9;
    @BindView(R.id.cv3_quiz2_sel)
    CardView cv3Quiz2Sel;

    int  clickcount1=0,clickcount2=0,clickcount3=0;
    List<String> Word_LIST = new ArrayList<>();

    String wordtoSEL = "Cat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);
        ButterKnife.bind(this);

        Word_LIST.add("Cat");
        Word_LIST.add("Bat");
        Word_LIST.add("Hat");

        initview();

    }

    public void initview()
    {
        clickcount1=0;clickcount2=0;clickcount3=0;
        tv1Quiz2.setText("" + Word_LIST.get(0));
        tv2Quiz2.setText("" + Word_LIST.get(1));
        tv3Quiz2.setText("" + Word_LIST.get(2));

//        tv1Quiz2.setBackgroundColor(getResources().getColor(R.color.whitecolor));
//        tv2Quiz2.setBackgroundColor(getResources().getColor(R.color.whitecolor));
//        tv3Quiz2.setBackgroundColor(getResources().getColor(R.color.whitecolor));

    }

    @OnClick({R.id.cv1_quiz2_sel, R.id.cv2_quiz2_sel, R.id.cv3_quiz2_sel,R.id.iv_quiz2_back, R.id.iv_quiz2_help, R.id.iv_quiz2_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv1_quiz2_sel:
                cv1Quiz2Sel.startAnimation(AnimationUtils.loadAnimation(Quiz2Activity.this, R.anim.image_click));
                if (tv1Quiz2.getText().toString().equalsIgnoreCase(wordtoSEL)) {
                    congrats();
                    Toast.makeText(Quiz2Activity.this, "Load next 3 words", Toast.LENGTH_SHORT).show();
                } else {
                    clickcount1=clickcount1+1;
                    if(clickcount1>=3)
                    {
                        tv1Quiz2.setTextColor(getResources().getColor(R.color.textcolorlessons));
                        cv1Quiz2Sel.setClickable(false);
//                        clickcount1=0;
                    }
                    else {
                        tv1Quiz2.setTextColor(getResources().getColor(R.color.whitecolor));
                        cv1Quiz2Sel.setClickable(true);
                    }
                    play_tryagain();
                }
                break;
            case R.id.cv2_quiz2_sel:
                cv2Quiz2Sel.startAnimation(AnimationUtils.loadAnimation(Quiz2Activity.this, R.anim.image_click));
                if (tv2Quiz2.getText().toString().equalsIgnoreCase(wordtoSEL)) {
                    congrats();
                    Toast.makeText(Quiz2Activity.this, "Load next 3 words", Toast.LENGTH_SHORT).show();
                } else {
                    clickcount2=clickcount2+1;
                    if(clickcount2>=3)
                    {
                        tv2Quiz2.setTextColor(getResources().getColor(R.color.textcolorlessons));
                        cv2Quiz2Sel.setClickable(false);
//                        clickcount2=0;
                    }
                    else {
                        tv2Quiz2.setTextColor(getResources().getColor(R.color.whitecolor));
                        cv2Quiz2Sel.setClickable(true);
                    }
                    play_tryagain();
                }
                break;
            case R.id.cv3_quiz2_sel:
                cv3Quiz2Sel.startAnimation(AnimationUtils.loadAnimation(Quiz2Activity.this, R.anim.image_click));
                if (tv3Quiz2.getText().toString().equalsIgnoreCase(wordtoSEL)) {
                    congrats();
                    Toast.makeText(Quiz2Activity.this, "Load next 3 words", Toast.LENGTH_SHORT).show();
                } else {
                    clickcount3=clickcount3+1;
                    if(clickcount3>=3)
                    {
                        tv3Quiz2.setTextColor(getResources().getColor(R.color.textcolorlessons));
                        cv3Quiz2Sel.setClickable(false);
//                        clickcount=0;
                    }
                    else {
                        tv3Quiz2.setTextColor(getResources().getColor(R.color.whitecolor));
                        cv3Quiz2Sel.setClickable(true);
                    }
                    play_tryagain();
                }
                break;
            case R.id.iv_quiz2_back:
                ivQuiz2Back.startAnimation(AnimationUtils.loadAnimation(Quiz2Activity.this, R.anim.image_click));
                finish();
                break;
            case R.id.iv_quiz2_help:
                ivQuiz2Help.startAnimation(AnimationUtils.loadAnimation(Quiz2Activity.this, R.anim.image_click));
                Intent i = new Intent(Quiz2Activity.this, Cad_Game_Activity.class);
                startActivity(i);
                break;
            case R.id.iv_quiz2_home:
                ivQuiz2Home.startAnimation(AnimationUtils.loadAnimation(Quiz2Activity.this, R.anim.image_click));
                Intent i2 = new Intent(Quiz2Activity.this, HomeActivity.class);
                startActivity(i2);
                finish();
                break;
        }
    }

    public void play_tryagain() {

        cv1Quiz2Sel.setClickable(false);
        cv2Quiz2Sel.setClickable(false);
        cv3Quiz2Sel.setClickable(false);

        MediaPlayer mPlayer2 = MediaPlayer.create(Quiz2Activity.this, R.raw.tryagain);
        mPlayer2.start();
        mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Log.d("MEDIA", "" + mediaPlayer);
                cv1Quiz2Sel.setClickable(true);
                cv2Quiz2Sel.setClickable(true);
                cv3Quiz2Sel.setClickable(true);

            }
        });
    }
    public void congrats() {
        cv1Quiz2Sel.setClickable(false);
        cv2Quiz2Sel.setClickable(false);
        cv3Quiz2Sel.setClickable(false);

        MediaPlayer mPlayer2 = MediaPlayer.create(Quiz2Activity.this, R.raw.congrats);
        mPlayer2.start();
        mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Log.d("MEDIA", "" + mediaPlayer);
                cv1Quiz2Sel.setClickable(true);
                cv2Quiz2Sel.setClickable(true);
                cv3Quiz2Sel.setClickable(true);


            }
        });
    }

}