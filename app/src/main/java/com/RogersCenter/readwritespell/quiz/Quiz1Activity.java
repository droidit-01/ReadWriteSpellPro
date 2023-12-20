package com.RogersCenter.readwritespell.quiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.RogersCenter.readwritespell.HomeActivity;
import com.RogersCenter.readwritespell.MainActivity;
import com.RogersCenter.readwritespell.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Quiz1Activity extends AppCompatActivity {

    @BindView(R.id.iv_quiz1)
    ImageView ivQuiz1;
    @BindView(R.id.cv_quiz1)
    CardView cvQuiz1;
    @BindView(R.id.btn_key_b)
    Button btnKeyB;
    @BindView(R.id.btn_key_c)
    Button btnKeyC;
    @BindView(R.id.btn_key_d)
    Button btnKeyD;
    @BindView(R.id.btn_key_e)
    Button btnKeyE;
    @BindView(R.id.btn_key_f)
    Button btnKeyF;
    @BindView(R.id.btn_key_g)
    Button btnKeyG;
    @BindView(R.id.btn_key_h)
    Button btnKeyH;
    @BindView(R.id.btn_key_i)
    Button btnKeyI;
    @BindView(R.id.btn_key_j)
    Button btnKeyJ;
    @BindView(R.id.btn_key_k)
    Button btnKeyK;
    @BindView(R.id.btn_key_l)
    Button btnKeyL;
    @BindView(R.id.btn_key_m)
    Button btnKeyM;
    @BindView(R.id.btn_key_n)
    Button btnKeyN;
    @BindView(R.id.btn_key_o)
    Button btnKeyO;
    @BindView(R.id.btn_key_p)
    Button btnKeyP;
    @BindView(R.id.btn_key_q)
    Button btnKeyQ;
    @BindView(R.id.btn_key_r)
    Button btnKeyR;
    @BindView(R.id.btn_key_s)
    Button btnKeyS;
    @BindView(R.id.btn_key_t)
    Button btnKeyT;
    @BindView(R.id.btn_key_u)
    Button btnKeyU;
    @BindView(R.id.btn_key_v)
    Button btnKeyV;
    @BindView(R.id.btn_key_w)
    Button btnKeyW;
    @BindView(R.id.btn_key_x)
    Button btnKeyX;
    @BindView(R.id.btn_key_y)
    Button btnKeyY;
    @BindView(R.id.btn_key_z)
    Button btnKeyZ;
    @BindView(R.id.iv_quiz1_back)
    ImageView ivQuiz1Back;

    @BindView(R.id.iv_quiz1_home)
    ImageView ivQuiz1Home;
    @BindView(R.id.btn_key_a)
    Button btnKeyA;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.iv_quiz1_help)
    ImageView ivQuiz1Help;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_quiz1, R.id.cv_quiz1, R.id.btn_key_a, R.id.btn_key_b, R.id.btn_key_c, R.id.btn_key_d, R.id.btn_key_e, R.id.btn_key_f, R.id.btn_key_g, R.id.btn_key_h, R.id.btn_key_i, R.id.btn_key_j, R.id.btn_key_k, R.id.btn_key_l, R.id.btn_key_m, R.id.btn_key_n, R.id.btn_key_o, R.id.btn_key_p, R.id.btn_key_q, R.id.btn_key_r, R.id.btn_key_s, R.id.btn_key_t, R.id.btn_key_u, R.id.btn_key_v, R.id.btn_key_w, R.id.btn_key_x, R.id.btn_key_y, R.id.btn_key_z, R.id.iv_quiz1_back, R.id.iv_quiz1_help, R.id.iv_quiz1_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_quiz1:
                image_anim(ivQuiz1);
                MediaPlayer mPlayer1 = MediaPlayer.create(Quiz1Activity.this, R.raw.apple);
                mPlayer1.start();
                break;
            case R.id.cv_quiz1:
                cvQuiz1.startAnimation(AnimationUtils.loadAnimation(Quiz1Activity.this, R.anim.image_click));
                MediaPlayer mPlayer2 = MediaPlayer.create(Quiz1Activity.this, R.raw.a_wd_snd);
                mPlayer2.start();
                break;
            case R.id.btn_key_a:
                keyboard_button_anim(btnKeyA);
                break;
            case R.id.btn_key_b:
                keyboard_button_anim(btnKeyB);
                break;
            case R.id.btn_key_c:
                keyboard_button_anim(btnKeyC);
                break;
            case R.id.btn_key_d:
                keyboard_button_anim(btnKeyD);
                break;
            case R.id.btn_key_e:
                keyboard_button_anim(btnKeyE);
                break;
            case R.id.btn_key_f:
                keyboard_button_anim(btnKeyF);
                break;
            case R.id.btn_key_g:
                keyboard_button_anim(btnKeyG);
                break;
            case R.id.btn_key_h:
                keyboard_button_anim(btnKeyH);
                break;
            case R.id.btn_key_i:
                keyboard_button_anim(btnKeyI);
                break;
            case R.id.btn_key_j:
                keyboard_button_anim(btnKeyJ);
                break;
            case R.id.btn_key_k:
                keyboard_button_anim(btnKeyK);
                break;
            case R.id.btn_key_l:
                keyboard_button_anim(btnKeyL);
                break;
            case R.id.btn_key_m:
                keyboard_button_anim(btnKeyM);
                break;
            case R.id.btn_key_n:
                keyboard_button_anim(btnKeyN);
                break;
            case R.id.btn_key_o:
                keyboard_button_anim(btnKeyO);
                break;
            case R.id.btn_key_p:
                keyboard_button_anim(btnKeyP);
                break;
            case R.id.btn_key_q:
                keyboard_button_anim(btnKeyQ);
                break;
            case R.id.btn_key_r:
                keyboard_button_anim(btnKeyR);
                break;
            case R.id.btn_key_s:
                keyboard_button_anim(btnKeyS);
                break;
            case R.id.btn_key_t:
                keyboard_button_anim(btnKeyT);
                break;
            case R.id.btn_key_u:
                keyboard_button_anim(btnKeyU);
                break;
            case R.id.btn_key_v:
                keyboard_button_anim(btnKeyV);
                break;
            case R.id.btn_key_w:
                keyboard_button_anim(btnKeyW);
                break;
            case R.id.btn_key_x:
                keyboard_button_anim(btnKeyX);
                break;
            case R.id.btn_key_y:
                keyboard_button_anim(btnKeyY);
                break;
            case R.id.btn_key_z:
                keyboard_button_anim(btnKeyZ);
                break;
            case R.id.iv_quiz1_back:
                image_anim(ivQuiz1Back);
                Intent i1 = new Intent(Quiz1Activity.this, MainActivity.class);
                startActivity(i1);
                finish();
                break;
            case R.id.iv_quiz1_help:
                image_anim(ivQuiz1Help);
                break;

            case R.id.iv_quiz1_home:
                image_anim(ivQuiz1Home);
                Intent i2 = new Intent(Quiz1Activity.this, HomeActivity.class);
                startActivity(i2);
                finish();
                break;
        }
    }

    public void keyboard_button_anim(Button btn) {
        btn.startAnimation(AnimationUtils.loadAnimation(Quiz1Activity.this, R.anim.image_click));
        if (btn.getText().toString().equalsIgnoreCase(ivQuiz1.getTag().toString())) {

            MediaPlayer mPlayer2 = MediaPlayer.create(Quiz1Activity.this, R.raw.a);
            mPlayer2.start();

            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Log.d("MEDIA", "" + mediaPlayer);
                    MediaPlayer mPlayer = MediaPlayer.create(Quiz1Activity.this, R.raw.congrats);
                    mPlayer.start();
                    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Intent i = new Intent(Quiz1Activity.this, Quiz2Activity.class);
                            startActivity(i);
                        }
                    });


                }
            });

//
//            MediaPlayer mediaPlayer = MediaPlayer.create(Quiz1Activity.this, R.raw.a_wd_snd);//define a new mediaplayer with your local wav file
//
//            mediaPlayer.start(); // no need to call prepare(); create() does that for you
//            number(0,Quiz1Activity.this);
        } else {

            MediaPlayer mPlayer2 = MediaPlayer.create(Quiz1Activity.this, R.raw.tryagain);
            mPlayer2.start();
        }
    }

    public void image_anim(ImageView img) {
        img.startAnimation(AnimationUtils.loadAnimation(Quiz1Activity.this, R.anim.image_click));
    }


}