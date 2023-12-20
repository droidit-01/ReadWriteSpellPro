package com.RogersCenter.readwritespell.reading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.RogersCenter.readwritespell.HomeActivity;
import com.RogersCenter.readwritespell.R;
import com.RogersCenter.readwritespell.StaticValues;
import com.RogersCenter.readwritespell.langs.Lang_3_Activity;
import com.RogersCenter.readwritespell.spellings.Spell_3_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;

public class Reading_Chapt7_Activity extends AppCompatActivity {

    public int startcount=0,endcount=1,position=0;
    TextView tv_Read_Chapt;
    CardView cv_Read_Chapt;
    View layout_read_chpt,layout_read_game_chpt;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;
    List<String> strs = new ArrayList<>();

    CardView cv1_read_game,cv2_read_game,cv3_read_game,cv1_read_game_sel,cv2_read_game_sel,cv3_read_game_sel;
    TextView tv1_read_game,tv2_read_game,tv3_read_game,tv_prev,tvnext;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    int count=1,gmepos1=0,gmepos2=0,gmepos3=0;
    private ProgressDialog progressDoalog1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_chapt7);
        layout_read_chpt = findViewById(R.id.layout_read_chpt);
        layout_read_game_chpt = findViewById(R.id.layout_read_game_chpt);

        tv_Read_Chapt = findViewById(R.id.tv_read_chpt);
        cv_Read_Chapt = findViewById(R.id.cv_read_chpt);
        iv_back = findViewById(R.id.iv_l2_back);
        iv_previous = findViewById(R.id.iv_l2_previous);
        iv_playpause = findViewById(R.id.iv_l2_playpause);
        iv_next = findViewById(R.id.iv_l2_next);
        tv_prev = findViewById(R.id.textView9);
        tvnext = findViewById(R.id.textView8);
        iv_home = findViewById(R.id.iv_l2_home);

        cv1_read_game = findViewById(R.id.cv1_read_game);
        cv2_read_game = findViewById(R.id.cv2_read_game);
        cv3_read_game = findViewById(R.id.cv3_read_game);
        cv1_read_game_sel = findViewById(R.id.cv1_read_game_sel);
        cv2_read_game_sel = findViewById(R.id.cv2_read_game_sel);
        cv3_read_game_sel = findViewById(R.id.cv3_read_game_sel);
        tv1_read_game = findViewById(R.id.tv1_read_game);
        tv2_read_game = findViewById(R.id.tv2_read_game);
        tv3_read_game = findViewById(R.id.tv3_read_game);


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Reading_Chapt7_Activity.this,iv_back);
                finish();
            }
        });
        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Reading_Chapt7_Activity.this,iv_previous);
                if (position > 0) {
                    position--;
                    startcount = 0;
                    endcount = 1;
//                    spanstring();
                }
            }
        });
        iv_playpause.setVisibility(View.GONE);
        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Reading_Chapt7_Activity.this,iv_next);

            }
        });
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Reading_Chapt7_Activity.this,iv_home);
                Intent i1 = new Intent(Reading_Chapt7_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        strs.add("hat");//0
        strs.add("bat");//1
        strs.add("rat");//2
        strs.add("fat");//3
        strs.add("mat");//4
        strs.add("vat");//5
        strs.add("pet");//6
        strs.add("jet");//7
        strs.add("wet");//8
        strs.add("let");//9
        strs.add("kit");//10
        strs.add("pit");//11
        strs.add("wit");//12
        strs.add("lit");//13
        strs.add("bit");//14
        strs.add("sit");//15
        strs.add("bet");//16

        tv_prev.setVisibility(View.GONE);
        tvnext.setVisibility(View.GONE);
        iv_next.setVisibility(View.GONE);
        iv_previous.setVisibility(View.GONE);
        set_notclickable_view();
        layout_read_chpt.setVisibility(View.GONE);
        layout_read_game_chpt.setVisibility(View.VISIBLE);
        setgame();

    }

    public void set_notclickable_view()
    {
        iv_previous.setEnabled(false);
        iv_previous.setClickable(false);
        iv_previous.setAlpha(0.8f);
        iv_next.setEnabled(false);
        iv_next.setClickable(false);
        iv_next.setAlpha(0.8f);
    }
    public void set_clickable_view()
    {
        iv_previous.setEnabled(true);
        iv_previous.setClickable(true);
        iv_previous.setAlpha(1.0f);
        iv_next.setEnabled(true);
        iv_next.setClickable(true);
        iv_next.setAlpha(1.0f);
    }

    public void setgame()
    {
//  strs.add("hat");//0  strs.add("bat");//1  strs.add("rat");//2  strs.add("fat");//3  strs.add("mat");//4  strs.add("vat");//5
//  strs.add("pet");//6  strs.add("jet");//7  strs.add("wet");//8  strs.add("let");//9  strs.add("kit");//10 strs.add("pit");//11
//  strs.add("wit");//12  strs.add("lit");//13  strs.add("bit");//14  strs.add("sit");//15  strs.add("bet");//16
        if (count==1)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt7_Activity.this, R.raw.hat);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=1;gmepos2=4;gmepos3=0;
                    game(gmepos3);//cardposition

                }
            });

        }
        else if (count==2)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt7_Activity.this, R.raw.vat);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=4;gmepos2=5;gmepos3=3;
                    game(gmepos2);

                }
            });
        }
        else if (count==3)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt7_Activity.this, R.raw.rat);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=3;gmepos2=2;gmepos3=0;
                    game(gmepos2);

                }
            });

        }
        else if (count==4)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt7_Activity.this, R.raw.bat);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=1;gmepos2=5;gmepos3=2;
                    game(gmepos1);

                }
            });
        }
        else if (count==5)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt7_Activity.this, R.raw.mat);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=1;gmepos2=4;gmepos3=2;
                    game(gmepos2);
                }
            });
        }
        else if (count==6)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt7_Activity.this, R.raw.fat);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=4;gmepos2=2;gmepos3=3;
                    game(gmepos3);
                }
            });
        }
        else if (count==7)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt7_Activity.this, R.raw.bet);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=16;gmepos2=8;gmepos3=7;
                    game(gmepos1);
                }
            });
        }
        else if (count==8)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt7_Activity.this, R.raw.wet);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=8;gmepos2=7;gmepos3=16;
                    game(gmepos1);
                }
            });
        }
        else if (count==9)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt7_Activity.this, R.raw.let);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=8;gmepos2=16;gmepos3=9;
                    game(gmepos3);
                }
            });
        }
        else if (count==10)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt7_Activity.this, R.raw.jet);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=7;gmepos2=8;gmepos3=9;
                    game(gmepos1);
                }
            });
        }
        else if (count==11)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt7_Activity.this, R.raw.wit);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=11;gmepos2=10;gmepos3=12;
                    game(gmepos3);
                }
            });
        }
        else if (count==12)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt7_Activity.this, R.raw.sit);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=10;gmepos2=12;gmepos3=15;
                    game(gmepos3);
                }
            });
        }
        else if (count==13)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt7_Activity.this, R.raw.pit);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=12;gmepos2=11;gmepos3=10;
                    game(gmepos2);
                }
            });
        }
        else if (count==14)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt7_Activity.this, R.raw.lit);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=12;gmepos2=13;gmepos3=14;
                    game(gmepos2);
                }
            });
        }
        else if (count==15)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt7_Activity.this, R.raw.bit);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=11;gmepos2=14;gmepos3=10;
                    game(gmepos2);
                }
            });
        }
        else if (count==16)
        {
            //game done
//            if(position==strArray.length-1)
//            {
                View layout_lastpage  = findViewById(R.id.layout_lastpage);
                TextView tv_lastpage_text= findViewById(R.id.tv_lastpage_text);
                ImageButton ib_home= findViewById(R.id.ib_home);
//                vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mediaPlayer) {
                        layout_lastpage.setVisibility(View.VISIBLE);
                        tv_lastpage_text.setText("You Completed Reading Lesson 7");
                        ib_home.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                StaticValues.SavePreferences("Reading_Chapt7_Activity","YES", Reading_Chapt7_Activity.this);
                                Intent intent = new Intent(Reading_Chapt7_Activity.this, Lang_3_Activity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
//                    }
//                });
//            }
        }
    }

    public void game(int selpos)
    {
        tv1_read_game.setText("" + strs.get(gmepos1));
        tv2_read_game.setText("" + strs.get(gmepos2));
        tv3_read_game.setText("" + strs.get(gmepos3));

        cv1_read_game_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv1_read_game.getText().toString().equalsIgnoreCase(strs.get(selpos)))
                {
                    congrats();
                }else {
                    play_tryagain();
                }

            }
        });
        cv2_read_game_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv2_read_game.getText().toString().equalsIgnoreCase(strs.get(selpos)))
                {
                    congrats();
                }else {
                    play_tryagain();
                }

            }
        });
        cv3_read_game_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv3_read_game.getText().toString().equalsIgnoreCase(strs.get(selpos)))
                {
                    congrats();
                }else {
                    play_tryagain();
                }

            }
        });
    }
    public void play_tryagain() {

//        cv1Quiz2Sel.setClickable(false);
//        cv2Quiz2Sel.setClickable(false);
//        cv3Quiz2Sel.setClickable(false);

        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt7_Activity.this, R.raw.tryagain);
        mPlayer2.start();
        mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Log.d("MEDIA", "" + mediaPlayer);
//                cv1Quiz2Sel.setClickable(true);
//                cv2Quiz2Sel.setClickable(true);
//                cv3Quiz2Sel.setClickable(true);

            }
        });
    }
    public void congrats()  {
//        cv1Quiz2Sel.setClickable(false);
//        cv2Quiz2Sel.setClickable(false);
//        cv3Quiz2Sel.setClickable(false);

        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt7_Activity.this, R.raw.right);
        mPlayer2.start();
        mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Log.d("MEDIA", "" + mediaPlayer);
//                cv1Quiz2Sel.setClickable(true);
//                cv2Quiz2Sel.setClickable(true);
//                cv3Quiz2Sel.setClickable(true);
                count++;
                setgame();

            }
        });
    }
}