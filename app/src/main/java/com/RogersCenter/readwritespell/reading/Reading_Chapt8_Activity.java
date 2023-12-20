package com.RogersCenter.readwritespell.reading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.RogersCenter.readwritespell.CustomAPEZProvider;
import com.RogersCenter.readwritespell.HomeActivity;
import com.RogersCenter.readwritespell.R;
import com.RogersCenter.readwritespell.StaticValues;
import com.RogersCenter.readwritespell.langs.Lang_3_Activity;
import com.RogersCenter.readwritespell.quiz.Quiz1Activity;
import com.RogersCenter.readwritespell.spellings.Spell_4_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.View_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;

public class Reading_Chapt8_Activity extends AppCompatActivity {

    VideoView vv;
    View layoutvideoviev,layout_bottom_nav,layout_read_game_chpt;
    int position = 0;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;
    List<Uri> listURI = new ArrayList<>();
    ConstraintLayout cl_read_click,cl_1,cl_2;
    View read_v_1,read_v_2,read_v_3,read_v_4,read_v_5,read_v_6;
    CardView cv1_read_game,cv2_read_game,cv3_read_game,cv1_read_game_sel,cv2_read_game_sel,cv3_read_game_sel;
    TextView tv1_read_game,tv2_read_game,tv3_read_game;
    int count=1,gmepos1=0,gmepos2=0,gmepos3=0;
    List<String> strs = new ArrayList<>();
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    String[] strArray = {"exp_vids/read_8_1_v1.webm", "exp_vids/read_8_2_v2.webm", "exp_vids/read_8_3_v3.webm", "exp_vids/read_8_4_cl.webm", "exp_vids/read_8_5.webm", "exp_vids/read_8_6_v1.webm",
            "exp_vids/read_8_7_v3.webm", "exp_vids/read_8_8_cl.webm", "exp_vids/read_8_9.webm", "exp_vids/read_8_10_v1.webm", "exp_vids/read_8_11_v2.webm", "exp_vids/read_8_12_v3.webm","exp_vids/read_8_13_cl.webm",
            "exp_vids/read_8_14.webm","exp_vids/read_8_15_v1.webm","exp_vids/read_8_16_v2.webm","exp_vids/read_8_17_v3.webm","exp_vids/read_8_18_cl.webm","exp_vids/read_8_19.webm","exp_vids/read_8_20_v1.webm",
            "exp_vids/read_8_21_v2.webm","exp_vids/read_8_22_v3.webm","exp_vids/read_8_23_cl1.webm","exp_vids/read_8_24_v4.webm","exp_vids/read_8_25_v5.webm","exp_vids/read_8_26_v6.webm","exp_vids/read_8_27_cl2.webm",
            "exp_vids/read_8_28_cl.webm","exp_vids/read_8_29.webm","exp_vids/read_8_30_v1.webm","exp_vids/read_8_31v2.webm","exp_vids/read_8_32_v3.webm","exp_vids/read_8_33_cl1.webm","exp_vids/read_8_34_v4.webm",
            "exp_vids/read_8_35_v5.webm","exp_vids/read_8_36_v6.webm","exp_vids/read_8_37_cl2.webm","exp_vids/read_8_38_cl.webm","exp_vids/read_8_39.webm","exp_vids/read_8_40_v1.webm","exp_vids/read_8_41_v2.webm",
            "exp_vids/read_8_42_v3.webm","exp_vids/read_8_43_cl1.webm","exp_vids/read_8_44_v4.webm","exp_vids/read_8_45_v5.webm","exp_vids/read_8_46_v6.webm","exp_vids/read_8_47_cl2.webm","exp_vids/read_8_48_cl.webm",
            "exp_vids/read_8_49.webm","exp_vids/read_8_50_v1.webm","exp_vids/read_8_51_v2.webm","exp_vids/read_8_52_v3.webm","exp_vids/read_8_53_cl1.webm","exp_vids/read_8_54_v4.webm","exp_vids/read_8_55_v5.webm",
            "exp_vids/read_8_56_v6.webm","exp_vids/read_8_57_cl2.webm","exp_vids/read_8_58_cl.webm","exp_vids/read_8_59.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_chapt8);

        vv = findViewById(R.id.vv);
        layoutvideoviev = findViewById(R.id.layoutvideoviev);
        layout_bottom_nav = findViewById(R.id.layout_bottom_nav);
        layout_read_game_chpt = findViewById(R.id.layout_read_game_chpt);
        iv_back = findViewById(R.id.iv_l2_back);
        iv_previous = findViewById(R.id.iv_l2_previous);
        iv_playpause = findViewById(R.id.iv_l2_playpause);
        iv_next = findViewById(R.id.iv_l2_next);
        iv_home = findViewById(R.id.iv_l2_home);

        cl_read_click = findViewById(R.id.cl_read_click);
        cl_1 = findViewById(R.id.cl_1);
        cl_2 = findViewById(R.id.cl_2);
        read_v_1 = findViewById(R.id.read_v_1);
        read_v_2 = findViewById(R.id.read_v_2);
        read_v_3 = findViewById(R.id.read_v_3);
        read_v_4 = findViewById(R.id.read_v_4);
        read_v_5 = findViewById(R.id.read_v_5);
        read_v_6 = findViewById(R.id.read_v_6);

        cv1_read_game = findViewById(R.id.cv1_read_game);
        cv2_read_game = findViewById(R.id.cv2_read_game);
        cv3_read_game = findViewById(R.id.cv3_read_game);
        cv1_read_game_sel = findViewById(R.id.cv1_read_game_sel);
        cv2_read_game_sel = findViewById(R.id.cv2_read_game_sel);
        cv3_read_game_sel = findViewById(R.id.cv3_read_game_sel);
        tv1_read_game = findViewById(R.id.tv1_read_game);
        tv2_read_game = findViewById(R.id.tv2_read_game);
        tv3_read_game = findViewById(R.id.tv3_read_game);

        for (int i = 0; i < strArray.length; i++) {
            Uri uri = CustomAPEZProvider.buildUri("" + strArray[i]);
            listURI.add(i, uri);
        }
        if (!listURI.isEmpty())
        {
            checkfileexists();
        }
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Reading_Chapt8_Activity.this, iv_back);
                stopvid();
                finish();
            }
        });

        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Reading_Chapt8_Activity.this, iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                    checkfileexists();
                } else {
                    Toast.makeText(Reading_Chapt8_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Reading_Chapt8_Activity.this, iv_playpause);
                if (vv.isPlaying()) {
                    pausePlayer();
                    iv_playpause.setImageResource(R.drawable.ic_play);
                } else {
                    resumetPlayer();
                    iv_playpause.setImageResource(R.drawable.ic_pause);

                }
            }
        });

        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Reading_Chapt8_Activity.this, iv_next);
                position += 1;
                if (position <= strArray.length - 1)  {
                    stopvid();
                    checkfileexists();
                } else {
//                    if(position>52)
//                    {

//                    layoutvideoviev.setVisibility(View.GONE);
//                    layout_read_game_chpt.setVisibility(View.VISIBLE);
//                    cl_read_click.setVisibility(View.GONE);
//                    setgame();
//                    }
                    position -= 1;
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(Reading_Chapt8_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Reading_Chapt8_Activity.this, Quiz1Activity.class);
//                            startActivity(i);
                            //finish();
                        }
                    });

                }
            }
        });

        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Reading_Chapt8_Activity.this, iv_home);
                Intent i1 = new Intent(Reading_Chapt8_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        strs.add("ox");//0
        strs.add("box");//1
        strs.add("fox");//2
        strs.add("tax");//3
        strs.add("rags");//4
        strs.add("zip");//5
        strs.add("cut");//6
        strs.add("yes");//7
        strs.add("quiz");//8

        cl_read_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if(position==3||position==7||position==12||position==17||position==27||position==37||position==47||position==57) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Reading_Chapt8_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Reading_Chapt8_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        cl_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if(position==22||position==32||position==42||position==52) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Reading_Chapt8_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Reading_Chapt8_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        cl_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if(position==26||position==36||position==46||position==56) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Reading_Chapt8_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Reading_Chapt8_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        read_v_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    View_anim(Reading_Chapt8_Activity.this,read_v_1);
                    if(position==0||position==5||position==9||position==14||position==19||position==29||position==39||position==49) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Reading_Chapt8_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Reading_Chapt8_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        read_v_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    View_anim(Reading_Chapt8_Activity.this,read_v_2);
                    if(position==1||position==10||position==15||position==20||position==30||position==40||position==50){
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Reading_Chapt8_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Reading_Chapt8_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        read_v_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    View_anim(Reading_Chapt8_Activity.this,read_v_3);
                    if(position==2||position==6||position==11||position==16||position==21||position==31||position==41||position==51) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Reading_Chapt8_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Reading_Chapt8_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        read_v_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    View_anim(Reading_Chapt8_Activity.this,read_v_4);
                    if(position==23||position==33||position==43||position==53) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Reading_Chapt8_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Reading_Chapt8_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        read_v_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    View_anim(Reading_Chapt8_Activity.this,read_v_5);
                    if(position==24||position==34||position==44||position==54) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Reading_Chapt8_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Reading_Chapt8_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        read_v_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    View_anim(Reading_Chapt8_Activity.this,read_v_6);
                    if(position==25||position==35||position==45||position==55) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Reading_Chapt8_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Reading_Chapt8_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
    }
    
    private void initializePlayer(int pos, List<Uri> urilist) {

//        vv.setVideoPath("http://techslides.com/demos/sample-videos/small.webm");
//        vv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + urilist.get(pos)));
        vv.setVideoURI(urilist.get(pos));
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(vv);
        vv.setMediaController(mediaController);
        vv.requestFocus();
        vv.start();
        vv.setMediaController(null);

        if(position==4||position==8||position==13||position==18||position==28||position==38||position==48||position==58)
        {
            read_v_4.setVisibility(View.GONE);
            read_v_5.setVisibility(View.GONE);
            read_v_6.setVisibility(View.GONE);
            layoutvideoviev.setVisibility(View.VISIBLE);
            layout_read_game_chpt.setVisibility(View.GONE);
            cl_read_click.setVisibility(View.GONE);
            iv_next.setClickable(true);
            iv_next.setEnabled(true);

            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    image_anim_last(Reading_Chapt8_Activity.this,iv_next);
                    iv_playpause.setImageResource(R.drawable.ic_play);
                }
            });
        }
        else if (position>=19)
        {
            read_v_1.setVisibility(View.VISIBLE);
            read_v_2.setVisibility(View.VISIBLE);
            read_v_4.setVisibility(View.VISIBLE);
            read_v_5.setVisibility(View.VISIBLE);
            read_v_6.setVisibility(View.VISIBLE);
            cl_1.setVisibility(View.VISIBLE);
            cl_2.setVisibility(View.VISIBLE);

            layoutvideoviev.setVisibility(View.VISIBLE);
            layout_read_game_chpt.setVisibility(View.GONE);
            cl_read_click.setVisibility(View.VISIBLE);
        }
        else if (position==0||position==1||position==2)
        {
            read_v_1.setVisibility(View.VISIBLE);
            read_v_2.setVisibility(View.VISIBLE);
            read_v_3.setVisibility(View.VISIBLE);
            read_v_4.setVisibility(View.GONE);
            read_v_5.setVisibility(View.GONE);
            read_v_6.setVisibility(View.GONE);
            cl_1.setVisibility(View.GONE);
            cl_2.setVisibility(View.GONE);
            layoutvideoviev.setVisibility(View.VISIBLE);
            layout_read_game_chpt.setVisibility(View.GONE);
            cl_read_click.setVisibility(View.VISIBLE);
        }
        else if (position==5||position==6)
        {
            read_v_1.setVisibility(View.VISIBLE);
            read_v_2.setVisibility(View.GONE);
            read_v_3.setVisibility(View.VISIBLE);
            read_v_4.setVisibility(View.GONE);
            read_v_5.setVisibility(View.GONE);
            read_v_6.setVisibility(View.GONE);
            cl_1.setVisibility(View.GONE);
            cl_2.setVisibility(View.GONE);
            layoutvideoviev.setVisibility(View.VISIBLE);
            layout_read_game_chpt.setVisibility(View.GONE);
            cl_read_click.setVisibility(View.VISIBLE);
        }
        else{
            iv_next.setClickable(false);
            iv_next.setEnabled(false);
            read_v_2.setVisibility(View.VISIBLE);
            read_v_4.setVisibility(View.GONE);
            read_v_5.setVisibility(View.GONE);
            read_v_6.setVisibility(View.GONE);
            layoutvideoviev.setVisibility(View.VISIBLE);
            layout_read_game_chpt.setVisibility(View.GONE);
            cl_read_click.setVisibility(View.VISIBLE);
        }
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (progressDoalog1!=null)
                {
                    progressDoalog1.dismiss();
                }
                iv_playpause.setImageResource(R.drawable.ic_pause);
            }
        });

        Log.d("POS", pos + "  " + urilist.get(pos));
    }

    public void checkfileexists() {
            if (StaticValues.GetPreferences("Last_Open_position", Reading_Chapt8_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Reading_Chapt8_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Reading_Chapt8_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Reading_Chapt8_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Reading_Chapt8_Activity.this);
            }
            initializePlayer(position, listURI);

    }

    public void stopvid() {
        if (vv != null)
            vv.stopPlayback();
    }

    private void pausePlayer() {
        if (vv.isPlaying()) {
            vv.pause();
        }
//        player.getPlaybackState();
    }

    private void resumetPlayer() {
        vv.start();
//        player.setPlayWhenReady(true);
//        player.getPlaybackState();
    }

    public void setgame()
    {
        if (count==1)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.kit);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=3;gmepos2=6;gmepos3=0;
                    game(gmepos3);//cardposition

                }
            });

        }
        else if (count==2)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.jim);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=5;gmepos2=0;gmepos3=7;
                    game(gmepos3);

                }
            });
        }
        else if (count==3)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.lit);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=10;gmepos2=1;gmepos3=0;
                    game(gmepos2);

                }
            });

        }
        else if (count==4)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.tab);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=10;gmepos2=7;gmepos3=6;
                    game(gmepos1);

                }
            });
        }
        else if (count==5)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.dig);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=0;gmepos2=2;gmepos3=8;
                    game(gmepos2);
                }
            });
        }
        else if (count==6)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.jam);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=7;gmepos2=6;gmepos3=0;
                    game(gmepos2);
                }
            });
        }
        else if (count==7)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.if_1);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=6;gmepos2=4;gmepos3=8;
                    game(gmepos2);
                }
            });
        }
        else if (count==8)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.meg);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=8;gmepos2=0;gmepos3=2;
                    game(gmepos1);
                }
            });
        }
        else if (count==9)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.hid);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=1;gmepos2=3;gmepos3=10;
                    game(gmepos2);
                }
            });
        }
        else if (count==10)
        {
            MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.met);
            mPlayer2.start();
            mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    gmepos1=10;gmepos2=9;gmepos3=6;
                    game(gmepos2);
                }
            });
        }
        else if (count==11)
        {
            //game done
//            if(position==strArray.length-1)
//            {
                View layout_lastpage  = findViewById(R.id.layout_lastpage);
                TextView tv_lastpage_text= findViewById(R.id.tv_lastpage_text);
                ImageButton ib_home= findViewById(R.id.ib_home);
                vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        layout_lastpage.setVisibility(View.VISIBLE);
                        tv_lastpage_text.setText("You Completed Reading Lesson 8");
                        ib_home.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                StaticValues.SavePreferences("Reading_Chapt8_Activity","YES", Reading_Chapt8_Activity.this);
                                Intent intent = new Intent(Reading_Chapt8_Activity.this, Spell_4_Activity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                });
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

        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.tryagain);
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

        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt8_Activity.this, R.raw.right);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopvid();
        StaticValues.SavePreferences("Last_Open_Lesson", "Reading_Chapt8_Activity", Reading_Chapt8_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Reading", Reading_Chapt8_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Reading_Chapt8_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Reading_Chapt8_Activity", Reading_Chapt8_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Reading", Reading_Chapt8_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Reading_Chapt8_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Reading_Chapt8_Activity", Reading_Chapt8_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Reading", Reading_Chapt8_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Reading_Chapt8_Activity.this);
    }
}